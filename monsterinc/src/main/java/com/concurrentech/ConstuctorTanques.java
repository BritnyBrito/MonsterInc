package com.concurrentech;

import java.util.ArrayList;

public class ConstuctorTanques extends Monstruo {

    public ConstuctorTanques(ArrayList<Integer> tiposBanno, String contrasena) {
        super(tiposBanno, contrasena);
        System.out.println("tanque");
        //TODO Auto-generated constructor stub
    }
    public void simulaFabricacionTanque(FabricaTanques fabrica){
        
        fabrica.fabricaTanque();
    }
}
