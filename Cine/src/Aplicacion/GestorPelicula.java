/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

import BaseDatos.FachadaBaseDatos;
import GUI.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 *
 * @author alumnogreibd
 */
public class GestorPelicula {
    FachadaGUI fachadaGUI;
    FachadaBaseDatos fachadaBD;
    
    public GestorPelicula(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fachadaGUI = fgui;
        this.fachadaBD = fbd;
    }
    
    public List<Pelicula> buscarPeliculas(String titulo, String duracion, String genero, String sinopsis, String clasificacion, 
        String idioma, LocalDate fechaEstreno, String duracionTrailer) {
        
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        
        //Llamamos al gestor de las peliculas
        peliculas = fachadaBD.buscarPeliculas(titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaEstreno, duracionTrailer);
        
        return peliculas;
    }
    
    //Función para comprobar los atributos de una película antes de crear el objeto e introducirlo en la BD
    public boolean validarCampos(String titulo, String duracion, String genero, String sinopsis, String clasificacion,
        String idioma, String fechaEstreno, String duracionTrailer) {
        //Comprobamos que los campos no se proporcionasen vacíos
        if (titulo == null || titulo.isEmpty() ||
            duracion == null || duracion.isEmpty() ||
            genero == null || genero.isEmpty() ||
            sinopsis == null || sinopsis.isEmpty() ||
            clasificacion == null || clasificacion.isEmpty() ||
            idioma == null || idioma.isEmpty() ||
            fechaEstreno == null || fechaEstreno.isEmpty() ||
            duracionTrailer == null || duracionTrailer.isEmpty()) {
            return false;
        }

        //Validamos el formato de la duración y duración tráiler (HH:mm:ss). Con el formatter especificamos el formato con el que se introduce.
        //intentamos parsearlo, en caso de que no cumpla el formato, salta una excepción.
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            LocalTime.parse(duracion, timeFormatter);
            LocalTime.parse(duracionTrailer, timeFormatter);
        } 
        catch (DateTimeParseException e) {
            this.fachadaGUI.muestraExcepcion("La duración o la duración del trailer no sigue el formato correcto (HH:mm:ss).");
            return false;
        }

        //Validamos ahora el formato de la fecha de estreno (yyyy-MM-dd)
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(fechaEstreno, dateFormatter);
        } 
        catch (DateTimeParseException e) {
            this.fachadaGUI.muestraExcepcion("La fecha de estreno no sigue el formato correcto (yyyy-MM-dd).");
            return false;
        }

        return true;
    }
    
    public Boolean anadirPelicula(String titulo, String duracion, String genero, String sinopsis, String clasificacion, 
        String idioma, String fechaEstreno, String duracionTrailer) {
        
        if(!validarCampos(titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaEstreno, duracionTrailer)) {
            fachadaGUI.muestraExcepcion("Error al introducir los datos de la película.");
            return false;
        }
        
        //Todos los atributos contienen información. Además, la fecha de estreno tiene buen formato, por lo que lo parseamos a LocalDate
        LocalDate fechaEstrenoParsed = LocalDate.parse(fechaEstreno);
        
        //Creamos el objeto de Película que vamos a introducir en la BD
        Pelicula peliculaAnadir = new Pelicula(titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaEstrenoParsed, duracionTrailer);
        
        if(!fachadaBD.anadirPelicula(peliculaAnadir)) { return false; }
        
        return true;
    }
    
}
