package com.concurrentech;

import java.util.ArrayList;

public class Reparador extends Monstruo{

    public Reparador(ArrayList<Integer> tiposBanno, String contrasena, String tipo) {
        super(tiposBanno, contrasena,tipo);
    }
    public void simulaReparar(CentroReparacion centro){
        centro.reparar();
    }
}