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
    GestorAnuncio gestAnuncio;
    GestorAnunciar gestAnunciar;
    GestorSesion gestSesion;
    
    //Constructor, donde creamos los objetos de las otras fachadas. Le pasamos esta fachada para que se comuniquen con esta, no entre la BD y la GUI
    FachadaAplicacion() {
        fachadaGUI = new FachadaGUI(this); 
        fachadaBD = new FachadaBaseDatos(this);
        gestUsuario = new GestorUsuario(fachadaGUI, fachadaBD);
        gestPelicula = new GestorPelicula(fachadaGUI, fachadaBD);
        gestAnuncio = new GestorAnuncio(fachadaGUI, fachadaBD);
        gestAnunciar = new GestorAnunciar(fachadaGUI, fachadaBD);
        gestSesion = new GestorSesion(fachadaGUI, fachadaBD);
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
    
    public Boolean editarPelicula(String titulo, String duracion, String genero, String sinopsis, String clasificacion, 
        String idioma, String fechaEstreno, String duracionTrailer) {
        
        //Llamammos al gestor de películas para actualizar la película
        if(!gestPelicula.editarPelicula(titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaEstreno, duracionTrailer)) {
            return false;
        }
        
        return true;
    }
    
    public ArrayList<Anuncio> obtenerAnuncios() {
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        
        anuncios = gestAnuncio.obtenerAnuncios();
        
        return anuncios;
    }
    
    public ArrayList<Anuncio> obtenerAnunciosSesion(Sesion sesionEditar) {
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        ArrayList<Integer> idAnuncios = new ArrayList<>();
        
        idAnuncios = gestAnunciar.obtenerIdAnunciosSesion(sesionEditar);
        
        if(idAnuncios.isEmpty()) {
            //si no tiene anuncios asociados, se devuelve el array anuncios vacío directamente
            return anuncios;
        }
        
        anuncios = gestAnuncio.obtenerAnunciosConId(idAnuncios);
        
        return anuncios;
    }
    
    public ArrayList<Sesion> obtenerSesiones() {
        ArrayList<Sesion> sesionesActuales = new ArrayList<Sesion>();
        
        sesionesActuales = gestSesion.obtenerSesiones();
        
        return sesionesActuales;
    }
    
    public Boolean actualizarAnunciosSesion(ArrayList<Anuncio> anunciosIntroducir, ArrayList<Anuncio> anunciosBorrar, Sesion sesionEditar) {
        if(!gestAnunciar.actualizarAnunciosSesion(anunciosIntroducir, anunciosBorrar, sesionEditar)) {
            return false;
        }
        
        return true;
    }
}
