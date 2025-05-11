/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

import BaseDatos.FachadaBaseDatos;
import GUI.FachadaGUI;
import java.util.List;
/**
 *
 * @author alumnogreibd
 */
public class GestorEquipo {
    
    FachadaGUI fachadaGUI;
    FachadaBaseDatos fachadaBD;
    
    public GestorEquipo(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fachadaGUI = fgui;
        this.fachadaBD = fbd;
    }
    
    public List<Equipo> obtenerEquiposSala(String idSala) {
        return fachadaBD.obtenerEquiposSala(idSala);
    }
    
}
