package com.concurrentech;

public class Utileria {

    private String estado;
    private String tipo;

    public Utileria(String tipo){
        this.tipo = tipo;
        this.estado = "disponible"; 
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
