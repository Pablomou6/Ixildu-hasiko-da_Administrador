/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import Aplicacion.*;
import GUI.*;

/**
 *
 * @author alumnogreibd
 */
public class FachadaGUI {
    FachadaAplicacion fachadaAp;
    VPrincipal vPrincipal; 
    
    public FachadaGUI(FachadaAplicacion fa) {
        this.fachadaAp = fa;
        this.vPrincipal = new VPrincipal(this.fachadaAp);
    }
    
    //Método para mostrar las excepciones
    public void muestraExcepcion(String txtExcepcion){
       VAviso va;
       
       va = new VAviso(vPrincipal, true, txtExcepcion);
       va.setVisible(true);
    }
    
    public void iniciarVentanas() {
        VAutenticacion vAutenticacion;

        vAutenticacion = new VAutenticacion(vPrincipal, true, fachadaAp);
        vPrincipal.setVisible(true);
        vAutenticacion.setVisible(true);
    }
    
}
