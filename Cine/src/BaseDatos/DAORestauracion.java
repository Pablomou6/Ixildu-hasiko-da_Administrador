/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import Aplicacion.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author alumnogreibd
 */
public class DAORestauracion extends AbstractDAO{
    
    public DAORestauracion(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    boolean insertarComida(String nombre, double precio, String tamano, int stock, String descripcion) {
        
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = this.getConexion();
            String consulta = "INSERT INTO comida (nombre, precio, tamaño, stockdisponible, descripcion) VALUES (?, ?, ?, ?, ?)";
            stm = con.prepareStatement(consulta);

            // Asignar parámetros
            stm.setString(1, nombre);
            stm.setDouble(2, precio);
            stm.setString(3, tamano);
            stm.setInt(4, stock);
            stm.setString(5, descripcion);

            // Ejecutar la consulta
            stm.executeUpdate();
            return true; // Inserción exitosa
        } catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            return false; // Error en la inserción
        } finally {
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("No se ha podido cerrar el PreparedStatement."); }
        }
        
    }
    
}
