package com.concurrentech;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase para representar el tanque
 */
public class Tanque extends Utileria{
    // capacidad de almacenamiento del tanque
    private int capacidad;
    // cantidad de energía que tiene el tanque
    private int energia;
    // candado para la energía del tanque
    private ReentrantLock candadoEnergia;

    
    /**
     * Constructor de la clase tanque
     * @param tipo tipo del tanque
     * @param capacidad capacidad del tanque
     */
    public Tanque(String tipo, int capacidad){
        super(tipo);
        this.capacidad = capacidad;
        this.energia = 0;
        this.candadoEnergia = new ReentrantLock();
    }
    /**
     * Método que nos da la capacidad del tanque
     * @return capacidad del tanque
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Método que nos da la energía del tanque
     * protegido por candado
     * @return energía del tanque
     */
    public int getEnergia() {
        try {
            candadoEnergia.lock();
            return energia;
        } finally {
            candadoEnergia.unlock();
        }
    }

    /**
     * Método que cambia la energía del tanque
     * protegido por candado
     * @param energia la nueva energía
     */
    public void setEnergia(int energia) {
        try {
            candadoEnergia.lock();
            this.energia = energia;
        } finally {
            candadoEnergia.unlock();
        }
    }

    @Override
    public String toString(){
        return "Capacidad: " + capacidad + ", tipo: " + tipo;
    }
    
    
}