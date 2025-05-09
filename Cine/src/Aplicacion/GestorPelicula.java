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
    
}
