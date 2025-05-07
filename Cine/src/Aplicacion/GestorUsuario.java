/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;
import BaseDatos.*;
import GUI.*;

/**
 *
 * @author alumnogreibd
 */
public class GestorUsuario {
    FachadaGUI fachadaGUI;
    FachadaBaseDatos fachadaBD;
    
    public GestorUsuario(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fachadaGUI = fgui;
        this.fachadaBD = fbd;
    }
    
    public Usuario comprobarIdUsuarioAutenticacion(String idUsuario) {
        Usuario user = null;
        
        user = fachadaBD.comprobarIdUsuarioAutenticacion(idUsuario);
        
        return user;
    }
}
