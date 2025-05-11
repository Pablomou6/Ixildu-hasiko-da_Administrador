/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import Aplicacion.Anuncio;
import Aplicacion.FachadaAplicacion;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 *
 * @author alumnogreibd
 */
public class DAOAnuncios extends AbstractDAO {
    
    public DAOAnuncios(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public ArrayList<Anuncio> obtenerAnuncios() {
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();

            String consulta = "SELECT idAnuncio, tematica, duracion FROM anuncio";
            stm = con.prepareStatement(consulta);
            rs = stm.executeQuery();

            while (rs.next()) {
                Anuncio anuncio = new Anuncio(
                    rs.getInt("idAnuncio"),
                    rs.getString("tematica"),
                    rs.getTime("duracion").toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                );
                anuncios.add(anuncio);
            }

        } 
        catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion("Error al obtener anuncios: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { System.out.println("No se pudo cerrar ResultSet."); }
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("No se pudo cerrar PreparedStatement."); }
        }

        return anuncios;
    }
    
    public ArrayList<Anuncio> obtenerAnunciosConId(ArrayList<Integer> idAnuncio) {
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        if (idAnuncio == null || idAnuncio.isEmpty()) {
            this.getFachadaAplicacion().muestraExcepcion("No se han podido recuperar anuncios dado que no se han proporcionado id's.");
            return anuncios;
        }

        try {
            con = this.getConexion();

            //Generamos un número de placeholders (?) igual al número de IDs: convertimos idAnuncio a un stream, de forma que lo mapeamos y hacemos que por cada "id"
            //se genere un interrogante "?". Por último, collect une todos los elementos del strem (solo hay tantos ? como id's había), de forma que se separan con formato
            //", ". Con collect() especificamos que queremos unir todos los elementos y con Collectors se especifica el formato. Sin esl segundo no se podría usar collect().
            String placeholders = idAnuncio.stream().map(id -> "?").collect(Collectors.joining(", "));
            String consulta = "SELECT * FROM anuncio WHERE idAnuncio IN (" + placeholders + ")";
            stm = con.prepareStatement(consulta);

            // Asignamos los valores a los placeholders
            for (int i = 0; i < idAnuncio.size(); i++) {
                stm.setInt(i + 1, idAnuncio.get(i));
            }

            rs = stm.executeQuery();
            while (rs.next()) {
                Anuncio anuncio = new Anuncio(
                    rs.getInt("idAnuncio"),
                    rs.getString("tematica"),
                    rs.getTime("duracion").toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                );
                anuncios.add(anuncio);
            }

        } catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion("Error al obtener anuncios: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { System.out.println("No se pudo cerrar ResultSet."); }
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("No se pudo cerrar PreparedStatement."); }
        }

        return anuncios;
    }

     
}
