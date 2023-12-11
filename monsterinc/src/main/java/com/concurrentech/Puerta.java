package com.concurrentech;

import java.util.ArrayList;

public class Puerta extends Utileria{
    private String direccionPuerta;
    private String extra;

    public Puerta(String direccionPuerta, String tipo, String extra){
        super(tipo);
        this.direccionPuerta = direccionPuerta;
        this.extra = extra;
    }
    public String getDireccionPuerta() {
        return direccionPuerta;
    }
    public void setDireccionPuerta(String direccionPuerta) {
        this.direccionPuerta = direccionPuerta;
    }
    public String getExtra() {
        return extra;
    }
    public void setExtra(String extra) {
        this.extra = extra;
    }
    
}