package com.concurrentech;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase para representar a la única fábrica de tanques
 */
public class FabricaTanques {
    // tipos de tanques que se fabrican
    String [] tipos = {"Estandar", "Maxitanque,", "Ultratanque,", "Giga tanque"};
    // capacidad de un tipo de tanque respecto a otro tipo
    int n = 1;
    int m = 2;
    int l = 2;
    int h = 3;
    int[] capacidades = {n,m*n,l*m*n, 2*h*l*m*n};
    // Prpbabilidades de que se fabrica un tipo de tanque
    float[] probabilidades ={0.5f,0.5f +0.25f,0.5f +0.25f+0.15f};
    // el almacen donde se van a guardar los tanque fabricados
    private AlmacenTanques almacen = new AlmacenTanques();
    // Monstruos que construiran los tanques
    private ConstuctorTanques[] monstruos;
    // Guardamos las actividades que cada monstruo debe realizar
    public  Thread[] monstruosAcciones;
    // numero de monstruos constructores
    private int NUMERO_MOSTRUOS =  5;
    // llevar un constrol de a que monstruo asignarle que actividad
    private  int j = 0;
    // para revisar cuantas rondas se llevan de construccion de tanques
    private int iteraciones = 0;

    /**
     * Método para empezar las operaciones de la fábrica
     */
    public void inicia(){
        inicializaMonstruos();
        try{
            initThreads();
        }catch(InterruptedException e){}
    }
    public Tanque fabricaTanque(){
        // vemos el tipo de tanque que se construirá
        double eleccion = Math.random();
        int t = 3;
        if (eleccion < probabilidades[0]){
            t = 0;
        }else if (eleccion < probabilidades[1]){
            t = 1;
        }else if (eleccion < probabilidades[2]){
            t = 2;
        }
        // construcción del tanque
        Tanque tanque = new Tanque(tipos[t], capacidades[t]);
        //tiempo que tarda la constuccion de un taque (en función de t, por 
        // lo que según el tipo será el tiempo que tarde)
        try {
            Thread.sleep(0, (t+1)*10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Mostramos el tanque construida
        System.out.println("Se construyó tanque : " + tanque.toString());
        // agregamos el tanque al almacen
        almacen.agregaTanque(tanque);
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
    /**
     * Método para inicializar a los monstruos que van a construir los tanques 
     */
    public void initThreads() throws InterruptedException{
        monstruosAcciones = new Thread[NUMERO_MOSTRUOS];
        for (int i = 0; i < NUMERO_MOSTRUOS; ++i) {
            // nos aseguramos que el monstruo no este ocupado haciendo otro tanque
            if(monstruosAcciones[i] != null){
                while(monstruosAcciones[i].equals(Thread.State.RUNNABLE)){
                    Thread.sleep(0, 20 * 1000);
                }
            }
            // asignamos la realizacion de un tanque a un monstruo
            monstruosAcciones[i] = new Thread(() -> adquiereRondas());
            monstruosAcciones[i].setName(String.format("%d", i));
        }
        // hacemos que los monstruos comienzen sus operaciones
        for (Thread t : monstruosAcciones) {
            t.start();
        }
        for (Thread t : monstruosAcciones) {
            t.join();
        }
        // Vemos si los monstruos deben participar en otra construccion de un tanque
        if (iteraciones < 4){
            iteraciones ++;
            // reiniciar variable para ubicar a los monstruos con sus tareas
            j = 0;
            initThreads();
        }

    }

    /**
     * Metodo para que un monstruo construya un tanque
     */
    private  void adquiereRondas() {
        ConstuctorTanques m = monstruos[j];
        j++;
        m.simulaFabricacionTanque(this);
    }
}
