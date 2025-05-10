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
    GestorRestauracion gestRestauracion;
    
    //Constructor, donde creamos los objetos de las otras fachadas. Le pasamos esta fachada para que se comuniquen con esta, no entre la BD y la GUI
    FachadaAplicacion() {
        fachadaGUI = new FachadaGUI(this); 
        fachadaBD = new FachadaBaseDatos(this);
        gestUsuario = new GestorUsuario(fachadaGUI, fachadaBD);
        gestPelicula = new GestorPelicula(fachadaGUI, fachadaBD);
        gestRestauracion = new GestorRestauracion(fachadaGUI, fachadaBD);
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
    
    public void eliminarPelicula(Pelicula p) {
        try {
            gestPelicula.eliminarPelicula(p);
        } catch (Exception e) {
            muestraExcepcion("Error al eliminar la película: " + e.getMessage());
        }
    }

    public boolean insertarComida(String nombre, double precio, String tamano, int stock, String descripcion) {
        
        return gestRestauracion.insertarComida(nombre, precio, tamano, stock, descripcion);
        
    }

    
}
