/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author alumnogreibd
 */
public class Anuncio {
    
    private Integer idAnuncio;
    private String tematica;
    private String duracion; //time en BD

    public Anuncio(Integer idAnuncio, String temática, String duracion) {
        this.idAnuncio = idAnuncio;
        this.tematica = temática;
        this.duracion = duracion;
    }

    public Integer getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(Integer idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Anuncio anuncio = (Anuncio) obj;
        return this.idAnuncio == anuncio.idAnuncio;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idAnuncio);
    }
    
}
