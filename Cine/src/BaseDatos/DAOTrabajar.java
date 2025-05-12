/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import Aplicacion.FachadaAplicacion;
import Aplicacion.Trabajador;
import java.sql.*;
import java.util.*;

/**
 *
 * @author alumnogreibd
 */
public class DAOTrabajar extends AbstractDAO {
    
    public DAOTrabajar(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public List<Trabajador> obtenerTrabajadoresSala(int idSala) {
        List<Trabajador> resultado = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();
            String consulta = 
                "SELECT t.dni, t.nombre, t.cargo, t.sueldo " +
                "FROM TrabajadorLimpieza t " +
                "JOIN TrabajarLimpieza tl ON t.dni = tl.dni " +
                "WHERE tl.idSala = ? " +
                "UNION " +
                "SELECT t.dni, t.nombre, t.cargo, t.sueldo " +
                "FROM TrabajadorProyeccion t " +
                "JOIN TrabajarProyeccion tp ON t.dni = tp.dni " +
                "WHERE tp.idSala = ?";
            stm = con.prepareStatement(consulta);
            stm.setInt(1, idSala); // Usar setInt en lugar de setString
            stm.setInt(2, idSala);
            rs = stm.executeQuery();

            while (rs.next()) {
                Trabajador t = new Trabajador(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("cargo"),
                    rs.getDouble("sueldo")
                );
                resultado.add(t);
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
