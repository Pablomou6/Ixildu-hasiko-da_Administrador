/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion;

import BaseDatos.FachadaBaseDatos;
import GUI.FachadaGUI;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author alumnogreibd
 */
public class GestorSesion {
    FachadaGUI fachadaGUI;
    FachadaBaseDatos fachadaBD;
    
    public GestorSesion(FachadaGUI fgui, FachadaBaseDatos fbd) {
        this.fachadaGUI = fgui;
        this.fachadaBD = fbd;
    }
    
    public ArrayList<Sesion> obtenerSesiones() {
        ArrayList<Sesion> sesionesActuales = new ArrayList<Sesion>();
        
        sesionesActuales = fachadaBD.obtenerSesiones();
        
        return sesionesActuales;
    }
    
    private Boolean comprobarDisponibilidadSala(Integer idSala, String horaInicioStr, String fechaStr, ArrayList<Anuncio> anunciosAsignados, Pelicula peliculaAnadir) {
        //recuperamos todas las sesiones que se dan en la sala la fecha dada
        ArrayList<Sesion> sesiones = fachadaBD.recuperarSesionesSalaFecha(idSala, fechaStr);
        //Usando el id de las sesiones, recuperamos la película de cada sesión y los anuncios que hay en ella
        LocalTime horaInicio = LocalTime.parse(horaInicioStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
        int duracionAnunciosNueva = anunciosAsignados.stream().mapToInt(Anuncio::getDuracionMinutos).sum();
        int duracionTotalNueva = peliculaAnadir.getDuracionMinutos() + duracionAnunciosNueva;
        LocalTime horaFinNueva = horaInicio.plusMinutes(duracionTotalNueva);
        
        

        for (Sesion sesion : sesiones) {
            Pelicula peliculaSesion = fachadaBD.buscarPeliculas(sesion.getTitulo(), "", "", "", "", "", null, "").get(0);
            ArrayList<Integer> idsAnuncio = fachadaBD.recuperarAnunciosIdSesion(sesion.getIdSesion());
            ArrayList<Anuncio> anunciosSesion = fachadaBD.obtenerAnunciosConId(idsAnuncio);

            LocalTime inicioSesion = LocalTime.parse(sesion.getHoraInicio(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            int duracionAnunciosSesion = anunciosSesion.stream().mapToInt(Anuncio::getDuracionMinutos).sum();
            int duracionTotalSesion = peliculaSesion.getDuracionMinutos() + duracionAnunciosSesion;
            LocalTime finSesion = inicioSesion.plusMinutes(duracionTotalSesion);
            
            

            //Comprobamos solapamiento: (startA < endB) && (startB < endA)
            if (horaInicio.isBefore(finSesion) && inicioSesion.isBefore(horaFinNueva)) {
                return false; //Hay solapamiento
            }
        }

        return true; // No hay solapamientos
        
    }
    
    private boolean validarDatosSesion(Integer idSala, String horaInicio, String fecha, Float precio, ArrayList<Anuncio> anunciosAsignados, Pelicula peliculaAnadir) {
        // Validar campos nulos o vacíos
        if (idSala == null || horaInicio == null || horaInicio.isEmpty() ||
            fecha == null || fecha.isEmpty() || precio == null || peliculaAnadir == null) {
            fachadaGUI.muestraExcepcion("Alguno de los campos está vacío.");
            return false;
        }

        // Validar formato de hora: HH:mm:ss
        try {
            java.time.LocalTime.parse(horaInicio); // Lanza excepción si formato inválido
        } catch (java.time.format.DateTimeParseException e) {
            fachadaGUI.muestraExcepcion("Formato de la hora de inicio incorrecto.");
            return false;
        }

        // Validar fecha: que tenga formato correcto y no sea anterior a la fecha de estreno
        try {
            java.time.LocalDate fechaSesion = java.time.LocalDate.parse(fecha); // formato esperado: yyyy-MM-dd
            java.time.LocalDate fechaEstreno = peliculaAnadir.getFechaEstreno();

            if (fechaSesion.isBefore(fechaEstreno)) {
                fachadaGUI.muestraExcepcion("La fecha de la sesión es previa a la de estreno de la película.");
                return false;
            }
        } catch (java.time.format.DateTimeParseException e) {
            fachadaGUI.muestraExcepcion("Formato de fecha(s) incorrecto.");
            return false;
        }
        
        if(!this.comprobarDisponibilidadSala(idSala, horaInicio, fecha, anunciosAsignados, peliculaAnadir)) {
            fachadaGUI.muestraExcepcion("La sala no está disponible.");
            return false;
        }
        
        System.out.println("Sería válida");
        return true;
    }

    
    public Boolean anadirSesion(Integer idSala, String horaInicio, String fecha, Float precio, ArrayList<Anuncio> anunciosAsignados, Pelicula peliculaAnadir) {
        if(!validarDatosSesion(idSala, horaInicio, fecha, precio, anunciosAsignados, peliculaAnadir)) {
            return false;
        }
        
        Sesion sesionAnadir = new Sesion(idSala, peliculaAnadir.getTitulo(), fecha, horaInicio, precio);
        
        if(!fachadaBD.anadirSesion(sesionAnadir, anunciosAsignados)) {
            return false;
        }
        
        return true;
    }
}
