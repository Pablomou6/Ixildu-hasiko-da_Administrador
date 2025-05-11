/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Aplicacion.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author alumnogreibd
 */
public class FachadaBaseDatos {
    FachadaAplicacion fachadaAp;
    Connection conexionBD;
    DAOUsuarios daoUsuarios;
    DAOPeliculas daoPeliculas;
    DAORestauracion daoRestauracion;
    DAOInstalaciones daoInstalaciones;
    DAOAnuncios daoAnuncios;
    DAOAnunciar daoAnunciar;
    DAOSesiones daoSesiones;
    
    public FachadaBaseDatos(FachadaAplicacion fa) {
        this.fachadaAp = fa; // Inicializamos la referencia a la fachada de aplicación
        Properties config = new Properties(); // Objeto para cargar las propiedades del archivo
        FileInputStream docConfig; // Stream para leer el archivo de configuración

        try {
            // Cargamos el archivo de configuración Cine.properties
            docConfig = new FileInputStream("Cine.properties");
            config.load(docConfig); // Leemos las propiedades del archivo
            docConfig.close(); // Cerramos el archivo después de leerlo

            // Leemos las propiedades necesarias para la conexión
            String gestor = config.getProperty("gestor"); // Gestor de base de datos 
            String servidor = config.getProperty("servidor"); // Dirección del servidor 
            String puerto = config.getProperty("puerto"); // Puerto del gestor de base de datos 
            String baseDatos = config.getProperty("baseDatos"); // Nombre de la base de datos
            String usuario = config.getProperty("usuario"); // Usuario de la base de datos
            String clave = config.getProperty("clave"); // Contraseña del usuario

            // Verificamos que el gestor configurado sea "postgresql"
            if (!"postgresql".equalsIgnoreCase(gestor)) {
                throw new IllegalArgumentException("El gestor configurado no es compatible: " + gestor);
            }

            // Construimos la URL de conexión para PostgreSQL
            String urlConexion = "jdbc:postgresql://" + servidor + ":" + puerto + "/" + baseDatos;

            // Establecemos la conexión a la base de datos
            this.conexionBD = DriverManager.getConnection(urlConexion, usuario, clave);
            System.out.println("Conexión establecida con éxito a la base de datos PostgreSQL.");
            
            daoUsuarios = new DAOUsuarios(conexionBD, fachadaAp);
            daoPeliculas = new DAOPeliculas(conexionBD, fachadaAp);
            daoRestauracion = new DAORestauracion(conexionBD, fachadaAp);
            daoInstalaciones = new DAOInstalaciones(conexionBD, fachadaAp);
            daoAnuncios = new DAOAnuncios(conexionBD, fachadaAp);
            daoAnunciar = new DAOAnunciar(conexionBD, fachadaAp);
            daoSesiones = new DAOSesiones(conexionBD, fa);

        } catch (FileNotFoundException f) {
            System.out.println("Archivo de configuración no encontrado: " + f.getMessage());
            fachadaAp.muestraExcepcion(f.getMessage()); 
        } catch (IOException io) {
            System.out.println("Error de entrada/salida al leer el archivo de configuración: " + io.getMessage());
            fachadaAp.muestraExcepcion(io.getMessage()); 
        } catch (SQLException se) {
            System.out.println("Error al establecer la conexión con la base de datos: " + se.getMessage());
            fachadaAp.muestraExcepcion(se.getMessage());
        } catch (IllegalArgumentException ia) {
            System.out.println("Error en la configuración: " + ia.getMessage());
            fachadaAp.muestraExcepcion(ia.getMessage()); 
        } 
    }
     
    public Usuario comprobarIdUsuarioAutenticacion(String idUsuario) {
        Usuario user = null;

        user = daoUsuarios.comprobarIdUsuarioAutenticacion(idUsuario);

        return user;
    }
    
    public List<Pelicula> buscarPeliculas(String titulo, String duracion, String genero, String sinopsis, String clasificacion, 
        String idioma, LocalDate fechaEstreno, String duracionTrailer) {
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        
        //Llamamos al gestor de las peliculas
        peliculas = daoPeliculas.buscarPeliculas(titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaEstreno, duracionTrailer);
        
        return peliculas;
    }
    
    public void eliminarPelicula(Pelicula p) {
        daoPeliculas.eliminarPelicula(p);
    }

    public boolean insertarComida(String nombre, double precio, String tamano, int stock, String descripcion) {
        return daoRestauracion.insertarComida(nombre,precio,tamano,stock,descripcion);
    }
    
    public boolean eliminarComida(int idComida) {
        return daoRestauracion.eliminarComida(idComida);
    }
    
    public List<String> obtenerComidas() {
        return daoRestauracion.obtenerComidas();
    }
    
    public List<String> obtenerSalas() {
        return daoInstalaciones.obtenerSalas();
    }

    public List<Trabajador> obtenerTrabajadoresSala(String idSala) {
        return daoInstalaciones.obtenerTrabajadoresSala(idSala);
    }

    public List<Equipo> obtenerEquiposSala(String idSala) {
        return daoInstalaciones.obtenerEquiposSala(idSala);
    }

    public Boolean editarPelicula(Pelicula peliculaEditar) {
        //La pelicula se pasa una vez comprobada en el gestor. Va directa al DAO
        if(!daoPeliculas.editarPelicula(peliculaEditar)) { return false; }
        
        return true;
    }
    
    public ArrayList<Anuncio> obtenerAnuncios() {
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        
        anuncios = daoAnuncios.obtenerAnuncios();
        
        return anuncios;
    }
    
    public ArrayList<Anuncio> obtenerAnunciosConId(ArrayList<Integer> idAnuncio) {
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        
        anuncios = daoAnuncios.obtenerAnunciosConId(idAnuncio);
        
        return anuncios;
    }
    
    public ArrayList<Integer> obtenerIdAnunciosSesion(Sesion sesionEditar) {
        ArrayList<Integer> idAnuncios = new ArrayList<>();
        
        idAnuncios = daoAnunciar.obtenerIdAnunciosSesion(sesionEditar);
        
        return idAnuncios;
    } 
    
    public ArrayList<Sesion> obtenerSesiones() {
        ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
        
        sesiones = daoSesiones.obtenerSesiones();
        
        return sesiones;
    }
    
    public Boolean actualizarAnunciosSesion(ArrayList<Anuncio> anunciosIntroducir, ArrayList<Anuncio> anunciosEliminar, Sesion sesionEditar) {
        if(!daoAnunciar.actualizarAnunciosSesion(anunciosIntroducir, anunciosEliminar, sesionEditar)) {
            return false;
        } 
        
        return true;
    }
}
