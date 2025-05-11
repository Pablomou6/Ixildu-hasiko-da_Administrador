/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import Aplicacion.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author alumnogreibd
 */
public class DAOSesiones extends AbstractDAO {
    
    public DAOSesiones(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public ArrayList<Sesion> obtenerSesiones() {
        ArrayList<Sesion> resultado = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();

            String consulta = "SELECT idSesion, idSala, titulo, fechaSesion, horaInicio, precio " +
                              "FROM sesion " +
                              "WHERE fechaSesion >= CURRENT_DATE " +
                              "ORDER BY fechaSesion, horaInicio";

            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()) {
                Sesion sesion = new Sesion(
                    rs.getInt("idSesion"),
                    rs.getInt("idSala"),
                    rs.getString("titulo"),
                    rs.getDate("fechaSesion").toLocalDate().format( DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    rs.getTime("horaInicio").toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                    rs.getBigDecimal("precio").floatValue()
                );
                resultado.add(sesion);
            }

        } catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stm != null) stm.close(); } catch (Exception e) {}
        }

        return resultado;
    }

}
