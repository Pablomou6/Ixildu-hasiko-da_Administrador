/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author alumnogreibd
 */
public class Sesion {
    private Integer idSesion;
    private Integer idSala;
    private String titulo;
    private String fechaSesion;
    private String horaInicio;
    private Float precio;
    
    public Sesion(Integer idSesion, Integer idSala, String titulo, String fechaSesion, String horaInicio, Float precio) {
        this.idSesion = idSesion;
        this.idSala = idSala;
        this.titulo = titulo;
        this.fechaSesion = fechaSesion;
        this.horaInicio = horaInicio;
        this.precio = precio;
    }

    public Sesion(Integer idSala, String titulo, String fechaSesion, String horaInicio, Float precio) {
        this.idSala = idSala;
        this.titulo = titulo;
        this.fechaSesion = fechaSesion;
        this.horaInicio = horaInicio;
        this.precio = precio;
    }
    

    public Integer getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Integer idSesion) {
        this.idSesion = idSesion;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(String fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
    
}
