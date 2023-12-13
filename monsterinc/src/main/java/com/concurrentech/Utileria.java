package com.concurrentech;

/**
 * Clase para representas la utiliería utiiizada en la empresa
 */
public class Utileria {
    // El estado de la utilería: ocupado, disponible o reparacion
    private String estado;
    // El tipo de la utilería
    protected String tipo;
    
    /**
     * Constructor de la clase Utiliería
     * @param tipo de la utilería
     */
    public Utileria(String tipo){
        this.tipo = tipo;
        this.estado = "disponible"; 
    }

    /**
     * Método que nos dice el estado
     * @return el estado de la utileria
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método que cambia el estado
     * @param estado el nuevo estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Método que nos dice el tipo de la utileria
     * @return el tipo de la utileria
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Método que cambia el tipo
     * @param tipo el nuevo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
