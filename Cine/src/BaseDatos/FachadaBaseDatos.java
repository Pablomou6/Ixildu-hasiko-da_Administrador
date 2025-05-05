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
import java.util.*;

/**
 *
 * @author alumnogreibd
 */
public class FachadaBaseDatos {
    FachadaAplicacion fachadaAp;
    Connection conexionBD;
    
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

        } catch (FileNotFoundException f) {
            System.out.println("Archivo de configuración no encontrado: " + f.getMessage());
            // fachadaAp.muestraExcepcion(f.getMessage()); // Descomentar si existe el método en fachadaAp
        } catch (IOException io) {
            System.out.println("Error de entrada/salida al leer el archivo de configuración: " + io.getMessage());
            // fachadaAp.muestraExcepcion(io.getMessage()); // Descomentar si existe el método en fachadaAp
        } catch (SQLException se) {
            System.out.println("Error al establecer la conexión con la base de datos: " + se.getMessage());
            // fachadaAp.muestraExcepcion(se.getMessage()); // Descomentar si existe el método en fachadaAp
        } catch (IllegalArgumentException ia) {
            System.out.println("Error en la configuración: " + ia.getMessage());
            // fachadaAp.muestraExcepcion(ia.getMessage()); // Descomentar si existe el método en fachadaAp
        }
        
        //Método para comprobar que esté conectada ben. FUNCIONA (borrar)
        this.obtenerUsuarios();
    }
     
     public List<String> obtenerUsuarios() {
    List<String> listaUsuarios = new ArrayList<>();

    try {
        // Creamos una sentencia SQL
        Statement st = conexionBD.createStatement();

        // Ejecutamos la consulta (ajusta el nombre de la tabla si es diferente)
        ResultSet rs = st.executeQuery("SELECT nombre FROM usuario");

        // Recorremos los resultados y añadimos a la lista
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            listaUsuarios.add(nombre);
        }

        // Cerramos los recursos
        rs.close();
        st.close();

    } catch (SQLException e) {
        System.out.println("Error al obtener los usuarios: " + e.getMessage());
    }
    
    for(String nombre : listaUsuarios) {
        System.out.println(nombre);
    }

    return listaUsuarios;
}
    
}
