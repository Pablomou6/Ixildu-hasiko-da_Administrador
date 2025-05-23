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
public class GestorRestauracion {
    
    FachadaGUI fachadaGUI;
    FachadaBaseDatos fachadaBD;
    
    public GestorRestauracion(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fachadaGUI = fgui;
        this.fachadaBD = fbd;
    }

    boolean insertarComida(String nombre, double precio, String tamano, int stock, String descripcion) {
        return fachadaBD.insertarComida(nombre, precio, tamano, stock, descripcion);
    }
    
    public boolean eliminarComida(int idComida) {
        return fachadaBD.eliminarComida(idComida);
    }
    
    public List<String> obtenerComidas() {
       return fachadaBD.obtenerComidas();
    }
    
}
