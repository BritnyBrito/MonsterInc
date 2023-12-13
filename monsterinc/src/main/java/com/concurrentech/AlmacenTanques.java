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
     * Al sacar un tanque del almacen, se elimina de la lista.
     * @param tipo el tipo de tanque a buscar
     * @return el tanque encontrado
     */
    public Tanque getTanque(String tipo){
        // Se protege el almacén
        candado.lock();
        // Se busca un tanque del tipo dado
        Tanque tanque = null;
        for (Tanque t : tanques) {
            if (t.getTipo().equals(tipo) && t.getEstado().equals("disponible")) {
                tanque = t;
                break;
            }
        }
        // Si no se encontró un tanque del tipo dado, se espera
        while (tanque == null) {
            try {
                candado.unlock();
                Thread.sleep(1000);
                candado.lock();
                for (Tanque t : tanques) {
                    if (t.getTipo().equals(tipo) && t.getEstado().equals("disponible")) {
                        tanque = t;
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        // Se cambia el estado del tanque a ocupado
        tanque.setEstado("ocupado");
        // Se elimina el tanque de la lista
        tanques.remove(tanque);
        // Se desbloquea el almacén
        candado.unlock();
        // Se regresa el tanque
        return tanque;
    }

    // Similar al metodo anterior, pero regresa un tanque random
    public Tanque getTanqueRandom(){
        // Se protege el almacén
        candado.lock();
        // Se busca un tanque del tipo dado
        Tanque tanque = null;
        int index = (int) (Math.random() * tanques.size());
        tanque = tanques.get(index);
        // Si no se encontró un tanque del tipo dado, se espera
        while (tanque == null) {
            try {
                candado.unlock();
                Thread.sleep(1000);
                candado.lock();
                index = (int) (Math.random() * tanques.size());
                tanque = tanques.get(index);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        // Se cambia el estado del tanque a ocupado
        tanque.setEstado("ocupado");
        // Se elimina el tanque de la lista
        tanques.remove(tanque);
        // Se desbloquea el almacén
        candado.unlock();
        // Se regresa el tanque
        return tanque;
    }
}
