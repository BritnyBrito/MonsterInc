package com.concurrentech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;

public class Main2 {
    // PARA LOS BANOS Y VESTIDORES
    // variable para saber a que monstuo se le asigna que accion: ir al vestido y baño
    private static int j = 0;
    // arreglo de hilos donde cada hilo permitira que un monstruo realice acciones
    public static Thread[] monstruosAcciones;
    // arreglo de monstruos genericos
    public static Monstruo[] monstruos;
    // lista con todos lo baños existentes
    public static ArrayList<Banno> bannos;
    // Máximo número de monstruos que trataran de usar los baños y vestidor
    public static int NUMERO_MOSTRUOS = 5;

    // PARA LA FABRICA DE PUERTAS
    public static FabricaPuertas fabricaPuertas;
    // Hilo para llevar las ejecucionde la fabrica de puertas
    public static Thread fabricaPuertasAcciones;

    // PARA LA FABRICA DE TANQUES
    public static FabricaTanques fabricaTanques;
    // Hilo para llevar las ejecucionde la fabrica de tanques
    public static Thread fabricaTanquesAcciones;
    
    //PARA EL RECOLECTOR
    public static Recolector recolector;
    // Hilo para realizar la alimentación de energía de la cuiudad
    public static Thread revisaRecolector;
    // timer para ver cuando hacer la alimentación
    public static Timer timer;
    // cada cuanto alimentar monstruopolis
    private static int TIEMPO_ALIMENTACION= 1 * 10;
    
    public static void main(String[] args) throws InterruptedException, IOException {
        
        // FABRICA DE PUERTAS
        fabricaPuertas = new FabricaPuertas();
        fabricaPuertasAcciones = new Thread(() -> fabricaPuertas.inicia());
        fabricaPuertasAcciones.start();
        
        // FABRICA DE TANQUES
        fabricaTanques = new FabricaTanques();
        fabricaTanquesAcciones = new Thread(() -> fabricaTanques.inicia());
        fabricaTanquesAcciones.start();
        
        // RECOLECTOR
        timer = new Timer();
        recolector = new Recolector();
        revisaRecolector = new Thread(() -> alimentaCiudad());
        revisaRecolector.start();
        // TEST esto correrá en Centro sustos
        Tanque[] tanques = {
            new Tanque("a", 1),
            new Tanque("b", 2),
            new Tanque("a", 3),
            new Tanque("c", 1),
            new Tanque("a", 2),
            new Tanque("a", 3),
            new Tanque("b", 1),
            new Tanque("a", 1),
            new Tanque("a", 2),
            new Tanque("a", 3),
            new Tanque("b", 1),
        };
        ArrayList<Thread> aaa = new ArrayList<>();
        for(Tanque t:  tanques){
            aaa.add(new Thread(() -> recolector.vaciaTanque(t)));
        }
        for( Thread t: aaa){
            System.out.println("aaaaaa");
            t.start();
        }
        // PARA CENTRO REPACION
        CentroReparacion centroReparacion = new CentroReparacion();
        Tanque[] tanques2 = {
            new Tanque("a", 1),
            new Tanque("b", 2),
            new Tanque("a", 3),
            new Tanque("c", 1),
            new Tanque("a", 2),
            new Tanque("a", 3),
            new Tanque("b", 1),
            new Tanque("a", 1),
            new Tanque("a", 2),
            new Tanque("a", 3),
            new Tanque("b", 1),
        };
        ArrayList<Thread> aaa2 = new ArrayList<>();
        Thread centroReparacionAA = new Thread(() -> centroReparacion.iniciaCentroReparacion());
        centroReparacionAA.start();
        for(Tanque t:  tanques2){
            aaa2.add(new Thread(() -> centroReparacion.simulaRoto(t)));
        }
        for( Thread t: aaa2){
            System.out.println("aaaaaa2");
            t.start();
        }
        // FIN TEST
        // VESTIDORES Y BANNOS
        inicializaBanos();
        inicializaMonstruos();
        initThreads();
        for (Thread t : monstruosAcciones) {
            t.start();
        }
    }
    /**
    * Inicializamos los monstruos que iran al banno y usaran su vestidor
    */
    private static void inicializaMonstruos(){
        monstruos = new Monstruo[NUMERO_MOSTRUOS];
        monstruos[0] = new Monstruo(new ArrayList<Integer>(Arrays.asList(1,2)),"123","Peludo");
        monstruos[1] = new Monstruo(new ArrayList<Integer>(Arrays.asList(1,2)),"dcd","Peludo");
        monstruos[2] = new Monstruo(new ArrayList<Integer>(Arrays.asList(1,2)),"ew","Peludo");
        monstruos[3] = new Monstruo(new ArrayList<Integer>(Arrays.asList(0,1)),"43r", "Grande");
        monstruos[4] = new Monstruo(new ArrayList<Integer>(Arrays.asList(0,1)),"4rmk","Grande");
    }

