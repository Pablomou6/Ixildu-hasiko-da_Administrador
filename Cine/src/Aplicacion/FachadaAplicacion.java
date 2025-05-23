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

    GestorRestauracion gestRestauracion;
    GestorAnuncio gestAnuncio;
    GestorAnunciar gestAnunciar;
    GestorSesion gestSesion;
    GestorEquipo gestEquipo;
    GestorTrabajar gestTrabajar;
    GestorSalas gestSalas;

    
    //Constructor, donde creamos los objetos de las otras fachadas. Le pasamos esta fachada para que se comuniquen con esta, no entre la BD y la GUI
    FachadaAplicacion() {
        fachadaGUI = new FachadaGUI(this); 
        fachadaBD = new FachadaBaseDatos(this);
        gestUsuario = new GestorUsuario(fachadaGUI, fachadaBD);
        gestPelicula = new GestorPelicula(fachadaGUI, fachadaBD);

        gestTrabajador = new GestorTrabajador(fachadaGUI, fachadaBD);

        gestRestauracion = new GestorRestauracion(fachadaGUI, fachadaBD);
        gestAnuncio = new GestorAnuncio(fachadaGUI, fachadaBD);
        gestAnunciar = new GestorAnunciar(fachadaGUI, fachadaBD);
        gestSesion = new GestorSesion(fachadaGUI, fachadaBD);
        gestEquipo = new GestorEquipo(fachadaGUI, fachadaBD);
        gestTrabajar = new GestorTrabajar(fachadaGUI, fachadaBD);
        gestSalas = new GestorSalas(fachadaGUI, fachadaBD);

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
    
    public boolean eliminarComida(int idComida) {
        return gestRestauracion.eliminarComida(idComida);
    }
    
    public List<String> obtenerComidas() {
        return gestRestauracion.obtenerComidas();
    }
    
    public List<Integer> obtenerSalas() {
        return gestSalas.obtenerSalas();
    }

    public List<Trabajador> obtenerTrabajadoresSala(int idSala) {
        return gestTrabajar.obtenerTrabajadoresSala(idSala);
    }

    public List<Equipo> obtenerEquiposSala(int idSala) {
        return gestEquipo.obtenerEquiposSala(idSala);
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

    public boolean anadirEquipoSala(int idSala, String nombre, String tipo, String modelo, double precio, String marca) {
        return gestEquipo.anadirEquipoSala(idSala, nombre, tipo, modelo, precio, marca);
    }
    
    public Equipo obtenerEquipoPorId(int idEquipo) {
        return gestEquipo.obtenerEquipoPorId(idEquipo);
    }
    
    public boolean eliminarEquipoSala(int idEquipo) {
        return gestEquipo.eliminarEquipoSala(idEquipo);
    }
    
    public boolean editarEquipoSala(int idEquipo, int idSala, String nombre, String tipo, String modelo, double precio, String marca) {
        return gestEquipo.editarEquipoSala(idEquipo, idSala, nombre, tipo, modelo, precio, marca);
    }
    
    
    public ArrayList<Integer> recuperarIdsSalas() {
        return gestSalas.recuperarIdsSalas();
    }
    
    public Boolean anadirSesion(Integer idSala, String horaInicio, String fecha, Float precio, ArrayList<Anuncio> anunciosAsignados, Pelicula peliculaAnadir) {
        
        if(!gestSesion.anadirSesion(idSala, horaInicio, fecha, precio, anunciosAsignados, peliculaAnadir)) {
            return false;
        }
        
        return true;
    }
    
    public Boolean editarSesion(Integer idSesion, Integer idSala, String titulo, String fechaSesion, String horaInicio, Float precio) {
        if(!gestSesion.editarSesion(idSesion, idSala, titulo, fechaSesion, horaInicio, precio)) {
            return false;
        }
        return true;
    }
    
    public Boolean eliminarSesion(Sesion sesion) {
        if(!gestSesion.eliminarSesion(sesion)) {
            return false;
        }
        return true;
    }
    
    public String obtenerSalasYSesiones(Pelicula pelicula) {
       return gestSesion.obtenerSalasYSesiones(pelicula);
    }

}
