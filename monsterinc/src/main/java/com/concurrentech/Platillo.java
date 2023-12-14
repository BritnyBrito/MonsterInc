package com.concurrentech;

import java.util.HashMap;

/**
 * Clase que representa un platillo.
 * Permite simular el hacer pedidos a la cafeteria.
 */
public class Platillo {
    // Nombre del platillo
    private String nombre;

    // Tipos de ingredientes que utiliza con sus cantidades
    private HashMap<String, Integer> ingredientes;
    // Boolean que indica si requiere un chef profesional
    private boolean requiereProfesional;

    /**
     * Constructor de la clase Platillo
     * @param nombre el nombre del platillo
     * @param ingredientes los ingredientes que utiliza
     * @param requiereProfesional boolean que indica si requiere un chef profesional
     */
    public Platillo(String nombre, HashMap<String, Integer> ingredientes, boolean requiereProfesional) {
        this.ingredientes = ingredientes;
        this.nombre = nombre;
        this.requiereProfesional = requiereProfesional;
    }

    /**
     * Método para obtener los ingredientes
     * @return los ingredientes
     */
    public HashMap<String, Integer> getIngredientes() {
        return ingredientes;
    }

    /**
     * Método para obtener la cantidad de un ingrediente
     * @param ingrediente el ingrediente a buscar
     * @return la cantidad del ingrediente
     */
    public int getCantidad(String ingrediente) {
        return ingredientes.get(ingrediente);
    }

    /**
     * Método para obtener el nombre del platillo
     * @return el nombre del platillo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para saber si requiere un chef profesional
     * @return true si requiere un chef profesional, false en otro caso
     */
    public boolean requiereProfesional() {
        return requiereProfesional;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Platillo other = (Platillo) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (ingredientes == null) {
            if (other.ingredientes != null)
                return false;
        } else if (!ingredientes.equals(other.ingredientes))
            return false;
        if (requiereProfesional != other.requiereProfesional)
            return false;
        return true;
    }
}
