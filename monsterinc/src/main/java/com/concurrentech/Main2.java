package com.concurrentech;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;

public class Main2 {
    public static Thread[] monstuosAcciones;
    public static Monstruo[] monstuos;
    public static ArrayList<Banno> bannos;
    public static int NUMERO_MOSTRUOS = 5;
    public static Recolector recolect;
    public static Thread revisaRecolector;
    private static Timer timer;
    private static void alimentaCiudad(){
        timer = new Timer();

        // Schedule the task to run every 30 seconds (30,000 milliseconds)
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                recolect.vaciaRecolector();
            }
        }, 0, 30 * 1000); // Initial delay is 0, and the period is 30 seconds
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        recolect = new Recolector();
        revisaRecolector = new Thread(() -> alimentaCiudad());
        revisaRecolector.start();

        // Sección para test de baños y vestidores
        /** 
        inicializaBanos();
        inicializaMonstruos();
        initThreads();
        for (Thread t : monstuosAcciones) {
            t.start();
        }
        for (Thread t : monstuosAcciones) {
            t.join();
        }
        System.out.println("Yap" );
         */
        
        // Sección para test de cafetería

        // Creamos la cafetería
        Cafeteria cafeteria = inicializaCafeteria();

        // Creamos los meseros
        LinkedList<Mesero> meseros = inicializaMeseros(cafeteria);

        // Creamos las mesas
        LinkedList<Mesa> mesas = inicializaMesas(cafeteria, meseros);

        // Creamos los chefs
        LinkedList<Chef> chefs = inicializaChefs(cafeteria);

        // Creamos los hilos de los chefs
        LinkedList<Thread> chefsThreads = new LinkedList<Thread>();
        for (Chef chef : chefs) {
            Thread thread = new Thread(chef);
            thread.setName(chef.getNombre());
            chefsThreads.add(thread);
        }

        // Creamos los hilos de las mesas
        LinkedList<Thread> mesasThreads = new LinkedList<Thread>();
        for (Mesa mesa : mesas) {
            Thread thread = new Thread(mesa);
            thread.setName(String.format("Mesa %d", mesa));
            mesasThreads.add(thread);
        }

        // Iniciamos los hilos de los chefs
        for (Thread thread : chefsThreads) {
            thread.start();
        }

        // Iniciamos los hilos de las mesas
        for (Thread thread : mesasThreads) {
            thread.start();
        }

        // Llevamos pedidos a las mesas cada 2 segundos
        while (true) {
            Thread.sleep(2000);
            for (Mesa mesa : mesas) {
                Mesero mesero = mesa.getMesero();
                mesero.llevarPedido(mesa);
            }
            // Terminamos si usuario presiona enter
            if (System.in.available() > 0) {
                break;
            }
        }

        // Finalizamos los hilos de los chefs
        for (Thread thread : chefsThreads) {
            thread.join();
        }
        
        // Finalizamos los hilos de las mesas
        for (Thread thread : mesasThreads) {
            thread.join();
        }
    }

    private static void inicializaMonstruos(){
        monstuos = new Monstruo[NUMERO_MOSTRUOS];
        monstuos[0] = new Monstruo(new ArrayList<Integer>(Arrays.asList(1,2)),"123");
        monstuos[1] = new Monstruo(new ArrayList<Integer>(Arrays.asList(1,2)),"dcd");
        monstuos[2] = new Monstruo(new ArrayList<Integer>(Arrays.asList(1,0)),"ew");
        monstuos[3] = new Monstruo(new ArrayList<Integer>(Arrays.asList(2)),"43r");
        monstuos[4] = new Monstruo(new ArrayList<Integer>(Arrays.asList(0)),"4r");
    }

    private static void inicializaBanos(){
        bannos = new ArrayList<>();
        bannos.add(new Banno(NUMERO_MOSTRUOS, 1, "1"));
        bannos.add(new Banno(NUMERO_MOSTRUOS, 2, "2"));
        bannos.add(new Banno(NUMERO_MOSTRUOS, 3, "3"));
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


    private static int j = 0;
    private static void initThreads() {
        monstuosAcciones = new Thread[NUMERO_MOSTRUOS];
        for (int i = 0; i < NUMERO_MOSTRUOS; ++i) {
            monstuosAcciones[i] = new Thread(() -> adquiereRondas());
            monstuosAcciones[i].setName(String.format("%d", i));
            //j++;
        }
    }
    private static void adquiereRondas() {
        //int i = Integer.valueOf(Thread.currentThread().getName());
        Monstruo m = monstuos[j];
        j++;
        m.simulaBanno(bannos);
        m.simulaVestidor(String.valueOf(j));
    }



}