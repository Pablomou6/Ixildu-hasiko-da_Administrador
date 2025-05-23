/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Trabajador {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String Dni;
    private String cargo;
    private Float sueldo;
    

    
    public Trabajador(String nombre, String apellido1, String apellido2, String Dni, String cargo, Float sueldo){
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.Dni = Dni;
        this.cargo = cargo;
        this.sueldo = sueldo;
    }
    public Trabajador(String dni, String nombre, String cargo, Double sueldo) {
        this.Dni = dni;
        this.nombre = nombre;
        this.cargo = cargo;
        this.sueldo = sueldo.floatValue(); // Convertir Double a Float

    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Float getSueldo() {
        return sueldo;
    }

    public void setSueldo(Float sueldo) {
        this.sueldo = sueldo;
    }
    
    
}
