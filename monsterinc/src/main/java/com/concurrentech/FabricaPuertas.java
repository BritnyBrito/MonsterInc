package com.concurrentech;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase para representar a la única fábrica de puertas
 */
public class FabricaPuertas {
    // puertas fabricadas al momento
    private volatile int fabricadas = 0;
    // probabilidad de que sea una puerta de fiesta
    private float pFiesta = 0.2f;
    // probabillidad de que sea una puerta de pijamada
    private float pPijamada = 0.1f;
    // el almacen donde se van a guardar las puertas fabricadas
    private AlmacenPuertas almacen = new AlmacenPuertas();
    // Monstruos que construiran las puertas
    private ConstructorPuertas[] monstruos;
    // Guardamos las actividades que cada monstruo debe realizar
    public  Thread[] monstruosAcciones;
    // numero de monstruos constructores
    private int NUMERO_MOSTRUOS =  5;
    // variable para saber cada cuanto hacer una puerta especial
    private int especial = 1;
    // llevar un constrol de a que monstruo asignarle que actividad
    private  int j = -1;
    // para llevar control de a que monstruo asigarle la puerta: cuantas puestas 
    // a necesitado un 2o monstruo
    private int salto = 0;
    // para revisar cuantas rondas se llevan de construccion de puertas
    private int iteraciones = 0;

    /**
     * Método para empezar las operaciones de la fábrica
     */
    public void inicia(){
        inicializaMonstruos();
        try{
            initThreads();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    /**
     * Método para la construcción de una puerta
     * @return la puerta construida
     */
    public Puerta fabricaPuerta(){
        Puerta puerta;
        fabricadas ++;
        String extra = "";
        // Vemos si la puerta llevará a una fiesta o pijamada
        if (Math.random()< pFiesta){
            extra = "fiesta";
        } else if (Math.random()< pPijamada){
            extra = "pijamada";
        }
        // Vemos si será una puerta de adultos o niño y construimos la puerta
        if ((fabricadas % 5) == 0){
            puerta = new Puerta("direccion " + Integer.toString(fabricadas), "adulto", extra);        
        }
        else{
            puerta = new Puerta("direccion " + Integer.toString(fabricadas), "nino", extra); 
        }
        //tiempo que tarda la constuccion
        try {
            Thread.sleep(0, 10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Mostramos la puerta construida
        System.out.println("Se construyó puerta : " + puerta.toString());
        // agregamos la puerta al almacen
        almacen.agregaPuerta(puerta);
        return puerta;      
    } 
    /**
     * Método para ayudar en la fabricación de una puerta: para puertas especiales que 
     * requieren más de un monstruo
     */
    public void ayudaFabricaPuerta(){
        System.out.println("ayudando con puerta especial");
    }
    /**
     * Método para inicializar a los monstruos que van a construir las puertas 
     */
    public void inicializaMonstruos(){
        monstruos = new ConstructorPuertas[NUMERO_MOSTRUOS];
        monstruos[0] = new ConstructorPuertas(new ArrayList<Integer>(Arrays.asList(1,2)),"123","Peludo");
        monstruos[1] = new ConstructorPuertas(new ArrayList<Integer>(Arrays.asList(1,2)),"dcd","Peludo");
        monstruos[2] = new ConstructorPuertas(new ArrayList<Integer>(Arrays.asList(1,2)),"ew","Peludo");
        monstruos[3] = new ConstructorPuertas(new ArrayList<Integer>(Arrays.asList(0,1)),"43r", "Grande");
        monstruos[4] = new ConstructorPuertas(new ArrayList<Integer>(Arrays.asList(0,1)),"4rmk","Grande");
    }
    /**
     * Método para inicializar las acciones de los monstruos constructores
     * @throws InterruptedException
     */
    public void initThreads() throws InterruptedException{
        monstruosAcciones = new Thread[NUMERO_MOSTRUOS];
        for (int i=0; i < NUMERO_MOSTRUOS - salto; ++i) {
            // vemos a que monstruo asignarle trabajo
            int k = i + salto;
            // nos aseguramos que el monstruo no este ocupado haciendo otra puerta
            if(monstruosAcciones[k] != null){
                while(monstruosAcciones[k].equals(Thread.State.RUNNABLE)){
                    Thread.sleep(0, 20 * 1000);
                }
            }
            // asignamos la realizacion de una puerta a un monstruo
            monstruosAcciones[k] = new Thread(() -> adquierePuerta());
            monstruosAcciones[k].setName(String.format("%d", k));
            // vemos si es una puerta que requiere a más de un mosntruo
            if (especial % 3 == 0 && k <NUMERO_MOSTRUOS-1){
                // nos aseguramos que el monstruo no este ocupado haciendo otra puerta
                if(monstruosAcciones[k+1] != null){
                    while(monstruosAcciones[k+1].equals(Thread.State.RUNNABLE)){ // qt
                    Thread.sleep(0, 20 * 1000);
                    }
                }
                // asignamos un 2o monstruoa la puerta
                monstruosAcciones[k+1] = new Thread(() -> adquierePuertaAyuda());
                monstruosAcciones[k+1].setName(String.format("%d", k+1));
                salto ++;}
            especial++;
        }
        // hacemos que los monstruos comienzen sus operaciones
        for (Thread t : monstruosAcciones) {
            t.start();
        }
        for (Thread t : monstruosAcciones) {
            t.join();
        }
        // Vemos si los monstruos deben participar en construccion de una puerta
        if (iteraciones < 4){
            iteraciones ++;
            // reiniciar variables para ubicar a los monstruos con sus tareas
            especial = 1;
            j = -1;
            salto = 0;
            initThreads();
        }
    }
    /**
     * Metodo para que un monstruo construya una puerta
     */
    private  void adquierePuerta() {
        j++;
        ConstructorPuertas m = monstruos[j];
        m.simulaFabricacionPuertaPrincipal(this);
    }
    /**
     * Metodo para que un monstruo ayude a construir una puerta
     */
    private  void adquierePuertaAyuda() {
        j++;
        ConstructorPuertas m = monstruos[j];
        m.simulaFabricacionPuertaAyuda(this);
    }

}
