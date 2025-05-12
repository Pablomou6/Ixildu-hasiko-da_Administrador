/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import Aplicacion.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaTrabajadores extends AbstractTableModel {
    private List<Trabajador> trabajadores;

    public ModeloTablaTrabajadores(){
       this.trabajadores = new ArrayList<Trabajador>();
    }
    
    public int getColumnCount (){
        return 4;
    }

    public int getRowCount(){
        return trabajadores.size();
    }
    
    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "DNI"; break;
            case 1: nombre= "Nombre"; break;
            case 2: nombre="Cargo"; break;
            case 3: nombre="Sueldo"; break;
        }
        return nombre;
    }
    
    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.String.class; break;
            case 2: clase=java.lang.String.class; break;
            case 3: clase=java.lang.Float.class; break;
        }
        return clase;
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }
    
    public Object getValueAt(int row, int col){
        Object resultado=null;

        switch (col){
            case 0: resultado= trabajadores.get(row).getDni(); break;
            case 1: resultado= trabajadores.get(row).getNombre(); break;
            case 2: resultado=trabajadores.get(row).getCargo();break;
            case 3: resultado=trabajadores.get(row).getSueldo(); break;
        }
        
        return resultado;
    }
    
    public void setFilas(List<Trabajador> trabajadores){
        this.trabajadores=trabajadores;
        fireTableDataChanged();
    }

    public Trabajador obtenerTrabajador(int i){
        if (i >= 0 && i < trabajadores.size()) {
            return this.trabajadores.get(i);
        } else {
            return null;
        }
    }
    
}
