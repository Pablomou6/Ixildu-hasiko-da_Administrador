/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

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
