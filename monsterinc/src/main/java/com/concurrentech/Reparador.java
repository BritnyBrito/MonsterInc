package com.concurrentech;

import java.util.ArrayList;

public class Reparador extends Monstruo{

    public Reparador(ArrayList<Integer> tiposBanno, String contrasena) {
        super(tiposBanno, contrasena);
    }
    public void simulaReparar(CentroReparacion centro){
        centro.reparar();
    }
}