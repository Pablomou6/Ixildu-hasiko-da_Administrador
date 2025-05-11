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
    
    public List<String> obtenerSalas() {
        List<String> resultado = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();
            String consulta = "SELECT idsala FROM sala";
            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()) {
                resultado.add(rs.getString("idSala"));
            }
        } catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { }
            try { if (stm != null) stm.close(); } catch (Exception e) { }
        }

        return resultado;
    }
}
