package com.concurrentech;

import java.util.HashMap;

public class Platillo {
    // Nombre del platillo
    private String nombre;

    // Tipos de ingredientes que utiliza con sus cantidades
    private HashMap<String, Integer> ingredientes;
    // Boolean que indica si requiere un chef profesional
    private boolean requiereProfesional;

    // Constructor
    public Platillo(HashMap<String, Integer> ingredientes, String nombre) {
        this.ingredientes = ingredientes;
        this.nombre = nombre;
    }

    // Método que regresa todos los ingredientes
    public HashMap<String, Integer> getIngredientes() {
        return ingredientes;
    }

    // Método que regresa la cantidad de algún ingrediente dado
    public int getCantidad(String ingrediente) {
        return ingredientes.get(ingrediente);
    }

    public String getNombre() {
        return nombre;
    }

    // Método que regresa si requiere un chef profesional
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
