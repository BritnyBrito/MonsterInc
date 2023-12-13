package com.concurrentech;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AlmacenPuertas {
    private ArrayList<Puerta> puertas = new ArrayList<>();
    public void agregaPuerta(Puerta puerta){
        puertas.add(puerta);
        //System.out.println("puerta " + puerta.getTipo() + puerta.getExtra() );
    }
}
