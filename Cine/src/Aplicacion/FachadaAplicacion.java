/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;
import GUI.*;
import BaseDatos.*;
import java.time.LocalDate;
import org.mindrot.jbcrypt.BCrypt;
import java.util.*;

/**
 *
 * @author alumnogreibd
 */
public class FachadaAplicacion {
    //Atributos de la fachada de la aplicación
    FachadaGUI fachadaGUI;
    FachadaBaseDatos fachadaBD;
    GestorUsuario gestUsuario;
    GestorPelicula gestPelicula;
    GestorTrabajador gestTrabajador;
    
    //Constructor, donde creamos los objetos de las otras fachadas. Le pasamos esta fachada para que se comuniquen con esta, no entre la BD y la GUI
    FachadaAplicacion() {
        fachadaGUI = new FachadaGUI(this); 
        fachadaBD = new FachadaBaseDatos(this);
        gestUsuario = new GestorUsuario(fachadaGUI, fachadaBD);
        gestPelicula = new GestorPelicula(fachadaGUI, fachadaBD);
        gestTrabajador = new GestorTrabajador(fachadaGUI, fachadaBD);
    }
    
    //Método main, en el que creamos la fachada de aplicación e iniciamos el programa
    public static void main(String[] args) {
        FachadaAplicacion fachadaAp;
 
        fachadaAp = new FachadaAplicacion();
        fachadaAp.iniciar();
    }
    
    //Al iniciar el programa, llamamos a la fachada de la GUI, para que inicie las ventanas
    public void iniciar() {
        fachadaGUI.iniciarVentanas();
    }
    
    //Método para mostrar las diferentes excepciones
    public void muestraExcepcion(String e){
     fachadaGUI.muestraExcepcion(e);
    }
    
    //Método para comprobar un usuario por el Id (para la autenticación)
    public Usuario comprobarIdUsuarioAutenticacion(String idUsuario) {
        Usuario user = null;
        
        user = gestUsuario.comprobarIdUsuarioAutenticacion(idUsuario);
        
        return user;
    }
    
    //Método para buscar las películas en la VPrincipal
    public List<Pelicula> buscarPeliculas(String titulo, String duracion, String genero, String sinopsis, String clasificacion, 
        String idioma, LocalDate fechaEstreno, String duracionTrailer) {
        
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        
        //Llamamos al gestor de las peliculas
        peliculas = gestPelicula.buscarPeliculas(titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaEstreno, duracionTrailer);
        
        return peliculas;
    }
    
    public Boolean anadirPelicula(String titulo, String duracion, String genero, String sinopsis, String clasificacion, 
        String idioma, String fechaEstreno, String duracionTrailer) {
        
        //Llamamos al gestor de las películas, donde comprobará el formato y creará el objeto
        if(!gestPelicula.anadirPelicula(titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaEstreno, duracionTrailer)) { return false; }
        
        return true;
    }
    
    public void insertarTrabajador(Trabajador trab, String tipo, ArrayList<Integer> salasSelec){
        gestTrabajador.insertarTrabajador(trab,tipo,salasSelec);
    }

    public List<Trabajador> obtenerTrabajador(String nombre, String dni) {
        return gestTrabajador.obtenerTrabajador(nombre,dni);
    }

    public String obtenerTipoTrabajador(String dni) {
        return gestTrabajador.obtenerTipoTrabajador(dni);
    }

    public List<Integer> obtenerSalasTrabajador(String dni) {
        return gestTrabajador.obtenerSalasTrabajador(dni);
    }

    public boolean actualizarTrabajador(Trabajador trab, String tipo, ArrayList<Integer> salasSeleccionadas, ArrayList<Integer> salasNoSeleccionadas) {
        return gestTrabajador.actualizarTrabajador(trab,tipo,salasSeleccionadas,salasNoSeleccionadas);
    }

    public boolean eliminarTrabajador(String dni, String tipo) {
        return gestTrabajador.eliminarTrabajador(dni,tipo);
    }

    
    
}
