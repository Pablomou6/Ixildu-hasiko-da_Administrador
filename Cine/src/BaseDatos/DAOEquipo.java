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
public class DAOEquipo extends AbstractDAO {
    
    public DAOEquipo(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public List<Equipo> obtenerEquiposSala(int idSala) {
        List<Equipo> resultado = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();
            String consulta = 
                "SELECT idEquipo, nombre, tipo, modelo, precio, marca, fechaAdquisicion " +
                "FROM Equipo " +
                "WHERE idSala = ?";
            stm = con.prepareStatement(consulta);
            stm.setInt(1, idSala); // Usar setInt para pasar el idSala como entero
            rs = stm.executeQuery();

            while (rs.next()) {
                Equipo equipo = new Equipo(
                    rs.getInt("idEquipo"),
                    rs.getString("nombre"),
                    rs.getString("tipo"),
                    rs.getString("modelo"),
                    rs.getFloat("precio"),
                    rs.getString("marca"),
                    rs.getDate("fechaAdquisicion").toLocalDate()
                );
                resultado.add(equipo);
            }
        } catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { }
            try { if (stm != null) stm.close(); } catch (Exception e) { }
        }

        return resultado;
    }
    
    public boolean anadirEquipoSala(int idSala, String nombre, String tipo, String modelo, double precio, String marca) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = this.getConexion();
            String consulta = 
                "INSERT INTO Equipo (idSala, nombre, tipo, modelo, precio, marca, fechaAdquisicion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(consulta);
            stm.setInt(1, idSala);
            stm.setString(2, nombre);
            stm.setString(3, tipo);
            stm.setString(4, modelo);
            stm.setDouble(5, precio);
            stm.setString(6, marca);
            stm.setDate(7, java.sql.Date.valueOf(java.time.LocalDate.now())); // Fecha actual
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            return false;
        } finally {
            try { if (stm != null) stm.close(); } catch (Exception e) { }
        }
    }
    
}
