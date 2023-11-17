package com.concurrentech;

import java.util.concurrent.locks.ReentrantLock;

public class Mesa {
    // Número de mesa
    private int numero;
    // Capacida de la mesa
    private int tamanno;
    // Número de clientes en la mesa
    private volatile int ocupados;
    // Candado que protege la mesa
    private ReentrantLock lock;
    // Cafeteria asociada
    private Cafeteria cafeteria;

    // Constructor
    public Mesa(int numero, int tamanno, Cafeteria cafeteria) {
        this.numero = numero;
        this.tamanno = tamanno;
        this.ocupados = 0;
        this.lock = new ReentrantLock();
        this.cafeteria = cafeteria;
    }

    // Método para agregar un cliente a la mesa, protegido por candado
    // En caso de que el numero de ocupados sea igual al tamaño de la mesa, 
    // lanzamos una excepción
    public void agregarCliente() {
        lock.lock();
        try {
            if (ocupados == tamanno) {
                throw new Exception("La mesa está llena");
            }
            ocupados++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    // Método para quitar un cliente de la mesa, protegido por candado
    public void quitarCliente() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            ocupados--;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "" + numero;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mesa other = (Mesa) obj;
        if (numero != other.numero)
            return false;
        if (tamanno != other.tamanno)
            return false;
        if (ocupados != other.ocupados)
            return false;
        if (lock == null) {
            if (other.lock != null)
                return false;
        } else if (!lock.equals(other.lock))
            return false;
        if (cafeteria == null) {
            if (other.cafeteria != null)
                return false;
        } else if (!cafeteria.equals(other.cafeteria))
            return false;
        return true;
    }
}
