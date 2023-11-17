package com.concurrentech;

import java.util.LinkedList;

public class Mesero {
    // Restaurante asociado
    private Cafeteria cafeteria;
    // Mesa asociada
    private Mesa mesa;

    // Constructor 
    public Mesero(Cafeteria cafeteria, Mesa mesa) {
        this.cafeteria = cafeteria;
        this.mesa = mesa;
    }

    // Método que crea un pedido y lo agrega a la fila de pedidos pendientes
    public void hacerPedido(Platillo platillo, Mesa mesa) {
        Pedido pedido = new Pedido(platillo, mesa);
        cafeteria.meterPedidoPendiente(pedido);
    }

    // Método que toma un pedido de la fila de pedidos listos y lo lleva a su mesa
    public void llevarPedido() {
        int indicePedido = cafeteria.buscarOrden(mesa);
        if (indicePedido != -1) {
            Pedido pedido = cafeteria.sacarPedidoListo(indicePedido);
            System.out.println("Llevando pedido de " + pedido.getPlatillo() + " a la mesa " + mesa);
        } else {
            System.out.println("No hay pedidos listos para la mesa " + mesa);
        }
    }
}
