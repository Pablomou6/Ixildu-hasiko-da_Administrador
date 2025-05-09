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
public class ModeloTablaEquipo extends AbstractTableModel {
    private List<Equipo> equipos;

    public ModeloTablaEquipo(){
       this.equipos = new ArrayList<Equipo>();
    }
    
    public int getColumnCount (){
        return 4;
    }

    public int getRowCount(){
        return equipos.size();
    }
    
    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "ID"; break;
            case 1: nombre= "Nombre"; break;
            case 2: nombre="Tipo"; break;
            case 3: nombre="Modelo"; break;
        }
        return nombre;
    }
    
    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.Integer.class; break;
            case 1: clase= java.lang.String.class; break;
            case 2: clase=java.lang.String.class; break;
            case 3: clase=java.lang.String.class; break;
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
            case 0: resultado= equipos.get(row).getIdEquipo(); break;
            case 1: resultado= equipos.get(row).getNombre(); break;
            case 2: resultado=equipos.get(row).getTipo();break;
            case 3: resultado=equipos.get(row).getModelo(); break;
        }
        return resultado;
    }
    
    public void setFilas(List<Equipo> equipos){
        this.equipos=equipos;
        fireTableDataChanged();
    }

    public Equipo obtenerEquipo(int i){
        if (i >= 0 && i < equipos.size()) {
            return this.equipos.get(i);
        } else {
            return null;
        }
    }
}
