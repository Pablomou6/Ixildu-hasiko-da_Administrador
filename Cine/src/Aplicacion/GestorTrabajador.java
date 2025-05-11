/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

import BaseDatos.FachadaBaseDatos;
import GUI.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author alumnogreibd
 */
public class GestorTrabajador {
    FachadaGUI fachadaGUI;
    FachadaBaseDatos fachadaBD;
    
    public GestorTrabajador(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fachadaGUI = fgui;
        this.fachadaBD = fbd;
    }
    
    public List<Pelicula> buscarTrabajadores(String titulo, String duracion, String genero, String sinopsis, String clasificacion, 
        String idioma, String fechaEstreno, String duracionTrailer) {
        
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        
        //Llamamos al gestor de las peliculas
        //peliculas = fachadaBD.buscarTrabajadores(titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaEstreno, duracionTrailer);
        
        return peliculas;
    }
    
    //Función para comprobar los atributos de una película antes de crear el objeto e introducirlo en la BD
    public boolean validarCampos(String nombre, String ap1, String ap2, String dni, String cargo,Float sueldo) {
        //Comprobamos que los campos no se proporcionasen vacíos
        if (nombre == null || nombre.isEmpty() ||
            ap1 == null || ap1.isEmpty() ||
            ap2 == null || ap2.isEmpty() ||
            dni == null || dni.isEmpty() ||
            cargo == null || cargo.isEmpty() ||
            sueldo == null || sueldo.toString().isEmpty()) {
            return false;
        }
        return true;
    }
    
    private void alerta(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public boolean insertarTrabajador(Trabajador trab, String tipo, ArrayList<Integer> salasSelec){        
        if(!fachadaBD.insertarTrabajador(trab, tipo, salasSelec)){
            return false;
        }
        return true;
    }

    public List<Trabajador> obtenerTrabajador(String nombre, String dni) {
        return fachadaBD.obtenerTrabajador(nombre,dni);
    }

    public String obtenerTipoTrabajador(String dni) {
        return fachadaBD.obtenerTipoTrabajador(dni);
    }

    public List<Integer> obtenerSalasTrabajador(String dni) {
        return fachadaBD.obtenerSalasTrabajador(dni);
    }
    
}
