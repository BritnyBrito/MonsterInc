package com.concurrentech;

import java.util.ArrayList;
public class ConstuctorPuertas extends Monstruo {
    public ConstuctorPuertas(ArrayList<Integer> tiposBanno, String contrasena) {
        super(tiposBanno, contrasena);
    }
    public void simulaFabricacionPuertaPrincipal(FabricaPuertas fabrica){
        
        fabrica.fabricaPuerta();
    }
    public void simulaFabricacionPuertaAyuda(FabricaPuertas fabrica){
        
        fabrica.ayudaFabricaPuerta();
    }
    

}