/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    
    public Integer getDuracionMinutos() {
        if (duracion == null || duracion.isEmpty()) return 0;

        try {
            // Formato HH:mm:ss
            LocalTime tiempo = LocalTime.parse(duracion);
            int totalMinutos = tiempo.getHour() * 60 + tiempo.getMinute();

            // Redondear hacia arriba si hay segundos
            if (tiempo.getSecond() > 0) {
                totalMinutos += 1;
            }

            return totalMinutos;
        } catch (DateTimeParseException e) {
            try {
                // Formato mm:ss como alternativa
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss");
                LocalTime tiempo = LocalTime.parse(duracion, formatter);
                int totalMinutos = tiempo.getMinute();

                if (tiempo.getSecond() > 0) {
                    totalMinutos += 1;
                }

                return totalMinutos;
            } catch (DateTimeParseException e2) {
                System.err.println("Error al procesar duración: " + duracion);
                return 0;
            }
        }
    }
    
}
