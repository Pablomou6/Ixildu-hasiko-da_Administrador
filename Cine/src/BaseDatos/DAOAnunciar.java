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
    
    public Boolean actualizarAnunciosSesion(ArrayList<Anuncio> anunciosIntroducir, ArrayList<Anuncio> anunciosEliminar, Sesion sesionEditar) {
        Connection conn = null;
        PreparedStatement psEliminar = null;
        PreparedStatement psInsertar = null;
        conn = this.getConexion();

        try {
            conn.setAutoCommit(false);  

            String deleteSQL = "DELETE FROM anunciar WHERE idsesion = ? AND idanuncio = ?";
            psEliminar = conn.prepareStatement(deleteSQL);
            //Este bucle, si el array está vacío, ya no se ejecutará
            for (Anuncio anuncio : anunciosEliminar) {
                psEliminar.setInt(1, sesionEditar.getIdSesion());
                psEliminar.setInt(2, anuncio.getIdAnuncio());
                //Añadimos la instrucción al lote, para ejecutarlas todas juntas
                psEliminar.addBatch();
            }
            //Ejecutamos el lote
            psEliminar.executeBatch();

            String insertSQL = "INSERT INTO anunciar (idsesion, idanuncio) VALUES (?, ?)";
            psInsertar = conn.prepareStatement(insertSQL);
            //Este bucle, si el array está vacío, ya no se ejecutará
            for (Anuncio anuncio : anunciosIntroducir) {
                psInsertar.setInt(1, sesionEditar.getIdSesion());
                psInsertar.setInt(2, anuncio.getIdAnuncio());
                psInsertar.addBatch();
            }
            psInsertar.executeBatch();

            conn.commit();  //Confirma la transacción

        } 
        catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getNextException().getMessage());
            if (conn != null) {
                try {
                    conn.rollback();  //Nos aseguramos de revertir la transacción en caso de error
                } catch (SQLException ex) {
                    this.getFachadaAplicacion().muestraExcepcion("No se pudo deshacer la transacción.");
                }
            }
            return false;
        } 
        finally {
            try { if (psEliminar != null) psEliminar.close(); } catch (Exception e) { System.out.println("No se pudo cerrar el primer PreparedStatement."); }
            try { if (psInsertar != null) psInsertar.close(); } catch (Exception e) { System.out.println("No se pudo cerrar PreparedStatement."); }
            try { conn.setAutoCommit(true); } catch (SQLException e) { this.getFachadaAplicacion().muestraExcepcion("No se pudo reestablecer el autocommit."); }
        }
        return true;
    }
   
   public ArrayList<Integer> recuperarAnunciosIdSesion(Integer idSesion) {
       ArrayList<Integer> idAnuncios = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion(); // Asume que este método devuelve una conexión válida

            String consulta = "SELECT idAnuncio FROM anunciar WHERE idSesion = ?";
            stm = con.prepareStatement(consulta);
            stm.setInt(1, idSesion);

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
