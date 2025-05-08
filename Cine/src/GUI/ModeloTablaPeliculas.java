/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import Aplicacion.*;
import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaPeliculas extends AbstractTableModel {
    private List<Pelicula> peliculas;

    public ModeloTablaPeliculas(){
       this.peliculas = new ArrayList<Pelicula>();
    }
    
    public int getColumnCount (){
        return 4;
    }

    public int getRowCount(){
        return peliculas.size();
    }
    
    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Titulo"; break;
            case 1: nombre= "Duración"; break;
            case 2: nombre="Género"; break;
            case 3: nombre="Idioma"; break;
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
            case 0: resultado= peliculas.get(row).getTitulo(); break;
            case 1: resultado= peliculas.get(row).getDuracion(); break;
            case 2: resultado=peliculas.get(row).getGenero();break;
            case 3: resultado=peliculas.get(row).getIdioma(); break;
        }
        return resultado;
    }
    
    public void setFilas(List<Pelicula> peliculas){
        this.peliculas=peliculas;
        fireTableDataChanged();
    }

    public Pelicula obtenerUsuario(int i){
        return this.peliculas.get(i);
    }
}
