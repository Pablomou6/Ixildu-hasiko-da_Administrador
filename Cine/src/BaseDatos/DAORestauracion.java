/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import Aplicacion.FachadaAplicacion;
import java.sql.Connection;

/**
 *
 * @author alumnogreibd
 */
public class DAORestauracion extends AbstractDAO{
    
    public DAORestauracion(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    boolean insertarComida(String nombre, double precio, String tamano, int stock, String descripcion) {
        return true;
        
        ///
        
    }
    
}
