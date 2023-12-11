package com.concurrentech;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
public class CentroReparacion {
    Queue<Utileria> ordenReparacion = new LinkedList();
    int tiempoReparacion;
    float probabilidadRoto;
    private Reparador[] monstruos;
    private int NUMERO_MOSTRUOS =  5;
    public Thread[] monstuosAcciones;
    private int iteraciones = 0;
    private  volatile int  j = 0;
    private  volatile int i = 0;
    public CentroReparacion(){
        probabilidadRoto =  0.3f;
        monstuosAcciones = new Thread[NUMERO_MOSTRUOS];
        inicializaMonstruos();
    }
    public void simultaRoto(Utileria utileria){
        if (Math.random() < probabilidadRoto){
            utileria.setEstado("reparacion");
            ordenReparacion.add(utileria);
            try{
                initThreads();
            }catch(InterruptedException e){
            }
        }
        return;
    }
    public void reparar(){
        Utileria util = ordenReparacion. poll();
        if (util == null){
            System.out.println("no hay objetos que reparar");
            return;
        }
        try {
            Thread.sleep(0, 30 * 1000); //30 segundos de reparacion
            util.setEstado("disponible");
            String a = Thread.currentThread().getName();
            System.out.println("arreglado por: " + a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void inicializaMonstruos(){
        monstruos = new Reparador[NUMERO_MOSTRUOS];
        monstruos[0] = new Reparador(new ArrayList<Integer>(Arrays.asList(1,2)),"123");
        monstruos[1] = new Reparador(new ArrayList<Integer>(Arrays.asList(1,2)),"dcd");
        monstruos[2] = new Reparador(new ArrayList<Integer>(Arrays.asList(1,0)),"ew");
        monstruos[3] = new Reparador(new ArrayList<Integer>(Arrays.asList(2)),"43r");
        monstruos[4] = new Reparador(new ArrayList<Integer>(Arrays.asList(0)),"4r");
    }
    public void initThreads() throws InterruptedException{
        int pos = i;
        if ( pos == NUMERO_MOSTRUOS - 1){
           i = 0;
        }else{
           i++;
        }
        //vemos que el reparador este disponible
        if (monstuosAcciones[pos] != null){
            while(monstuosAcciones[pos].equals(Thread.State.RUNNABLE)){
                Thread.sleep(0, 40 * 1000);
            }
        }
        p = pos;
        monstuosAcciones[pos] = new Thread(() -> adquiereRondas());
        monstuosAcciones[pos].setName(String.format("%d", pos));
        monstuosAcciones[pos].start();

        /*monstuosAcciones = new Thread[NUMERO_MOSTRUOS];
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
        }*/
        

    }
    private volatile int p = 0;
    private  void adquiereRondas() {
        //int i = Integer.valueOf(Thread.currentThread().getName());
        int j = p;
        Reparador m = monstruos[j];
        //j++;
        m.simulaReparar(this);
        //if(j == NUMERO_MOSTRUOS){
          //  j = 0;
        //}
        
    }
}
