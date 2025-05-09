/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

import java.time.LocalDate;

/**
 *
 * @author alumnogreibd
 */
public class Pelicula {
    
    private String titulo;
    private String duracion;
    private String genero;
    private String sinopsis;
    private String clasificacion;
    private String idioma;
    private LocalDate fechaEstreno;
    private String duracionTrailer;

    public Pelicula(String titulo, String duracion, String genero, String sinopsis, String clasificacion, String idioma, LocalDate fechaEstreno, String duracionTrailer) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.clasificacion = clasificacion;
        this.idioma = idioma;
        this.fechaEstreno = fechaEstreno;
        this.duracionTrailer = duracionTrailer;
    }
    
    

    public String getTitulo() {
        return titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public String getDuracionTrailer() {
        return duracionTrailer;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public void setDuracionTrailer(String duracionTrailer) {
        this.duracionTrailer = duracionTrailer;
    }
    
    
}
