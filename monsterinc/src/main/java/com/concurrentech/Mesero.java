package com.concurrentech;

public class Mesero {
    // Restaurante asociado
    private Cafeteria cafeteria;
    // Nombre 
    private String nombre;

    // Constructor 
    public Mesero(String nombre, Cafeteria cafeteria) {
        this.cafeteria = cafeteria;
        this.nombre = nombre;
    }

    // Método que crea un pedido y lo agrega a la fila de pedidos pendientes
    public void hacerPedido(Platillo platillo, Mesa mesa) {
        Pedido pedido = new Pedido(platillo, mesa);
        cafeteria.meterPedidoPendiente(pedido);
    }

    // Método que toma un pedido de la fila de pedidos listos y lo lleva a su mesa
    public void llevarPedido(Mesa mesa) {
        int indicePedido = cafeteria.buscarOrden(mesa);
        if (indicePedido != -1) {
            Pedido pedido = cafeteria.sacarPedidoListo(indicePedido);
            System.out.println("Mesero" + nombre + " llevando pedido de " + pedido.getPlatillo() + " a la mesa " + mesa);
        } else {
            System.out.println("No hay pedidos listos para la mesa " + mesa);
        }
    }
}
