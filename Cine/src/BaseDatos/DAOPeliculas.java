/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Aplicacion.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                    rs.getTime("duracion").toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                    rs.getString("genero"),
                    rs.getString("sinopsis"),
                    rs.getString("clasificacion"),
                    rs.getString("idioma"),
                    rs.getDate("fechaestreno").toLocalDate(),
                    rs.getTime("duraciontrailer").toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
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
    
    public void eliminarPelicula(Pelicula p) {
        Connection conexion = null;
        try {
            conexion = this.getConexion();

            // Verificar si la película tiene sesiones
            String checkSql = "SELECT COUNT(*) FROM sesion WHERE titulo = ?";
            PreparedStatement checkStmt = conexion.prepareStatement(checkSql);
            checkStmt.setString(1, p.getTitulo());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int numSesiones = rs.getInt(1);
            rs.close();
            checkStmt.close();

            if (numSesiones > 0) {
                throw new RuntimeException("No se puede eliminar la película porque tiene sesiones asociadas.");
            }

            // Si no hay sesiones, eliminar la película
            String sql = "DELETE FROM pelicula WHERE titulo = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, p.getTitulo());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la película: " + e.getMessage());
        }
    }
    
    //Función que editará una película de la BD
    public Boolean editarPelicula(Pelicula peliculaEditar) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = this.getConexion();

            //Creamos la consulta UPDATE (omitimos el campo 'titulo' porque es la PK y no se edita)
            String consulta = "UPDATE pelicula SET duracion = ?, genero = ?, sinopsis = ?, clasificacion = ?, idioma = ?, fechaestreno = ?, duraciontrailer = ? " +
                              "WHERE titulo = ?";

            stm = con.prepareStatement(consulta);

            // Asignamos los valores a los parámetros
            stm.setTime(1, java.sql.Time.valueOf(peliculaEditar.getDuracion()));
            stm.setString(2, peliculaEditar.getGenero());
            stm.setString(3, peliculaEditar.getSinopsis());
            stm.setString(4, peliculaEditar.getClasificacion());
            stm.setString(5, peliculaEditar.getIdioma());
            stm.setDate(6, java.sql.Date.valueOf(peliculaEditar.getFechaEstreno()));
            stm.setTime(7, java.sql.Time.valueOf(peliculaEditar.getDuracionTrailer()));
            stm.setString(8, peliculaEditar.getTitulo()); //WHERE, donde usamos el titulo (pk) para identificar la pelicula

            stm.executeUpdate();
        } catch (SQLException e) {
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            return false;
        } finally {
            try { if (stm != null) stm.close(); } catch (Exception e) { System.out.println("No se ha podido cerrar el PreparedStatement."); }
        }

        return true;
    }


    
}
