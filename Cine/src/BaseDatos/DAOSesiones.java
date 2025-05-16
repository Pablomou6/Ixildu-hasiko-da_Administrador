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

        } 
        catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } 
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { System.out.println("Error al cerrar el ResultSet."); }
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("Error al cerrar el PreparedStatement"); }
        }

        return resultado;
    }
    
    public ArrayList<Sesion> recuperarSesionesSalaFecha(Integer idSala, String fecha) {
        ArrayList<Sesion> sesiones = new ArrayList<>();

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();

            String consulta = "SELECT idsesion, idsala, titulo, fechasesion, horainicio, precio FROM sesion WHERE idsala = ? AND fechasesion = ?";
            stm = con.prepareStatement(consulta);
            stm.setInt(1, idSala);
            stm.setDate(2, java.sql.Date.valueOf(fecha)); //fecha en formato yyyy-MM-dd

            rs = stm.executeQuery();

            while (rs.next()) {
                Sesion sesion = new Sesion(
                    rs.getInt("idSesion"),
                    rs.getInt("idSala"),
                    rs.getString("titulo"),
                    rs.getDate("fechasesion").toString(),
                    rs.getString("horaInicio"),
                    rs.getFloat("precio")
                );

                sesiones.add(sesion);
            }

        } 
        catch (SQLException e) {
            System.out.println("Error al recuperar sesiones por sala y fecha: " + e.getMessage());
        } 
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { System.out.println("Error al cerrar el ResultSet."); }
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("Error al cerrar el PreparedStatement"); }
        }

        return sesiones;
    }
    
    
    public Boolean anadirSesion(Sesion sesionAnadir, ArrayList<Anuncio> anunciosAsignados) {
        Connection con = null;
        PreparedStatement stmSesion = null;
        PreparedStatement stmAnuncio = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();
            con.setAutoCommit(false); // Iniciamos transacción

            // Usamos RETURNING para obtener el idSesion generado
            String consultaSesion = "INSERT INTO sesion (idSala, titulo, fechaSesion, horaInicio, precio) " +
                                    "VALUES (?, ?, ?, ?, ?) RETURNING idSesion";

            stmSesion = con.prepareStatement(consultaSesion);
            stmSesion.setInt(1, sesionAnadir.getIdSala());
            stmSesion.setString(2, sesionAnadir.getTitulo());
            stmSesion.setDate(3, java.sql.Date.valueOf(sesionAnadir.getFechaSesion()));
            stmSesion.setTime(4, java.sql.Time.valueOf(sesionAnadir.getHoraInicio()));
            stmSesion.setFloat(5, sesionAnadir.getPrecio());

            rs = stmSesion.executeQuery();

            Integer idSesionGenerado = null;
            if (rs.next()) {
                idSesionGenerado = rs.getInt(1);
            } else {
                throw new SQLException("No se devolvió el idSesion tras insertar la sesión.");
            }

            //Insertar los anuncios
            String consultaAnuncio = "INSERT INTO anunciar (idSesion, idAnuncio) VALUES (?, ?)";
            stmAnuncio = con.prepareStatement(consultaAnuncio);

            for (Anuncio anuncio : anunciosAsignados) {
                stmAnuncio.setInt(1, idSesionGenerado);
                stmAnuncio.setInt(2, anuncio.getIdAnuncio());
                stmAnuncio.executeUpdate();
            }

            con.commit(); //Todo correcto
        } 
        catch (SQLException e) {
            try { if (con != null) con.rollback(); } catch (SQLException ex) {}
            this.getFachadaAplicacion().muestraExcepcion("Error al insertar sesión y anuncios: " + e.getMessage());
            return false;
        } 
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { System.out.println("Error al intentar cerrar el ResultSet."); }
            try { if (stmSesion != null) stmSesion.close(); } catch (Exception e) { System.out.println("Error al intentar cerrar el PreparedStatement de sesion."); }
            try { if (stmAnuncio != null) stmAnuncio.close(); } catch (Exception e) { System.out.println("Error al intentar cerrar el PreparedStatement de anuncio."); }
        }

        return true;
    }
    
    public Boolean editarSesion(Sesion sesionEditar) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = this.getConexion();

            String consulta = "UPDATE sesion SET idSala = ?, titulo = ?, fechaSesion = ?, horaInicio = ?, precio = ? " +
                              "WHERE idSesion = ?";

            stm = con.prepareStatement(consulta);
            stm.setInt(1, sesionEditar.getIdSala());
            stm.setString(2, sesionEditar.getTitulo());
            stm.setDate(3, java.sql.Date.valueOf(sesionEditar.getFechaSesion())); // yyyy-MM-dd
            stm.setTime(4, java.sql.Time.valueOf(sesionEditar.getHoraInicio()));   // HH:mm:ss
            stm.setFloat(5, sesionEditar.getPrecio());
            stm.setInt(6, sesionEditar.getIdSesion());

            stm.executeUpdate();

        } 
        catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion("Error al editar sesión: " + e.getMessage());
            return false;
        } 
        finally {
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("No se ha podido cerrar el PreparedStatement"); }
        }
        
        return true;
    }
    
    public Boolean eliminarSesion(Sesion sesion) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = this.getConexion();

            String consulta = "DELETE FROM sesion WHERE idSesion = ?";
            stm = con.prepareStatement(consulta);
            stm.setInt(1, sesion.getIdSesion());

            int filasAfectadas = stm.executeUpdate();

            return filasAfectadas > 0; //Devuelve true si se eliminó alguna fila
        } catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion("Error al eliminar sesión: " + e.getMessage());
            return false;
        } finally {
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("No se ha podido cerrar el PreparedStatement"); }
        }
    }
    
    public String obtenerSalasYSesiones(Pelicula pelicula) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        StringBuilder resultado = new StringBuilder();

        try {
            con = this.getConexion();
            String consulta = 
                "SELECT s.idSala, COUNT(s.idSesion) AS numSesiones " +
                "FROM Sesion s " +
                "WHERE s.titulo = ? " +
                "GROUP BY s.idSala " +
                "ORDER BY s.idSala";

            stm = con.prepareStatement(consulta);
            stm.setString(1, pelicula.getTitulo()); // Usar el título de la película
            rs = stm.executeQuery();

            while (rs.next()) {
                int idSala = rs.getInt("idSala");
                int numSesiones = rs.getInt("numSesiones");
                resultado.append("Sala ").append(idSala).append(" (").append(numSesiones).append(" sesiones), ");
            }

            // Eliminar la última coma y espacio, si existen
            if (resultado.length() > 0) {
                resultado.setLength(resultado.length() - 2);
            }
        } catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { }
            try { if (stm != null) stm.close(); } catch (Exception e) { }
        }

        return resultado.toString();
    }


}
