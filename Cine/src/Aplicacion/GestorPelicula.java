/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

import BaseDatos.FachadaBaseDatos;
import GUI.FachadaGUI;

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
    
}
