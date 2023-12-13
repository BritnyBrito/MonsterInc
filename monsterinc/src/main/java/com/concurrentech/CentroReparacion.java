package com.concurrentech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase para representar el centro de reparación de la utilería
 */
public class CentroReparacion {
    // orden en el que se van a reparar las cosas
    Queue<Utileria> ordenReparacion = new LinkedList();
    // tiempo que se tardan en reparar una utilería
    int tiempoReparacion;
    // para la simulacion, la probabilidad que algo de la utilería
    //este roto
    float probabilidadRoto =  0.4f;
    // Monstruos que harán las reparaciones
    private Reparador[] monstruos;
    // Número de monstruos reparadores
    private int NUMERO_MOSTRUOS =  5;
    // Acciones de cada uno de los monstruos reparadores
    public Thread[] monstruosAcciones = new Thread[NUMERO_MOSTRUOS];
    // Variables para llevar control de que tarea se le asigna a que monstruo
    private  volatile int i = 0;
    private volatile int p = 0;
    /**
     * Inicializa a los monstruos para que esten al pendiente en cuanto haya una
     * utilería que reparar
     */
    public void iniciaCentroReparacion(){
        inicializaMonstruos();
    }
    /**
     * Simula que un objeto se rompio
     * @param utileria
     * @return si en efecto la utileria se debe de reparar
     */
    public boolean simulaRoto(Utileria utileria){
        // vemos si esta utileria se rompera o no
        if (Math.random() < probabilidadRoto){
            // marcamos que en efecto se debe hacer una reparacion
            utileria.setEstado("reparacion");
            ordenReparacion.add(utileria);
            try{
                //asignamos a un monstruo para que lo repare
                initThread();
            }catch(InterruptedException e){
            }
            return true;
        }
        return false;
    }
    /**
     * Método para reparar una utilería
     */
    public void reparar(){
        // Vemos según el orden que utilería hay que reparar
        Utileria util = ordenReparacion. poll();
        if (util == null){
            System.out.println("no hay objetos que reparar");
            return;
        }
        try {
            // tiempo que tarda la reparación
            Thread.sleep(0, 30 * 1000); 
            // tenemos el item ahora disponible
            util.setEstado("disponible");
            String a = Thread.currentThread().getName();
            System.out.println("Utilería arreglada por: " + a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para inicializar a los monstruos que van a reparar la utileria
     */
    public void inicializaMonstruos(){
        monstruos = new Reparador[NUMERO_MOSTRUOS];
        monstruos[0] = new Reparador(new ArrayList<Integer>(Arrays.asList(1,2)),"123","Peludo");
        monstruos[1] = new Reparador(new ArrayList<Integer>(Arrays.asList(1,2)),"dcd","Peludo");
        monstruos[2] = new Reparador(new ArrayList<Integer>(Arrays.asList(1,2)),"ew","Peludo");
        monstruos[3] = new Reparador(new ArrayList<Integer>(Arrays.asList(0,1)),"43r", "Grande");
        monstruos[4] = new Reparador(new ArrayList<Integer>(Arrays.asList(0,1)),"4rmk","Grande");
    }
    public void initThread() throws InterruptedException{
        // Vemos que mosntruo debe realizar la repación
        int pos = i;
        if ( pos == NUMERO_MOSTRUOS - 1){
           i = 0;
        }else{
           i++;
        }
        // Vemos que el reparador este disponible
        if (monstruosAcciones[pos] != null){
            while(monstruosAcciones[pos].equals(Thread.State.RUNNABLE)){
            }
        }
        p = pos;
        // Asignamos la reparación al monstruo
        monstruosAcciones[pos] = new Thread(() -> adquiereReparacionUtileria());
        monstruosAcciones[pos].setName(String.format("%d", pos));
        monstruosAcciones[pos].start();
    }

    /**
     * Hacemos que un monstruo deba reparar una utileria
     */
    private  void adquiereReparacionUtileria() {
        int j = p;
        Reparador m = monstruos[j];
        m.simulaReparar(this);
    }
}
