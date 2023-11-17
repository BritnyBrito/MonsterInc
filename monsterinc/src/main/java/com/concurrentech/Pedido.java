package com.concurrentech;

public class Pedido {
    // Platillo que se pide
    private Platillo platillo;
    // Mesa asociada
    private Mesa mesa;

    // Constructor
    public Pedido(Platillo platillo, Mesa mesa) {
        this.platillo = platillo;
        this.mesa = mesa;
    }

    // Método que regresa el platillo
    public Platillo getPlatillo() {
        return platillo;
    }

    // Método que regresa la mesa
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
