import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static Thread[] monstuosAcciones;
    public static Monstruo[] monstuos;
    public static ArrayList<Banno> bannos;
    public static int NUMERO_MOSTRUOS = 5;
    public static void main(String[] args) throws InterruptedException {
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