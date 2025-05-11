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
    
    public List<Equipo> obtenerEquiposSala(String idSala) {
        List<Equipo> resultado = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();
            String consulta = "SELECT * FROM equipo WHERE idsala = ?";
            stm = con.prepareStatement(consulta);
            stm.setString(1, idSala);
            rs = stm.executeQuery();

            while (rs.next()) {
                Equipo e = new Equipo(rs.getInt("idEquipo"), rs.getString("nombre"), rs.getString("tipo"), rs.getString("modelo"));
                resultado.add(e);
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
