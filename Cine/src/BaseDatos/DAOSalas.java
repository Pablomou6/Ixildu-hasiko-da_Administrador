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
public class DAOSalas extends AbstractDAO {

    public DAOSalas(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public List<Integer> obtenerSalas() {
        List<Integer> resultado = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();
            String consulta = "SELECT idsala FROM sala";
            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()) {
                resultado.add(rs.getInt("idSala"));
            }
        } catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { }
            try { if (stm != null) stm.close(); } catch (Exception e) { }
        }

        return resultado;
    }
  
    public ArrayList<Integer> recuperarIdsSalas() {
    ArrayList<Integer> idsSalas = new ArrayList<>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        conn = this.getConexion();
        String consulta = "SELECT idSala FROM sala";
        ps = conn.prepareStatement(consulta);
        rs = ps.executeQuery();

        while (rs.next()) {
            idsSalas.add(rs.getInt("idSala"));
        }

    } 
    catch (SQLException e) {
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    } 
    finally {
        try { if (rs != null) rs.close(); } catch (Exception e) { System.out.println("No se ha podido cerrar el ResultSet."); }
        try { if (ps != null) ps.close(); } catch (Exception e) { System.out.println("No se ha podido cerrar el ResultSet."); }
    }

    return idsSalas;
}

   
}
