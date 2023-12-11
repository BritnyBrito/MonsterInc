package com.concurrentech;

import java.util.ArrayList;

public class AlmacenTanques {
    private ArrayList<Tanque> tanques = new ArrayList<>();
    public void agregaTanque(Tanque tanque){
        tanques.add(tanque);
    }
}
