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
public class GestorAnunciar {
    FachadaGUI fachadaGUI;
    FachadaBaseDatos fachadaBD;
    
    public GestorAnunciar(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fachadaGUI = fgui;
        this.fachadaBD = fbd;
    }
    
    public ArrayList<Integer> obtenerIdAnunciosSesion(Sesion sesionEditar) {
        ArrayList<Integer> idAnuncios = new ArrayList<>();
        
        idAnuncios = fachadaBD.obtenerIdAnunciosSesion(sesionEditar);
        
        return idAnuncios;
    }
}
