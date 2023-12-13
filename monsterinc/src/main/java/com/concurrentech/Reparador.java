package com.concurrentech;

import java.util.ArrayList;

/**
 * Clase para representar a los monstruos que puede reparar utileria
 */
public class Reparador extends Monstruo{
    
    /**
     * Constructor de la clase Reparador
     *   @param tiposBanno baños a los que puede acceder
     *   @param contrasena contraseña del vestidor
     *   @param tipo tipo de monstruo
     */
    public Reparador(ArrayList<Integer> tiposBanno, String contrasena, String tipo) {
        super(tiposBanno, contrasena,tipo);
    }

    /**
     * Método para reparar un objeto de utileria
     * @param centro el centro donde se realizan las reparaciones
     */
    public void simulaReparar(CentroReparacion centro){
        centro.reparar();
    }
}