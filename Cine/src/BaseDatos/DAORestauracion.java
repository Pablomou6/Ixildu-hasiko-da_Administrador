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
    
    public boolean eliminarComida(int idComida) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = this.getConexion();

            // Verificar si la comida está asignada a un pedido
            String consultaVerificacion = "SELECT COUNT(*) FROM pedir WHERE idcomida = ?";
            stm = con.prepareStatement(consultaVerificacion);
            stm.setInt(1, idComida);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int asignaciones = rs.getInt(1);
            rs.close();
            stm.close();

            if (asignaciones > 0) {
                // Si la comida está asignada a un pedido, no se puede eliminar
                this.getFachadaAplicacion().muestraExcepcion("No se puede eliminar la comida porque está asignada a un pedido.");
                return false;
            }

            // Eliminar la comida
            String consultaEliminacion = "DELETE FROM comida WHERE idcomida = ?";
            stm = con.prepareStatement(consultaEliminacion);
            stm.setInt(1, idComida);
            stm.executeUpdate();

            return true; // Eliminación exitosa
        } catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            return false; // Error en la eliminación
        } finally {
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("No se ha podido cerrar el PreparedStatement."); }
        }
    }
    
    public List<String> obtenerComidas() {
        List<String> resultado = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();
            String consulta = "SELECT idcomida, nombre, precio FROM comida";
            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()) {
                // Formato: "ID - Nombre - Precio"
                String comida = rs.getInt("idcomida") + " - " + rs.getString("nombre") + " - " + rs.getDouble("precio") + "€";
                resultado.add(comida);
            }
        } catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { System.out.println("No se ha podido cerrar el ResultSet."); }
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("No se ha podido cerrar el PreparedStatement."); }
        }

        return resultado;
    }
    
}
