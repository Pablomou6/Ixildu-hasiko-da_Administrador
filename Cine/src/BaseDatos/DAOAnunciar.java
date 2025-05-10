/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import Aplicacion.*;
import Aplicacion.FachadaAplicacion;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author alumnogreibd
 */
public class DAOAnunciar extends AbstractDAO {
    public DAOAnunciar(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public ArrayList<Integer> obtenerIdAnunciosSesion(Sesion sesionEditar) {
        ArrayList<Integer> idAnuncios = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion(); // Asume que este método devuelve una conexión válida

            String consulta = "SELECT idAnuncio FROM anunciar WHERE idSesion = ?";
            stm = con.prepareStatement(consulta);
            stm.setInt(1, sesionEditar.getIdSesion());

            rs = stm.executeQuery();

            while (rs.next()) {
                idAnuncios.add(rs.getInt("idAnuncio"));
            }

        } 
        catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion("Error al obtener anuncios de la sesión: " + e.getMessage());
        } 
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { System.out.println("No se pudo cerrar ResultSet."); }
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("No se pudo cerrar PreparedStatement."); }
        }

        return idAnuncios;
    }
}
