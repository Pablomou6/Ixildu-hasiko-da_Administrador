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
    
    public List<Equipo> obtenerEquiposSala(int idSala) {
        return fachadaBD.obtenerEquiposSala(idSala);
    }
    
    public boolean anadirEquipoSala(int idSala, String nombre, String tipo, String modelo, double precio, String marca) {
        return fachadaBD.anadirEquipoSala(idSala, nombre, tipo, modelo, precio, marca);
    }
    
    public Equipo obtenerEquipoPorId(int idEquipo) {
        return fachadaBD.obtenerEquipoPorId(idEquipo);
    }
    
}
