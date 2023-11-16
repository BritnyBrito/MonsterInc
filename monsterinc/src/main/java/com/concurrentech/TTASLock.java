package com.concurrentech;

import java.util.concurrent.atomic.AtomicBoolean;

public class TTASLock implements Lock {

    AtomicBoolean state = new AtomicBoolean(false); // Se crea un objeto AtomicBoolean con valor inicial false

    @Override
    public void lock() {
        while (true) { // Ciclo infinito
            while (state.get()) // Ciclo mientras el estado es verdadero
                ; // No hace nada
            if (!state.getAndSet(true)) // Si el estado es falso, lo cambia a verdadero y retorna
                return;
        }
    }

    @Override
    public void unlock() {
        state.set(false); // Cambia el estado a falso
    }

}