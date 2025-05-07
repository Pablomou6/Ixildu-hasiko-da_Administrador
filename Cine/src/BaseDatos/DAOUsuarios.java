/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Aplicacion.*;
import java.sql.*;

/**
 *
 * @author alumnogreibd
 */
public class DAOUsuarios extends AbstractDAO {
    
    public DAOUsuarios(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public Usuario comprobarIdUsuarioAutenticacion(String idUsuario) {
        Connection conn = null;
        PreparedStatement stmUsuario = null;
        ResultSet rs = null;
        Usuario usuario = null;

        try {
            conn = this.getConexion();

            String consulta = "SELECT * FROM Usuario WHERE idUsuario = ?";
            stmUsuario = conn.prepareStatement(consulta);
            stmUsuario.setString(1, idUsuario);

            rs = stmUsuario.executeQuery();

            if(rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getString("idusuario"));
                usuario.setContrasena(rs.getString("contraseña"));
                usuario.setTipoUsuario(rs.getString("tipousuario"));
            }

        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } 
        finally {
            try {
                if(rs != null) {
                    rs.close();
                } 
            }
            catch(SQLException e) {
                System.out.println("No se ha podido cerrar el ResultSet.");
            }
            
            try {
                if(stmUsuario != null) {
                    stmUsuario.close();
                }
            }
            catch(SQLException e) {
                System.out.println("No se ha podido cerrar el PreparedStatement.");
            }
        }

        return usuario;
    }
      
}
