package com.concurrentech;

import java.util.ArrayList;

/**
 * Clase para representar a los monstruos que pueden diseñar puertas
 */
public class ConstructorPuertas extends Monstruo {
    /**
     * Constructor de la clase ConstructorPuertas
     *   @param tiposBanno baños a los que puede acceder
     *   @param contrasena contraseña del vestidor
     *   @param tipo tipo de monstruo
     */
    public ConstructorPuertas(ArrayList<Integer> tiposBanno, String contrasena, String tipo) {
        super(tiposBanno, contrasena,tipo);
    }
    /**
     * Método para construir una puerta
     * @param fabrica la fabrica en la que va a construir la puerta
     */
    public void simulaFabricacionPuertaPrincipal(FabricaPuertas fabrica){
        fabrica.fabricaPuerta();
    }
    /**
     * Método para ayudar a construir una puerta, esto para las puertas que sean
     * muy complicadas y requieran de más de un monstruo
     * @param fabrica la fabrica en la que va a ayudar a construir la puerta
     */
    public void simulaFabricacionPuertaAyuda(FabricaPuertas fabrica){
        fabrica.ayudaFabricaPuerta();
    }
    

}