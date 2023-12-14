package com.concurrentech;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase que representa una cafetería.
 * Permite agregar y obtener pedidos de la cafetería.
 */
public class Cafeteria {
    // Lista de platillos ofrecidos
    private LinkedList<Platillo> platillos;
    // Inventario, indica la cantidad de ingredientes
    private volatile HashMap<String, Integer> inventario;
    // Número máximo de clientes
    private int maxClientes;
    // Candado para el inventario
    private ReentrantLock inventarioLock;
    // Fila para pedidos listos
    private volatile LinkedList<Pedido> pedidosListos;
    // Candado para pedidos listos
    private ReentrantLock pedidosListosLock;
    // Fila para pedidos pendientes
    private volatile LinkedList<Pedido> pedidosPendientes;
    // Candado para pedidos pendientes
    private ReentrantLock pedidosPendientesLock;

    /**
     * Constructor de la clase Cafeteria
     * @param maxClientes el número máximo de clientes
     * @param platillos la lista de platillos ofrecidos
     * @param inventario el inventario inicial
     */
    public Cafeteria(int maxClientes, LinkedList<Platillo> platillos, HashMap<String, Integer> inventario) {
        this.platillos = platillos;
        this.inventario = inventario;
        this.maxClientes = maxClientes;
        this.inventarioLock = new ReentrantLock();
        this.pedidosListos = new LinkedList<Pedido>();
        this.pedidosListosLock = new ReentrantLock();
        this.pedidosPendientesLock = new ReentrantLock();
        this.pedidosPendientes = new LinkedList<Pedido>();
    }


    /**
     * Regresa el inventario de un ingrediente dado.
     *
     * @param ingrediente el ingrediente del cual se quiere saber el inventario
     * @return el inventario del ingrediente
     */
    public int getInventario(String ingrediente) {
        inventarioLock.lock();
        try {
            return inventario.get(ingrediente);
        } finally {
            inventarioLock.unlock();
        }
    }

    /**
     * Aumenta la cantidad de un ingrediente dado.
     * @param ingrediente el ingrediente del cual se quiere aumentar el inventario
     * @param cantidad la cantidad a aumentar
     */
    public void aumentarInventario(String ingrediente, int cantidad) {
        inventarioLock.lock();
        try {
            inventario.put(ingrediente, inventario.get(ingrediente) + cantidad);
        } finally {
            inventarioLock.unlock();
        }
    }

    /**
     * Disminuye la cantidad de un ingrediente dado.
     * @param ingrediente el ingrediente del cual se quiere disminuir el inventario
     * @param cantidad la cantidad a disminuir
     */
    public void disminuirInventario(String ingrediente, int cantidad) {
        inventarioLock.lock();
        try {
            inventario.put(ingrediente, inventario.get(ingrediente) - cantidad);
        } finally {
            inventarioLock.unlock();
        }
    }

    /**
     * Regresa el número máximo de clientes.
     * @return el número máximo de clientes
     */
    public int getMaxClientes() {
        return maxClientes;
    }

    /**
     * Método que saca un pedido de la fila de pedidos listos, dado su índice
     * @param indice el índice del pedido a sacar
     * @return el pedido sacado
     */
    public Pedido sacarPedidoListo(int indice) {
        pedidosListosLock.lock();
        try {
            return pedidosListos.remove(indice);
        } finally {
            pedidosListosLock.unlock();
        }
    }

    /**
     * Método que mete un pedido a la fila de pedidos listos
     * @param pedido el pedido a meter
     */
    public void meterPedidoListo(Pedido pedido) {
        pedidosListosLock.lock();
        try {
            pedidosListos.add(pedido);
        } finally {
            pedidosListosLock.unlock();
        }
    }

    /**
     * Método que saca un pedido de la fila de pedidos pendientes, protegido por candado
     * @return el pedido sacado
     */
    public Pedido sacarPedidoPendiente() {
        pedidosPendientesLock.lock();
        try {
            return pedidosPendientes.poll();
        } finally {
            pedidosPendientesLock.unlock();
        }
    }


    /**
     * Método que mete un pedido a la fila de pedidos pendientes, protegido por candado
     * @param pedido el pedido a meter
     */
    public void meterPedidoPendiente(Pedido pedido) {
        pedidosPendientesLock.lock();
        try {
            pedidosPendientes.add(pedido);
        } finally {
            pedidosPendientesLock.unlock();
        }
    }

    /**
     * Método que busca una orden en la fila de pedidos listos mediante su mesa.
     * @param mesa la mesa de donde proviene la orden a buscar
     * @return el índice de la orden en la lista, si no se encuentra, regresa -1
     */
    public int buscarOrden(Mesa mesa) {
        pedidosListosLock.lock();
        try {
            for (int i = 0; i < pedidosListos.size(); i++) {
                if (pedidosListos.get(i).getMesa().equals(mesa)) {
                    return i;
                }
            }
            return -1;
        } finally {
            pedidosListosLock.unlock();
        }
    }

    /**
     * Regresa la lista de platillos ofrecidos.
     * @return la lista de platillos ofrecidos
     */
    public LinkedList<Platillo> getPlatillos() {
        return platillos;
    }

    @Override
    public String toString() {
        return "Cafeteria [maxClientes=" + maxClientes + "]";
    }

}
