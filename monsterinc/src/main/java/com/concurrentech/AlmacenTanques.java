package com.concurrentech;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase para representar un almacén de tanques.
 * Se almacenan los tanques, se pueden agregar y quitar tanques.
 * Los tanques se almacenan de acuerdo con su tipo: 
 * Estandar, Maxitanque, Ultratanque, Giga tanque
 */

public class AlmacenTanques {
    // Lista de tanques
    private ArrayList<Tanque> tanques;
    // Candado para proteger el almacén
    private ReentrantLock candado;

    /**
     * Constructor de la clase AlmacenTanques
     */
    public AlmacenTanques(){
        tanques = new ArrayList<Tanque>();
        candado = new ReentrantLock();
    }

    /**
     * Método que agrega un tanque al almacén
     * @param tanque el tanque a agregar
     */
    public void agregaTanque(Tanque tanque){
        // Se protege el almacén
        candado.lock();
        // Se cambia estado del tanque a disponible
        tanque.setEstado("disponible");
        // Se agrega el tanque
        tanques.add(tanque);
        // Se desbloquea el almacén
        candado.unlock();
    }
    
    /**
     * Método que regresa un tanque del almacén
     * dado un tipo de tanque, el tanque debe estar disponible.
     * En caso de no encontrar un tanque del tipo dado, o
     * de que no haya tanques disponibles, el método
     * espera hasta que haya un tanque disponible.
     * @param tipo el tipo de tanque a buscar
     * @return el tanque encontrado
     */
    public Tanque getTanque(String tipo){
        // Se protege el almacén
        candado.lock();
        // Se busca el tanque dependiendo su tipo
        // Se busca un tanque disponible
        for(Tanque tanque : tanques){
            if(tanque.getEstado().equals("disponible") && tanque.getTipo().equals(tipo)){
                // Se cambia el estado del tanque
                tanque.setEstado("ocupado");
                // Se desbloquea el almacén
                candado.unlock();
                // Se regresa el tanque
                return tanque;
            }
        }
        // Se desbloquea el almacén
        candado.unlock();
        // Si no se encontró un tanque disponible, se espera
        // hasta que haya un tanque disponible
        return getTanque(tipo);
    }
}
