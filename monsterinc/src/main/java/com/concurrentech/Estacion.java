package com.concurrentech;

public class Estacion {
    private Monstruo monstruoA;
    private Monstruo monstruoB;
    private Tanque tanque;
    private Puerta puerta;

    public Estacion(Monstruo monstruoA, Monstruo monstruoB, Tanque tanque, Puerta puerta){
        this.monstruoA = monstruoA;
        this.monstruoB = monstruoB;
        this.tanque = tanque;
        this.puerta = puerta;
    }

    public Monstruo getMonstruoA() {
        return monstruoA;
    }

    public Monstruo getMonstruoB() {
        return monstruoB;
    }

    public Tanque getTanque() {
        return tanque;
    }

    public void setTanque(Tanque tanque) {
        this.tanque = tanque;
    }

    public Puerta getPuerta() {
        return puerta;
    }

    public void setPuerta(Puerta puerta) {
        this.puerta = puerta;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Estacion other = (Estacion) obj;
        if (monstruoA == null) {
            if (other.monstruoA != null)
                return false;
        } else if (!monstruoA.equals(other.monstruoA))
            return false;
        if (monstruoB == null) {
            if (other.monstruoB != null)
                return false;
        } else if (!monstruoB.equals(other.monstruoB))
            return false;
        if (tanque == null) {
            if (other.tanque != null)
                return false;
        } else if (!tanque.equals(other.tanque))
            return false;
        if (puerta == null) {
            if (other.puerta != null)
                return false;
        } else if (!puerta.equals(other.puerta))
            return false;
        return true;
    }
}
