package com.concurrentech;


public class Tanque extends Utileria{
    private int capacidad;
    public Tanque(String tipo, int capacidad){
        super(tipo);
        this.capacidad = capacidad;
    }
    public int getCapacidad() {
        return capacidad;
    }

    
    
}