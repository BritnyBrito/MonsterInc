package com.concurrentech;

import java.util.ArrayList;
import java.util.Arrays;

public class FabricaPuertas {
    private int fabricadas = 0;
    private float pFiesta = 0.2f;
    private float pPijamada = 0.1f;
    private AlmacenPuertas almacen = new AlmacenPuertas();
    private ConstuctorPuertas[] monstruos;
    public  Thread[] monstuosAcciones;
    private int NUMERO_MOSTRUOS =  5;
    private  int j = -1;
    private int salto = 0;
    private int iteraciones = 0;
    public void inicia(){
        inicializaMonstruos();
        try{
            initThreads();
        }catch(InterruptedException e){}
    }
    public Puerta fabricaPuerta(){
        Puerta puerta;
        fabricadas ++;
        String extra = "";
        if (Math.random()< pFiesta){
            extra = "fiesta";
        } else if (Math.random()< pPijamada){
            extra = "pijamada";
        }
        //vemos si será una puerta de adultos
        if ((fabricadas % 5) == 0){
            puerta = new Puerta("direccion " + Integer.toString(fabricadas), "adulto", extra);        
        }
        else{
            puerta = new Puerta("direccion " + Integer.toString(fabricadas), "nino", extra); 
        }
        almacen.agregaPuerta(puerta);
        //tiempo que tarda la constuccion
        try {
            Thread.sleep(0, 10 * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return puerta;      
    } 
    public void ayudaFabricaPuerta(){
        System.out.println("ayudando con puerta especial");
    }
    public void asignaPuertas(){

    }
    public void inicializaMonstruos(){
        monstruos[0] = new ConstuctorPuertas(new ArrayList<Integer>(Arrays.asList(1,2)),"123","Peludo");
        monstruos[1] = new ConstuctorPuertas(new ArrayList<Integer>(Arrays.asList(1,2)),"dcd","Peludo");
        monstruos[2] = new ConstuctorPuertas(new ArrayList<Integer>(Arrays.asList(1,2)),"ew","Peludo");
        monstruos[3] = new ConstuctorPuertas(new ArrayList<Integer>(Arrays.asList(0,1)),"43r", "Grande");
        monstruos[4] = new ConstuctorPuertas(new ArrayList<Integer>(Arrays.asList(0,1)),"4rmk","Grande");
    }
    private int especial = 1;
    public void initThreads() throws InterruptedException{
        monstuosAcciones = new Thread[NUMERO_MOSTRUOS];
        for (int i=0; i < NUMERO_MOSTRUOS - salto; ++i) {
            int k = i + salto;
            //vemos que el reparador este disponible
       /*  while(monstuosAcciones[k].equals(Thread.State.RUNNABLE)){
            Thread.sleep(0, 20 * 1000);
        }*/
            monstuosAcciones[k] = new Thread(() -> adquiereRondas());
            monstuosAcciones[k].setName(String.format("%d", k));
            if (especial % 3 == 0 && k <NUMERO_MOSTRUOS-1){
                /*while(monstuosAcciones[k+1].equals(Thread.State.RUNNABLE)){
                    Thread.sleep(0, 20 * 1000);
                }*/
                monstuosAcciones[k+1] = new Thread(() -> adquiereRondasayuda());
                monstuosAcciones[k+1].setName(String.format("%d", k+1));
                salto ++;}
            especial++;
        }
        for (Thread t : monstuosAcciones) {
            t.start();
        }
        for (Thread t : monstuosAcciones) {
            t.join();
        }

        /*if (iteraciones < 5){
            iteraciones ++;
            j = 0;
            initThreads();
        }*/
    }
    private  void adquiereRondas() {
       // int n = Integer.valueOf(Thread.currentThread().getName());
        j++;
        ConstuctorPuertas m = monstruos[j];
        /*if (especial % 3 == 0 && j <NUMERO_MOSTRUOS -1){
            j++;
            m.simulaFabricacionPuertaPrincipal(this);
            ConstuctorPuertas m2 = monstruos[j];
            m2.simulaFabricacionPuertaAyuda(this);
        }else{*/
            m.simulaFabricacionPuertaPrincipal(this);
        //}
        //if(j == NUMERO_MOSTRUOS){
          //  j = 0;
        //}
        
    }
    private  void adquiereRondasayuda() {
       // int n = Integer.valueOf(Thread.currentThread().getName());
        j++;
        ConstuctorPuertas m = monstruos[j];
        m.simulaFabricacionPuertaAyuda(this);
        /*if (especial % 3 == 0 && j <NUMERO_MOSTRUOS -1){
            j++;
            m.simulaFabricacionPuertaPrincipal(this);
            ConstuctorPuertas m2 = monstruos[j];
            m2.simulaFabricacionPuertaAyuda(this);
        }else{
            m.simulaFabricacionPuertaPrincipal(this);
        }*/
        //if(j == NUMERO_MOSTRUOS){
          //  j = 0;
        //}
        
    }

}
