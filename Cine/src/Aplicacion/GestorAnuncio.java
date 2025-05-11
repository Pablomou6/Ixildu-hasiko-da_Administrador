/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

import BaseDatos.FachadaBaseDatos;
import GUI.FachadaGUI;
import java.util.ArrayList;

/**
 *
 * @author alumnogreibd
 */
public class GestorAnuncio {
    FachadaGUI fachadaGUI;
    FachadaBaseDatos fachadaBD;
    
    public GestorAnuncio(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fachadaGUI = fgui;
        this.fachadaBD = fbd;
    }
    
    public ArrayList<Anuncio> obtenerAnuncios() {
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        
        anuncios = fachadaBD.obtenerAnuncios();
        
        return anuncios;
    }
    
    public ArrayList<Anuncio> obtenerAnunciosConId(ArrayList<Integer> idAnuncio) {
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        
        anuncios = fachadaBD.obtenerAnunciosConId(idAnuncio);
        
        return anuncios;
    }
    
}
