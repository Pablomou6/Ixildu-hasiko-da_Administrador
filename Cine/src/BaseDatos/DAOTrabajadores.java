/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Aplicacion.*;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author alumnogreibd
 */
public class DAOTrabajadores extends AbstractDAO {
    
    public DAOTrabajadores(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    private void alerta(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void asignarSalas(Trabajador trab, String tipo, ArrayList<Integer> salasSelec, Connection con) throws SQLException {
        PreparedStatement stmtTrabajar=null;
        String tablaTrabajar = tipo.equalsIgnoreCase("Proyeccion") ? "trabajarproyeccion" : "trabajarlimpieza";

        String sqlTrabajar = "INSERT INTO " + tablaTrabajar + " (dni, idsala) VALUES (?, ?)";
    
        try{
            stmtTrabajar = con.prepareStatement(sqlTrabajar);
            for (Integer idSala : salasSelec) {
                stmtTrabajar.setString(1, trab.getDni());
                stmtTrabajar.setInt(2, idSala);
                stmtTrabajar.executeUpdate();
            }
        }catch(SQLException e){
            System.out.println("Fallo");            
        }
    }
    
    private void desasignarSalas(String dni, String tipo, ArrayList<Integer> salasNoSelec, Connection con, boolean eliminar) throws SQLException {
        PreparedStatement stmtEliminar=null;
        String tablaTrabajar = tipo.equalsIgnoreCase("Proyeccion") ? "trabajarproyeccion" : "trabajarlimpieza";
        
        
            // 🔹 Eliminar SOLO las salas que ya no están seleccionadas
            String consultaEliminar = "DELETE FROM " + tablaTrabajar + " WHERE dni = ?";
    
            try{
                stmtEliminar = con.prepareStatement(consultaEliminar);
                stmtEliminar.setString(1, dni);
                stmtEliminar.executeUpdate();
            }catch(SQLException e){
                System.out.println("Fallo");
            }            
        
//        else{
//            String sqlTrabajar = "DELETE FROM " + tablaTrabajar + " (dni, idsala) VALUES (?, ?)";
//    
//            try{
//                stmtEliminar = con.prepareStatement(sqlTrabajar);
//                for (Integer idSala : salasNoSelec) {
//                    stmtEliminar.setString(1, dni);
//                    stmtEliminar.setInt(2, idSala);
//                    stmtEliminar.executeUpdate();
//                }
//            }catch(SQLException e){
//                System.out.println("Fallo");            
//            }
//            
//        }
        
    }
    
    public List<Trabajador> buscarTrabajadores(String nombre, String ap1, String ap2, String dni, String cargo, Float sueldo) {
        
        List<Trabajador> resultado = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();

            StringBuilder consulta = new StringBuilder("SELECT * FROM trabajadorproyeccion UNION SELECT * FROM trabajadorlimpieza WHERE 1=1");
            List<Object> parametros = new ArrayList<>();

            if (dni != null && !dni.isEmpty()) {
                consulta.append(" AND dni ILIKE ?");
                parametros.add("%" + dni + "%");
            }
            if (nombre != null && !nombre.isEmpty()) {
                consulta.append(" AND nombre = ?");
                parametros.add("%" + nombre + "%");
            }
            if (ap1 != null && !ap1.isEmpty()) {
                consulta.append(" AND ap1 ILIKE ?");
                parametros.add("%" + ap1 + "%");
            }
            if (ap2 != null && !ap2.isEmpty()) {
                consulta.append(" AND ap2 ILIKE ?");
                parametros.add("%" + ap2 + "%");
            }
            if (cargo != null && !cargo.isEmpty()) {
                consulta.append(" AND cargo ILIKE ?");
                parametros.add("%" + cargo + "%");
            }
            if (sueldo != null && !sueldo.toString().isEmpty()) {
                consulta.append(" AND sueldo = ?");
                parametros.add(Float.valueOf(sueldo));
            }

            stm = con.prepareStatement(consulta.toString());

            // Asignar parámetros
            for (int i = 0; i < parametros.size(); i++) {
                stm.setObject(i + 1, parametros.get(i));
            }

            rs = stm.executeQuery();
            while (rs.next()) {
                Trabajador trab = new Trabajador(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("ap1"),
                    rs.getString("ap2"),
                    rs.getString("cargo"),
                    rs.getFloat("sueldo")
                );
                resultado.add(trab);
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
    public Boolean insertarTrabajador(Trabajador trab, String tipo, ArrayList<Integer> salasSelec) {
        Connection con = null;
        PreparedStatement stm = null;
        
        

        try {
            con = this.getConexion();
            //Desactivamos el autocomit para que podamos manejar manualmente la transacción
            con.setAutoCommit(false);
            
            String tablaTrabajador = tipo.equalsIgnoreCase("Proyeccion") ? "trabajadorproyeccion" : "trabajadorlimpieza";
            String tablaTrabajar = tipo.equalsIgnoreCase("Proyeccion") ? "trabajarproyeccion" : "trabajarlimpieza";
            
            //Creamos el insert
            String consulta = "INSERT INTO " + tablaTrabajador + " (dni, nombre, ap1, ap2, cargo, sueldo) " +
                              "VALUES (?, ?, ?, ?, ?, ?)";
            //Pasamos el string al preparedStatement
            stm = con.prepareStatement(consulta);
            
            //Asignamos a cada atributo, la posición que debe ocupar en el insert.
            //En los que el tipo de dato es Time o Date, debemos hacer el valueof del string
            stm.setString(1, trab.getDni());
            stm.setString(2, trab.getNombre());
            stm.setString(3, trab.getApellido1());
            stm.setString(4, trab.getApellido2());
            stm.setString(5, trab.getCargo());
            stm.setFloat(6, trab.getSueldo());
            
            //Ejecutamos el insert (es válido con executeUpdate ya que solo es una operación y tenemos el autocommit)
            stm.executeUpdate();
            
            asignarSalas(trab, tipo, salasSelec, con);
            
            con.commit();
            alerta("Trabajador insertado correctamente con salas asignadas", "Info");
        } 
        catch (SQLException e) {
            try{
                con.rollback();
                System.err.println("Error al insertar el trabajador. Se ha realizado un rollback");
                
            } catch (SQLException rollbackEx){
                System.err.println("Error al hacer rollback");
            }
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            return false;
        } 
        finally {
            try { 
                con.setAutoCommit(true);
                if (stm != null) stm.close(); 
            } catch (Exception e) { 
                System.out.println("No se ha posido cerrar el PreparedStatement.");}
        }
        
        return true;
    }

    public List<Trabajador> obtenerTrabajador(String nombre, String dni) {
        List<Trabajador> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmTrabajadores=null;
        ResultSet rsTrabajadores=null;
        String ordni="";
        if(!dni.isEmpty()){
            ordni = " OR dni LIKE '%"+ dni + "%' ";
        }
    String consulta = "SELECT * FROM trabajadorproyeccion WHERE nombre LIKE '%"+ nombre +"%' "+ ordni +
                      "UNION " +
                      "SELECT * FROM trabajadorlimpieza WHERE nombre LIKE '%"+ nombre + "%' "+ ordni;

    try{
        
        con = this.getConexion();
        stmTrabajadores = con.prepareStatement(consulta);
         
        System.out.println("Consulta: " + consulta);
        
        try{
            rsTrabajadores = stmTrabajadores.executeQuery();
            while (rsTrabajadores.next()) {
                resultado.add(new Trabajador(
                    rsTrabajadores.getString("nombre"),
                    rsTrabajadores.getString("ap1"),
                    rsTrabajadores.getString("ap2"),
                    rsTrabajadores.getString("dni"),
                    rsTrabajadores.getString("cargo"),
                    rsTrabajadores.getFloat("sueldo")
                ));
            }
        }catch(SQLException e){
            System.err.println("no");
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener trabajadores: " + e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        
    }
    finally{
        try {
        if (rsTrabajadores != null) rsTrabajadores.close();
        if (stmTrabajadores != null) stmTrabajadores.close();
    } catch (SQLException e) {
        System.err.println("Error al cerrar recursos: " + e.getMessage());
    }
    }
    
    return resultado;
    }

    public String obtenerTipoTrabajador(String dni) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection con = this.getConexion();

    try {
        if (con == null) {
            System.err.println("Error: La conexión a la base de datos está cerrada.");
        return null;
        }       
        
    String consulta = "SELECT 'Proyeccion' AS tipo FROM trabajadorproyeccion WHERE dni = ? " +
                      "UNION " +
                      "SELECT 'Limpieza' AS tipo FROM trabajadorlimpieza WHERE dni = ?";
    stm = con.prepareStatement(consulta);
    stm.setString(1, dni);
    stm.setString(2, dni);
    
        System.out.println("Consulta: " + consulta);

    rs = stm.executeQuery();
    if (rs.next()) {
        return rs.getString("tipo");
    }

} catch (SQLException e) {
    System.err.println("Error al obtener el tipo de trabajador: " + e.getMessage());
} finally {
    try {
        if (rs != null) rs.close();
        if (stm != null) stm.close();
    } catch (SQLException e) {
        System.err.println("Error al cerrar recursos: " + e.getMessage());
    }
}

return null;
    }

    public List<Integer> obtenerSalasTrabajador(String dni) {
        List<Integer> salasAsignadas = new ArrayList<>();
        Connection con;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String consulta = "SELECT idsala FROM TrabajarProyeccion WHERE dni like '%"+ dni + "%' " +
                        "UNION " +
                        "SELECT idsala FROM TrabajarLimpieza WHERE dni like '%"+ dni + "%'";

        try {
            con = this.getConexion();
            stm = con.prepareStatement(consulta);
        
            try {
                rs = stm.executeQuery();
                while (rs.next()) {
                    salasAsignadas.add(rs.getInt("idsala"));
                }
            }catch (SQLException e){
                System.err.println("no");                
            }
        
        } catch (SQLException e) {
            System.err.println("Error al obtener salas asignadas: " + e.getMessage());
        }   
    
        return salasAsignadas;
    }

    public Boolean actualizarTrabajador(Trabajador trab, String tipo, ArrayList<Integer> salasSeleccionadas, ArrayList<Integer> salasNoSeleccionadas) {
        
        String consulta = "";
        
        if ("Proyeccion".equals(tipo)) {
        consulta = "UPDATE trabajadorproyeccion SET nombre = ?, ap1 = ?, ap2 = ?, cargo = ?, sueldo = ? WHERE dni = ?";
        } 
        else if ("Limpieza".equals(tipo)) {
        consulta = "UPDATE trabajadorlimpieza SET nombre = ?, ap1 = ?, ap2 = ?, cargo = ?, sueldo = ? WHERE dni = ?";
        } 
        else {
        System.err.println("Error: Tipo de trabajador desconocido.");
        }
        
        PreparedStatement stmt=null;
        Connection con = null;
        
        try{
        con = this.getConexion();
        stmt = con.prepareStatement(consulta);

        stmt.setString(1, trab.getNombre());
        stmt.setString(2, trab.getApellido1());
        stmt.setString(3, trab.getApellido2());
        stmt.setString(4, trab.getCargo());
        stmt.setFloat(5, trab.getSueldo());
        stmt.setString(6, trab.getDni());
      
        int filasActualizadas = stmt.executeUpdate();
        
        ArrayList<Integer> salasTodas = new ArrayList<>();
        salasTodas.add(1);
        salasTodas.add(2);
        salasTodas.add(3);
        salasTodas.add(4);
        salasTodas.add(5);
        desasignarSalas(trab.getDni(),tipo,salasTodas, con, true);
        asignarSalas(trab, tipo, salasSeleccionadas, con);
        
              
        
        if (filasActualizadas > 0) {

            System.out.println("Trabajador actualizado correctamente.");
        } else {
            System.err.println("Error: No se encontró el trabajador con ese DNI.");
        }

        } catch (SQLException e) {
            System.err.println("Error al actualizar trabajador: " + e.getMessage());
        }
        
        return true;        
    }

    public boolean eliminarTrabajador(String dni, String tipo) {
        Connection con = null;
        PreparedStatement stmTrabajador = null;
        PreparedStatement stmSalas = null;

        try {
            con = this.getConexion();
            con.setAutoCommit(false); // Desactivamos autoCommit para manejar la transacción manualmente
        
            // Determinar las tablas correctas
            String tablaTrabajador = tipo.equalsIgnoreCase("Proyeccion") ? "trabajadorproyeccion" : "trabajadorlimpieza";
            
            desasignarSalas(dni, tipo, null , con, true);
            
            //stmSalas.executeUpdate();
        
            // Luego eliminamos el trabajador
            String sqlEliminarTrabajador = "DELETE FROM " + tablaTrabajador + " WHERE dni = ?";
            stmTrabajador = con.prepareStatement(sqlEliminarTrabajador);
            stmTrabajador.setString(1, dni);
         
            int filasEliminadas = stmTrabajador.executeUpdate();

            if (filasEliminadas > 0) {
                con.commit(); // Si todo se ejecuta correctamente, confirmamos la transacción
                alerta("Trabajador eliminado correctamente", "Info");
                
                return true;
            } else {
                con.rollback(); // Si no se encontró el trabajador, hacemos rollback
                alerta("Error: No se encontró un trabajador con ese DNI", "Error");
                return false;
            }
        } catch (SQLException e) {
            try {
                if (con != null) con.rollback(); // En caso de error, revertimos cambios
                System.err.println("Error al eliminar trabajador. Se ha realizado un rollback.");
            } catch (SQLException rollbackEx) {
                System.err.println("Error al hacer rollback.");
            }
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            return false;
        } finally {
            try {
                con.setAutoCommit(true);
                if (stmTrabajador != null) stmTrabajador.close();
                if (stmSalas != null) stmSalas.close();
            } catch (Exception e) {
                System.out.println("No se ha podido cerrar el PreparedStatement.");
        }
    }
}

    

    
}
