package com.concurrentech;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase para representar a un recolector
 */
public class Recolector{
    // capacidad de almacenamientos del recolector
    private int capacidad = 10;
    // energía en el momento en el recolector
    private volatile int energiaAlMomento = 0;
    // total de energía que se ha almacenado en el recolector
    private volatile int energiaTotal = 0;
    // almacen de tanques donde se enviaran los tanques una vez vaciados
    private AlmacenTanques almacen = new AlmacenTanques();
    // candado para evitar que se tenga un mal registro de la energía almacenada
    private Lock lock = new ReentrantLock();
    /**
     * Método para vaciar la energía de un tanque en el recolector
     * @param tanque el tanque que se va a vaciar
     */
    public void vaciaTanque(Tanque tanque){
        // si la energía que traería el tanque sobrepasa la capacidad del recolector,
        // esperamos a que se vacie
        while (energiaAlMomento + tanque.getCapacidad() > capacidad){    
        }
        // recolectamos la energía al momento: uso de candado con el fin de evitar que se
        // pierda rastro de alguna actualización
        try {
            lock.lock();
            energiaAlMomento += tanque.getCapacidad();
            energiaTotal += tanque.getCapacidad();
        } finally {
            lock.unlock();
        }
        // regresamos el tanque, ahora disponible al almacen
        tanque.setEstado("disponible");
        almacen.agregaTanque(tanque);
        System.out.println("Tanque descargado. Energia al momento:" + energiaAlMomento + ". Energía total: " + energiaTotal);

    }
    /**
     * Método para vaciar el recolector: alimentar a la ciudad
     */
    public void vaciaRecolector (){
        try {
            lock.lock();
            energiaAlMomento =0;
        } finally {
            lock.unlock();
        }
        System.out.println("Recolector vaciado: Monstruopolis tiene energía");
    }
}
