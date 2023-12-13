package com.concurrentech;

/**
 * Clase para representar el tanque
 */
public class Tanque extends Utileria{
    // capacidad de almacenamiento del tanque
    private int capacidad;
    /**
     * Constructor de la clase tanque
     * @param tipo tipo del tanque
     * @param capacidad capacidad del tanque
     */
    public Tanque(String tipo, int capacidad){
        super(tipo);
        this.capacidad = capacidad;
    }
    /**
     * Método que nos da la capacidad del tanque
     * @return capacidad del tanque
     */
    public int getCapacidad() {
        return capacidad;
    }
    @Override
    public String toString(){
        return "Capacidad: " + capacidad + ", tipo: " + tipo;
    }
    
    
}