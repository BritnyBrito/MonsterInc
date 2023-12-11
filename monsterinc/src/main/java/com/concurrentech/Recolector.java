package com.concurrentech;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Recolector{
    private int capacidad = 10;
    private int tiempoEnergiaMonstruopolis;
    private volatile int energiaAlMomento;
    private AlmacenTanques almacen = new AlmacenTanques();
    private Lock lock = new ReentrantLock();
    public void vaciaTanque(Tanque tanque){
        while (energiaAlMomento + tanque.getCapacidad() > capacidad){
            //vaciaRecolector();      
            try{
                Thread.sleep(0, 40 * 1000);
            }catch(InterruptedException e){} 

        }
        //lock.lock
        //energiaAlMomento += tanque.getCapacidad();
        //lock.unlock
        try {
            lock.lock();
            // Critical section: Only one thread can execute this code block at a time
            energiaAlMomento += tanque.getCapacidad();
        } finally {
            lock.unlock();
        }
        tanque.setEstado("disponible");
        almacen.agregaTanque(tanque);
        System.out.println(energiaAlMomento);

    }
    public void vaciaRecolector (){
        //lock.lock
        //energiaAlMomento = 0;
        //lock.unlock
        try {
            lock.lock();
            // Critical section: Only one thread can execute this code block at a time
            energiaAlMomento =0;
        } finally {
            lock.unlock();
        }
        System.out.println("recolector vaciado");
    }
}
