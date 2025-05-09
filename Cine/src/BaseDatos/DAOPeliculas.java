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
public class DAOPeliculas extends AbstractDAO {
    
    public DAOPeliculas(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public List<Pelicula> buscarPeliculas(String titulo, String duracion, String genero, String sinopsis, String clasificacion, 
        String idioma, LocalDate fechaEstreno, String duracionTrailer) {
        
        List<Pelicula> resultado = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();

            StringBuilder consulta = new StringBuilder("SELECT * FROM pelicula WHERE 1=1");
            List<Object> parametros = new ArrayList<>();

            if (titulo != null && !titulo.isEmpty()) {
                consulta.append(" AND titulo ILIKE ?");
                parametros.add("%" + titulo + "%");
            }
            if (duracion != null && !duracion.isEmpty()) {
                consulta.append(" AND duracion = ?");
                parametros.add(Time.valueOf(duracion));
            }
            if (genero != null && !genero.isEmpty()) {
                consulta.append(" AND genero ILIKE ?");
                parametros.add("%" + genero + "%");
            }
            if (sinopsis != null && !sinopsis.isEmpty()) {
                consulta.append(" AND sinopsis ILIKE ?");
                parametros.add("%" + sinopsis + "%");
            }
            if (clasificacion != null && !clasificacion.isEmpty()) {
                consulta.append(" AND clasificacion ILIKE ?");
                parametros.add("%" + clasificacion + "%");
            }
            if (idioma != null && !idioma.isEmpty()) {
                consulta.append(" AND idioma ILIKE ?");
                parametros.add("%" + idioma + "%");
            }
            if (fechaEstreno != null) {
                consulta.append(" AND fechaestreno = ?");
                //Debemos especificar que sea el Date de sql. Esto se debe a que tenemos el Date de java.util importado y tmabién el de java.sql
                parametros.add(java.sql.Date.valueOf(fechaEstreno)); 
            }
            if (duracionTrailer != null && !duracionTrailer.isEmpty()) {
                consulta.append(" AND duraciontrailer = ?");
                parametros.add(Time.valueOf(duracionTrailer));
            }

            stm = con.prepareStatement(consulta.toString());

            // Asignar parámetros
            for (int i = 0; i < parametros.size(); i++) {
                stm.setObject(i + 1, parametros.get(i));
            }

            rs = stm.executeQuery();
            while (rs.next()) {
                Pelicula p = new Pelicula(
                    rs.getString("titulo"),
                    rs.getTime("duracion").toLocalTime().toString(),
                    rs.getString("genero"),
                    rs.getString("sinopsis"),
                    rs.getString("clasificacion"),
                    rs.getString("idioma"),
                    rs.getDate("fechaestreno").toLocalDate(),
                    rs.getTime("duraciontrailer").toLocalTime().toString()
                );
                resultado.add(p);
            }

        } 
        catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } 
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { System.out.println("No se ha podido cerrar el ResultSet.");}
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("No se ha posido cerrar el PreparedStatement.");}
        }

        return resultado;
    }
    
    //Insert que introduce una película en la BD
    public Boolean anadirPelicula(Pelicula peliculaAnadir) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = this.getConexion();
            
            //Creamos el insert
            String consulta = "INSERT INTO pelicula (titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaestreno, duraciontrailer) " +
                              "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            //Pasamos el string al preparedStatement
            stm = con.prepareStatement(consulta);
            
            //Asignamos a cada atributo, la posición que debe ocupar en el insert.
            //En los que el tipo de dato es Time o Date, debemos hacer el valueof del string
            stm.setString(1, peliculaAnadir.getTitulo());
            stm.setTime(2, java.sql.Time.valueOf(peliculaAnadir.getDuracion()));
            stm.setString(3, peliculaAnadir.getGenero());
            stm.setString(4, peliculaAnadir.getSinopsis());
            stm.setString(5, peliculaAnadir.getClasificacion());
            stm.setString(6, peliculaAnadir.getIdioma());
            stm.setDate(7, java.sql.Date.valueOf(peliculaAnadir.getFechaEstreno()));
            stm.setTime(8, java.sql.Time.valueOf(peliculaAnadir.getDuracionTrailer()));
            
            //Ejecutamos el insert (es válido con executeUpdate ya que solo es una operación y tenemos el autocommit)
            stm.executeUpdate();
        } 
        catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            return false;
        } 
        finally {
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("No se ha posido cerrar el PreparedStatement.");}
        }
        
        return true;
    }

    
}
