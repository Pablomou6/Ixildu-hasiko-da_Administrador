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
public class GestorSala {
     FachadaGUI fachadaGUI;
    FachadaBaseDatos fachadaBD;
    
    public GestorSala(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fachadaGUI = fgui;
        this.fachadaBD = fbd;
    }
    
    
}
