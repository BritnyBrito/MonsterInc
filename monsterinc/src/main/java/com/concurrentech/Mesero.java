package com.concurrentech;

/**
 * Clase que representa un mesero.
 * Permite simular el hacer pedidos a la cafeteria.
 */
public class Mesero {
    // Restaurante asociado
    private Cafeteria cafeteria;
    // Nombre 
    private String nombre;

    /**
     * Constructor de la clase Mesero
     * @param nombre el nombre del mesero
     * @param cafeteria la cafeteria asociada
     */
    public Mesero(String nombre, Cafeteria cafeteria) {
        this.cafeteria = cafeteria;
        this.nombre = nombre;
    }

    /**
     * Método para hacer un pedido a la cafeteria
     * @param platillo el platillo a pedir
     * @param mesa la mesa a la que se le hace el pedido
     */
    public void hacerPedido(Platillo platillo, Mesa mesa) {
        Pedido pedido = new Pedido(platillo, mesa);
        cafeteria.meterPedidoPendiente(pedido);
    }


    /**
     * Método para llevar un pedido a una mesa
     * @param mesa la mesa a la que se le lleva el pedido
     */
    public void llevarPedido(Mesa mesa) {
        int indicePedido = cafeteria.buscarOrden(mesa);
        if (indicePedido != -1) {
            Pedido pedido = cafeteria.sacarPedidoListo(indicePedido);
            System.out.println("Mesero " + nombre + " llevando pedido de " + pedido.getPlatillo() + " a la mesa " + mesa);
        } else {
            System.out.println("No hay pedidos listos para la mesa " + mesa);
        }
    }
}
