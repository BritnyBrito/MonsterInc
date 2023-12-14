package com.concurrentech;

/**
 * Clase que modela el Algoritmo del Filtro Modificado
 * 
 * @version 1.0
 * @author Kassandra Mirael
 */
public class Filtro implements Semaphore {
    private volatile int[] level;
    private volatile int[] victim;
    private int maxHilosConcurrentes;

    /**
     * Constructor del Filtro
     * 
     * @param hilos                El numero de Hilos Permitidos
     * @param maxHilosConcurrentes EL numero de hilos concurrentes simultaneos
     */
    public Filtro(int hilos, int maxHilosConcurrentes) {
        this.maxHilosConcurrentes = maxHilosConcurrentes;
        level = new int[hilos];
        victim = new int[hilos - maxHilosConcurrentes];
        for (int i = 0; i < hilos; i++) {
            level[i] = 0;
        }
    }

    @Override
    public int getPermitsOnCriticalSection() {
        return maxHilosConcurrentes;
    }

    @Override
    public void acquire() {
        int me = Integer.valueOf(Thread.currentThread().getName());
        int hilos = victim.length;
        for (int i = 0; i < hilos; i++) {
            level[me] = i; // mostrar que llegamos al nivel
            victim[i] = me; // nos ponemos como víctim del nivel

            while (existeK(i, me) && victim[i] == me)
                ; // esperar a que otro sea la victima del nivel
        }

    }

    private boolean existeK(int i, int me) {
        for (int k = 0; k < level.length; k++) {
            if (i > 0 && k != me && level[k] >= i) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void release() {
        int me = Integer.valueOf(Thread.currentThread().getName());
        level[me] = 0;
    }

}