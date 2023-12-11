package com.concurrentech;

import java.util.ArrayList;
import java.util.Random;
public class Monstruo {
    private ArrayList<Integer> tiposBannos;
    private String contrasena;
    private Vestidor locker;
    public Monstruo(ArrayList<Integer> tiposBanno, String contrasena){
        this.tiposBannos =  tiposBanno;
        this.contrasena = contrasena;
        locker = new Vestidor(contrasena);
    }
    
    public void simulaVestidor(String elemento){
        locker.agregaElemento(elemento, contrasena);
    }
    public void simulaBanno(ArrayList<Banno> bannos){
        idaBanno(seleccionaBanno(bannos));
    }
    private  void idaBanno(Banno banno){
        String tipo = banno.getTipo();
        System.out.println("tratando de entrar al banno: " + tipo);
        banno.acquire();
        System.out.println("Entrando al banno: " + tipo);
        banno.release();
        System.out.println("Saliendo del banno: " + tipo);
    }
    private Banno seleccionaBanno(ArrayList<Banno> bannos){
        Random rand = new Random(); 
        int eleccionPos = rand.nextInt(tiposBannos.size()); 
        return bannos.get(tiposBannos.get(eleccionPos));
    }
    public void salirBanno() {

    }

    public void entrarVestidor() {

    }

    public void salirVestidor() {

    }
}
