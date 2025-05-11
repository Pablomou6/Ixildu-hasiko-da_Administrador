/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

import BaseDatos.FachadaBaseDatos;
import GUI.*;
import java.time.LocalDate;
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
    
    public void eliminarPelicula(Pelicula p) {
        fachadaBD.eliminarPelicula(p);
    }

    
    public Boolean editarPelicula(String titulo, String duracion, String genero, String sinopsis, String clasificacion, 
        String idioma, String fechaEstreno, String duracionTrailer) {
        
        if(!validarCampos(titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaEstreno, duracionTrailer)) {
            fachadaGUI.muestraExcepcion("Error al modificar los datos de la película.");
            return false;
        }
        
        //Todos los atributos contienen información. Además, la fecha de estreno tiene buen formato, por lo que lo parseamos a LocalDate
        LocalDate fechaEstrenoParsed = LocalDate.parse(fechaEstreno);
        
        //Creamos el objeto de Película que vamos a actualizar en la BD
        Pelicula peliculaEditar = new Pelicula(titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaEstrenoParsed, duracionTrailer);
        
        if(!fachadaBD.editarPelicula(peliculaEditar)) { return false; }
        
        return true;
    }
    
}
