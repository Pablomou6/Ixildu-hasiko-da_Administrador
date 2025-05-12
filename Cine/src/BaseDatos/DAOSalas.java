/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import Aplicacion.FachadaAplicacion;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author alumnogreibd
 */
public class DAOSalas extends AbstractDAO {
    public DAOSalas(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
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
