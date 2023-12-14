package com.concurrentech;

public interface Lock {
    
    /**
     * Metodo que bloquea el acceso a la
     * seccion critica.
     */
    public void lock();

    /**
     * Metodo que desbloque el acceso a la
     * seccion critica.
     */
    public void unlock();
}