    /**
    * Inicializamos los baños
    */
    private static void inicializaBanos(){
        bannos = new ArrayList<>();
        bannos.add(new Banno(NUMERO_MOSTRUOS, 1, 1));
        bannos.add(new Banno(NUMERO_MOSTRUOS, 2, 2));
        bannos.add(new Banno(NUMERO_MOSTRUOS, 3, 3));
    }

    /**
     * Método para inicializar las acciones de los monstruos genericos: ir su vestido 
     * y al baño 
     */
    private static void initThreads() {
        monstruosAcciones = new Thread[NUMERO_MOSTRUOS];
        for (int i = 0; i < NUMERO_MOSTRUOS; ++i) {
            monstruosAcciones[i] = new Thread(() -> idaVestidorBanno());
            monstruosAcciones[i].setName(String.format("%d", i));
        }
    }
    /**
     * Metodo para que un monstruo vaya a su vestidor:  agrega, modifique 
     * y finalmente elimine un elemento de su vestidor. Después va al baño
     */
    private static void idaVestidorBanno() {
        Monstruo m = monstruos[j];
        j++;
        m.simulaVestidor("elemento" + String.valueOf(j));
        m.simulaBanno(bannos);
    }

    // Método para crear chefs con nombres aleatorios, creamos 5 chefs
    private static LinkedList<Chef> inicializaChefs(Cafeteria cafeteria) {
        LinkedList<Chef> chefs = new LinkedList<Chef>();
        chefs.add(new Chef(false, cafeteria, "Juan"));
        chefs.add(new Chef(false, cafeteria, "Pedro"));
        chefs.add(new Chef(true, cafeteria, "Luis"));
        chefs.add(new Chef(false, cafeteria, "Ana"));
        chefs.add(new Chef(false, cafeteria, "Maria"));
        return chefs;
    }

    // Método para crear meseros, creamos 10 meseros
    private static LinkedList<Mesero> inicializaMeseros(Cafeteria cafeteria) {
        LinkedList<Mesero> meseros = new LinkedList<Mesero>();
        meseros.add(new Mesero("Juan", cafeteria));
        meseros.add(new Mesero("Pedro", cafeteria));
        meseros.add(new Mesero("Luis", cafeteria));
        meseros.add(new Mesero("Ana", cafeteria));
        meseros.add(new Mesero("Maria", cafeteria));
        meseros.add(new Mesero("Jose", cafeteria));
        meseros.add(new Mesero("Luisa", cafeteria));
        meseros.add(new Mesero("Miguel", cafeteria));
        meseros.add(new Mesero("Rosa", cafeteria));
        meseros.add(new Mesero("Carlos", cafeteria));
        return meseros;
    }
    

    // Método para crear mesas, creamos 10 mesas y a cada una le corresponde un mesero
    private static LinkedList<Mesa> inicializaMesas(Cafeteria cafeteria, LinkedList<Mesero> meseros) {
        LinkedList<Mesa> mesas = new LinkedList<Mesa>();
        for (int i = 0; i < 10; i++) {
            mesas.add(new Mesa(i, 4, cafeteria, meseros.get(i)));
        }
        return mesas;
    }

    // Método para crear una cafetería
    // Nos inventamos algunos platillos, un inventario y una lista de mesas
    private static Cafeteria inicializaCafeteria() {
        // Creamos los platillos
        Platillo platillo1 = new Platillo("Hamburguesa", new HashMap<String, Integer>() {
            {
                put("Pan", 1);
                put("Carne", 1);
                put("Lechuga", 1);
                put("Tomate", 1);
                put("Queso", 1);
            }
        }, false);
        Platillo platillo2 = new Platillo("Hot dog", new HashMap<String, Integer>() {
            {
                put("Pan", 1);
                put("Salchicha", 1);
                put("Lechuga", 1);
                put("Tomate", 1);
                put("Queso", 1);
            }
        }, false);
        Platillo platillo3 = new Platillo("Ensalada", new HashMap<String, Integer>() {
            {
                put("Lechuga", 1);
                put("Tomate", 1);
                put("Queso", 1);
            }
        }, false);
        Platillo platillo4 = new Platillo("Sopa", new HashMap<String, Integer>() {
            {
                put("Agua", 1);
                put("Tomate", 1);
                put("Queso", 1);
            }
        }, true);

        // Creamos el inventario
        HashMap<String, Integer> inventario = new HashMap<String, Integer>() {
            {
                put("Pan", 10);
                put("Carne", 10);
                put("Lechuga", 10);
                put("Tomate", 10);
                put("Queso", 10);
                put("Salchicha", 10);
                put("Agua", 10);
            }
        };

        // Creamos la cafetería con las mesas, los platillos y el inventario
        Cafeteria cafeteria = new Cafeteria(10, new LinkedList<Platillo>() {
            {
                add(platillo1);
                add(platillo2);
                add(platillo3);
                add(platillo4);
            }
        }, inventario);

        return cafeteria;
    }
    /**
     * Metodo que se aseguro que se le brinde energía a la ciudad cada TIEMPO_ALIMENTACION 
     */
    private static void alimentaCiudad(){
       timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                recolector.vaciaRecolector();
            }
        }, 0, TIEMPO_ALIMENTACION); 
    }

}