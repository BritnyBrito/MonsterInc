package com.concurrentech;

/**
 * Clase que representa una estación de trabajo.
 * Permite simular el llenado de tanques con sustos o con risas.
 */
public class Estacion {
    private Monstruo monstruoA;
    private Monstruo monstruoB;
    private Tanque tanque;
    private Puerta puerta;

    /**
     * Constructor de la clase Estacion
     * @param monstruoA el primer monstruo a participar
     * @param monstruoB el segundo monstruo a participar
     * @param tanque el tanque a utilizar
     * @param puerta la puerta a utilizar
     */
    public Estacion(Monstruo monstruoA, Monstruo monstruoB, Tanque tanque, Puerta puerta){
        this.monstruoA = monstruoA;
        this.monstruoB = monstruoB;
        this.tanque = tanque;
        this.puerta = puerta;
    }


    /**
     * Metodo para obtener el primer monstruo
     * @return el primer monstruo
     */
    public Monstruo getMonstruoA() {
        return monstruoA;
    }

    /**
     * Metodo para obtener el segundo monstruo
     * @return el segundo monstruo
     */
    public Monstruo getMonstruoB() {
        return monstruoB;
    }

    /**
     * Metodo para obtener el tanque
     * @return el tanque
     */
    public Tanque getTanque() {
        return tanque;
    }

    /**
     * Metodo para configurar el tanque
     * @param tanque el tanque a configurar
     */
    public void setTanque(Tanque tanque) {
        this.tanque = tanque;
    }

    /**
     * Metodo para obtener la puerta
     * @return la puerta
     */
    public Puerta getPuerta() {
        return puerta;
    }

    /**
     * Metodo para configurar la puerta
     * @param puerta la puerta a configurar
     */
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
