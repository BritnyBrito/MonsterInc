package com.concurrentech;

import java.util.ArrayList;

public class Vestidor {
    private String contra;
    private ArrayList<String> elementos;
    public Vestidor(String contra){
        this.elementos = new ArrayList<>();
        this.contra = contra;
    }
    public void agregaElemento(String e, String contra){
        if(this.contra.equals(contra)){
            elementos.add(e);
            System.out.println("'elemento agregado a casillero'" );
        }
    }
    public String getContra() {
        return contra;
    }
    public void setContra(String contra) {
        this.contra = contra;
    }
}
