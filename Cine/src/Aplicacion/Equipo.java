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
public class Equipo {
    private Integer idEquipo;
    private Integer idSala;
    private String nombre;
    private String tipo;
    private Float precio;
    private String marca;
    private String modelo;
    private LocalDate fechaAdquisicion;
    
    public Equipo(Integer idEquipo, String nombre, String tipo, String modelo, Float precio, String marca, LocalDate fechaAdquisicion) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.modelo = modelo;
        this.precio = precio;
        this.marca = marca;
        this.fechaAdquisicion = fechaAdquisicion;
    }
    
    public Equipo(Integer idEquipo, Integer idSala, String nombre, String tipo, Float precio, String marca, String modelo, LocalDate fechaAdquisicion) {
        this.idEquipo = idEquipo;
        this.idSala = idSala;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }
    
    
}
