/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;
import GUI.*;
import BaseDatos.*;

/**
 *
 * @author alumnogreibd
 */
public class FachadaAplicacion {
    FachadaGUI fachadaGUI;
    FachadaBaseDatos fachadaBD;
    
    FachadaAplicacion() {
        fachadaGUI = new FachadaGUI(this); 
        fachadaBD = new FachadaBaseDatos(this);
    }
    
    public static void main(String[] args) {
        FachadaAplicacion fachadaAp;
        
        fachadaAp = new FachadaAplicacion();
        //fachadaAp.iniciar();
    }
    
}
