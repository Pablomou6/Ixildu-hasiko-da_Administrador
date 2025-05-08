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
public class ModeloTablaSesiones extends AbstractTableModel {
    private List<Sesion> sesiones;

    public ModeloTablaSesiones(){
       this.sesiones = new ArrayList<Sesion>();
    }
    
    public int getColumnCount (){
        return 6;
    }

    public int getRowCount(){
        return sesiones.size();
    }
    
    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "idSesion"; break;
            case 1: nombre= "idSala"; break;
            case 2: nombre="Titulo"; break;
            case 3: nombre="Fecha"; break;
            case 4: nombre="Hora inicio"; break;
            case 5: nombre="Precio"; break;
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
            case 3: clase=java.lang.String.class; break;
            case 4: clase=java.lang.String.class; break;
            case 5: clase=java.lang.String.class; break;
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
            case 0: resultado= sesiones.get(row).getIdSesion(); break;
            case 1: resultado= sesiones.get(row).getIdSala(); break;
            case 2: resultado=sesiones.get(row).getTitulo();break;
            case 3: resultado=sesiones.get(row).getFechaSesion(); break;
            case 4: resultado=sesiones.get(row).getHoraInicio(); break;
            case 5: resultado=sesiones.get(row).getPrecio(); break;
        }
        return resultado;
    }
    
    public void setFilas(List<Sesion> sesiones){
        this.sesiones=sesiones;
        fireTableDataChanged();
    }

    public Sesion obtenerSesion(int i){
        if (i >= 0 && i < sesiones.size()) {
            return this.sesiones.get(i);
        } else {
            return null;
        }
    }
}
