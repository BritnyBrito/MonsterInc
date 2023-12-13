package com.concurrentech;

import java.util.ArrayList;
import java.util.Arrays;

public class FabricaTanques {
    String [] tipos = {"Estandar", "Maxitanque,", "Ultratanque,", "Giga tanque"};
    int n = 1;
    int m = 2;
    int l = 2;
    int h = 3;
    int[] capacidades = {n,m*n,l*m*n, 2*h*l*m*n};
    float[] probabilidades ={0.5f,0.5f +0.25f,0.5f +0.25f+0.15f};
    private AlmacenTanques almacen = new AlmacenTanques();
    private ConstuctorTanques[] monstruos;
    public  Thread[] monstuosAcciones;
    private int NUMERO_MOSTRUOS =  5;
    private  int j = 0;
    private int iteraciones = 0;
    public void inicia(){
        inicializaMonstruos();
        try{
            initThreads();
        }catch(InterruptedException e){}
    }
    public Tanque fabricaTanque(){
        double eleccion = Math.random();
        int t = 3;
        if (eleccion < probabilidades[0]){
            t = 0;
        }else if (eleccion < probabilidades[1]){
            t = 1;
        }else if (eleccion < probabilidades[2]){
            t = 2;
        }
        Tanque tanque = new Tanque(tipos[t], capacidades[t]);
        almacen.agregaTanque(tanque);

        //tiempo que tarda la constuccion
        try {
            Thread.sleep(0, (t+1)*10 * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tanque;      
    } 
    public void inicializaMonstruos(){
        monstruos = new ConstuctorTanques[NUMERO_MOSTRUOS];
        monstruos[0] = new ConstuctorTanques(new ArrayList<Integer>(Arrays.asList(1,2)),"123","Peludo");
        monstruos[1] = new ConstuctorTanques(new ArrayList<Integer>(Arrays.asList(1,2)),"dcd","Peludo");
        monstruos[2] = new ConstuctorTanques(new ArrayList<Integer>(Arrays.asList(1,2)),"ew","Peludo");
        monstruos[3] = new ConstuctorTanques(new ArrayList<Integer>(Arrays.asList(0,1)),"43r", "Grande");
        monstruos[4] = new ConstuctorTanques(new ArrayList<Integer>(Arrays.asList(0,1)),"4rmk","Grande");
    }
    public void initThreads() throws InterruptedException{
        monstuosAcciones = new Thread[NUMERO_MOSTRUOS];
        for (int i = 0; i < NUMERO_MOSTRUOS; ++i) {
            monstuosAcciones[i] = new Thread(() -> adquiereRondas());
            monstuosAcciones[i].setName(String.format("%d", i));
            //j++;
        }
        for (Thread t : monstuosAcciones) {
            t.start();
        }
        for (Thread t : monstuosAcciones) {
            t.join();
        }
        if (iteraciones < 5){
            iteraciones ++;
            j = 0;
            initThreads();
        }

    }
    private  void adquiereRondas() {
        //int i = Integer.valueOf(Thread.currentThread().getName());
        ConstuctorTanques m = monstruos[j];
        j++;
        m.simulaFabricacionTanque(this);
        //if(j == NUMERO_MOSTRUOS){
          //  j = 0;
        //}
        
    }
}
