package com.concurrentech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;

/**
 * Clase que representa la ciudad de Monstruopolis.
 * Permite simular el funcionamiento de la ciudad.
 */
public class Main {
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
    private static int TIEMPO_ALIMENTACION= 10 * 1000;
    // Iteraciones de mesereado
    private static int ITERACIONES = 100;

    /**
     * Método main de la clase Main
     * @param args argumentos de la línea de comandos
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        /// ALMACENES
        AlmacenPuertas almacenPuertas = new AlmacenPuertas();
        AlmacenTanques almacenTanques = new AlmacenTanques();

        // FABRICA DE PUERTAS
        fabricaPuertas = new FabricaPuertas(almacenPuertas);
        fabricaPuertasAcciones = new Thread(() -> fabricaPuertas.inicia());
        fabricaPuertasAcciones.start();
        
        // FABRICA DE TANQUES
        fabricaTanques = new FabricaTanques(almacenTanques);
        fabricaTanquesAcciones = new Thread(() -> fabricaTanques.inicia());
        fabricaTanquesAcciones.start();

        // CENTROS
        CentroRisas centroRisas = new CentroRisas(almacenPuertas, almacenTanques);
        CentroSustos centroSustos = new CentroSustos(almacenPuertas, almacenTanques);
        
        // Creamos recolector
        recolector = new Recolector(almacenTanques, centroSustos, centroRisas);
        Thread recolectorAcciones = new Thread(recolector);
        recolectorAcciones.start();

        // Iniciamos el timer para alimentar la ciudad
        timer = new Timer();
        revisaRecolector = new Thread(() -> alimentaCiudad());
        revisaRecolector.start();

        // PARA CENTRO REPACION
        CentroReparacion centroReparacion = new CentroReparacion(almacenPuertas, almacenTanques);
        Thread centroReparacionAcciones = new Thread(centroReparacion);
        centroReparacionAcciones.start();


        // FIN TEST
        
        // FIN TEST
        // VESTIDORES Y BANNOS
        inicializaBanos();
        inicializaMonstruos();
        initThreads();
        for (Thread t : monstruosAcciones) {
            t.start();
        }
        
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
            thread.setName(String.format("Mesa %s", mesa.toString()));
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

        // Llevamos pedidos a las mesas y hacemos trabajar
        // a los centros cada 3 segundos
        // durante ITERACIONES_MESEREADO iteraciones
        while (ITERACIONES > 0) {
            ITERACIONES--;
            for (Mesa mesa : mesas) {
                Mesero mesero = mesa.getMesero();
                mesero.llevarPedido(mesa);
            }
            // Seleccionamos dos monstruos al azar
            // y los mandamos aleatoriamente a un centro
            int i = (int) (Math.random() * NUMERO_MOSTRUOS);
            int k = (int) (Math.random() * NUMERO_MOSTRUOS);
            if (Math.random() < 0.5) {
                centroRisas.risas(monstruos[i], monstruos[k]);
            } else {
                centroSustos.susto(monstruos[i], monstruos[k]);
            }
            // Terminamos si usuario presiona enter
            if (System.in.available() > 0) {
                break;
            }
            Thread.sleep(3000);
        }

        // Finalizamos los hilos de los chefs
        for (Thread thread : chefsThreads) {
            thread.join();
        }
        
        // Finalizamos los hilos de las mesas
        for (Thread thread : mesasThreads) {
            thread.join();
        }

        // Finalizamos el recolector
        revisaRecolector.join();

        // Finalizamos el centro de reparación
        centroReparacionAcciones.join();
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

    /**
     * Método para crear chefs, creamos 5 chefs
     * @param cafeteria la cafetería a la que pertenecen los chefs
     * @return una lista con los chefs creados
     */
    private static LinkedList<Chef> inicializaChefs(Cafeteria cafeteria) {
        LinkedList<Chef> chefs = new LinkedList<Chef>();
        chefs.add(new Chef(false, cafeteria, "Juan"));
        chefs.add(new Chef(false, cafeteria, "Pedro"));
        chefs.add(new Chef(true, cafeteria, "Luis"));
        chefs.add(new Chef(false, cafeteria, "Ana"));
        chefs.add(new Chef(false, cafeteria, "Maria"));
        return chefs;
    }

    /**
     * Método para crear meseros, creamos 10 meseros
     * @param cafeteria la cafetería a la que pertenecen los meseros
     * @return una lista con los meseros creados
     */
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
    

    /**
     * Método para crear mesas, creamos 10 mesas
     * @param cafeteria la cafetería a la que pertenecen las mesas
     * @param meseros la lista de meseros que atenderán las mesas
     * @return una lista con las mesas creadas
     */
    private static LinkedList<Mesa> inicializaMesas(Cafeteria cafeteria, LinkedList<Mesero> meseros) {
        LinkedList<Mesa> mesas = new LinkedList<Mesa>();
        for (int i = 0; i < 10; i++) {
            mesas.add(new Mesa(i, 4, cafeteria, meseros.get(i)));
        }
        return mesas;
    }

    /**
     * Método para inicializar la cafetería
     * @return la cafetería creada
     */
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
                put("Pan", 100);
                put("Carne", 100);
                put("Lechuga", 100);
                put("Tomate", 100);
                put("Queso", 100);
                put("Salchicha", 100);
                put("Agua", 100);
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