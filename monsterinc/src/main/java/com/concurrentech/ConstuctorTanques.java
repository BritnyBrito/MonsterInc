package com.concurrentech;

import java.util.ArrayList;

/**
 * Clase para representar a los monstruos que pueden construir tanques
 */
public class ConstuctorTanques extends Monstruo {
    
    /**
     * Constructor de la clase ConstuctorTanques
     *   @param tiposBanno baños a los que puede acceder
     *   @param contrasena contraseña del vestidor
     *   @param tipo tipo de monstruo
     */
    public ConstuctorTanques(ArrayList<Integer> tiposBanno, String contrasena, String tipo) {
        super(tiposBanno, contrasena, tipo);
    }

    /**
     * Método para construir un tanque
     * @param fabrica la fabrica en la que va a construir el tanque
     */
    public void simulaFabricacionTanque(FabricaTanques fabrica){
        fabrica.fabricaTanque();
    }
}
