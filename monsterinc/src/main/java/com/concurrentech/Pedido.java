package com.concurrentech;

/**
 * Clase que representa un pedido.
 * Permite simular el hacer pedidos a la cafeteria.
 */
public class Pedido {
    // Platillo que se pide
    private Platillo platillo;
    // Mesa asociada
    private Mesa mesa;

    /**
     * Constructor de la clase Pedido
     * @param platillo el platillo a pedir
     * @param mesa la mesa a la que se le hace el pedido
     */
    public Pedido(Platillo platillo, Mesa mesa) {
        this.platillo = platillo;
        this.mesa = mesa;
    }


    /**
     * Método para obtener el platillo
     * @return el platillo
     */
    public Platillo getPlatillo() {
        return platillo;
    }


    /**
     * Método para obtener la mesa
     * @return la mesa
     */
    public Mesa getMesa() {
        return mesa;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (platillo == null) {
            if (other.platillo != null)
                return false;
        } else if (!platillo.equals(other.platillo))
            return false;
        if (mesa == null) {
            if (other.mesa != null)
                return false;
        } else if (!mesa.equals(other.mesa))
            return false;
        return true;
    }
}
