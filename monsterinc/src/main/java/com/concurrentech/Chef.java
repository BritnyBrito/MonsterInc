package com.concurrentech;

public class Chef {
    // Boolean que indica si es un chef profesional
    private boolean esProfesional;
    private Cafeteria cafeteria;

    // Constructor 
    public Chef(boolean esProfesional, Cafeteria cafeteria) {
        this.esProfesional = esProfesional;
        this.cafeteria = cafeteria;
    }

    // Método que produce platillos 
    // El platillo se obtiene de la lista de pedidos pendientes
    // Al producir un platillo, se disminuye el inventario y se inserta en la fila de pedidos listos
    public void cocina() {
        Pedido pedido = cafeteria.sacarPedidoPendiente();
        Platillo platillo = pedido.getPlatillo();
        // En caso de que el platillo requiera chef profesional y no lo seamos, regrea null
        if (platillo.requiereProfesional() && !esProfesional) {
            return;
        }
        // Verificamos que todos los ingredientes que solicita el platillo estén disponibles
        // En caso de que sí, se produce el platillo y lo regresamos
        // En caso de que no, se regresa null
        for (String ingrediente : platillo.getIngredientes().keySet()) {
            if (cafeteria.getInventario(ingrediente) < platillo.getCantidad(ingrediente)) {
                return;
            } 
        }
        // Si hubo de todos los ingredientes, se disminuye el inventario
        for (String ingrediente : platillo.getIngredientes().keySet()) {
            cafeteria.disminuirInventario(ingrediente, platillo.getCantidad(ingrediente));
        }
        // Se inserta el platillo en la fila de pedidos listos
        cafeteria.meterPedidoListo(new Pedido(platillo, pedido.getMesa()));
    }
    
}
