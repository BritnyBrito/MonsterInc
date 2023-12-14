package com.concurrentech;

/**
 * Clase que representa un chef.
 * Permite producir platillos.
 */
public class Chef implements Runnable {
    // Boolean que indica si es un chef profesional
    private boolean esProfesional;
    // Cafetería asociada
    private Cafeteria cafeteria;
    // Nombre
    private String nombre;

    /**
     * Constructor de la clase Chef
     * @param esProfesional boolean que indica si es un chef profesional
     * @param cafeteria la cafetería asociada
     * @param nombre el nombre del chef
     */
    public Chef(boolean esProfesional, Cafeteria cafeteria, String nombre) {
        this.esProfesional = esProfesional;
        this.cafeteria = cafeteria;
        this.nombre = nombre;
    }

    /**
     * Método para simular el chef
     */
    @Override
    public void run() {
        // Cocinamos cada 6 segundos
        while (true) {
            try {
                Thread.sleep(6000);
                cocina();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método que produce platillos.
     * El platillo se obtiene de la lista de pedidos pendientes.
     * Al producir un platillo, se disminuye el inventario y se inserta en la fila de pedidos listos.
     */
    public void cocina() {
        Pedido pedido = cafeteria.sacarPedidoPendiente();
        Platillo platillo = pedido.getPlatillo();
        // En caso de que el platillo requiera chef profesional y no lo seamos, regresa null
        if (platillo.requiereProfesional() && !esProfesional) {
            // Notificamos
            System.out.println("Chef " + nombre + " no puede producir " + platillo + " porque requiere chef profesional");
            return;
        }
        // Verificamos que todos los ingredientes que solicita el platillo estén disponibles
        // En caso de que sí, se produce el platillo y lo regresamos
        // En caso de que no, se regresa null
        for (String ingrediente : platillo.getIngredientes().keySet()) {
            if (cafeteria.getInventario(ingrediente) < platillo.getCantidad(ingrediente)) {
                // Notificamos
                System.out.println("Chef " + nombre + " no puede producir " + platillo + " porque no hay suficiente " + ingrediente);
                return;
            } 
        }
        // Si hubo de todos los ingredientes, se disminuye el inventario
        for (String ingrediente : platillo.getIngredientes().keySet()) {
            cafeteria.disminuirInventario(ingrediente, platillo.getCantidad(ingrediente));
        }
        // Se inserta el platillo en la fila de pedidos listos
        cafeteria.meterPedidoListo(new Pedido(platillo, pedido.getMesa()));

        // Notificamos la producción del platillo
        System.out.println("Chef " + nombre + " produjo " + platillo + " para la mesa " + pedido.getMesa());
    }

    /**
     * Regresa el nombre del chef
     * @return el nombre del chef
     */
    public String getNombre() {
        return nombre;
    }
    
}
