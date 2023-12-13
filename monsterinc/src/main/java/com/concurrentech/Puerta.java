package com.concurrentech;

/**
 * Clase para representar puertas
 */
public class Puerta extends Utileria{
    // dirección del lugar al que lleva la puerta
    private String direccionPuerta;
    // información extra de la puerta: si hay una fiesta o pijamada
    private String extra;
    /**
     * Constructor de la clase Puerta
     * @param direccionPuerta direccionPuerta de la casa
     * @param tipo tipo de la puerta
     * @param extra extra de la puerta
     */
    public Puerta(String direccionPuerta, String tipo, String extra){
        super(tipo);
        this.direccionPuerta = direccionPuerta;
        this.extra = extra;
    }
    /**
     * Método que nos da la direccionPuerta de la puerta
     * @return direccionPuerta de la puerta
     */
    public String getDireccionPuerta() {
        return direccionPuerta;
    }
    /**
     * Método que cambia la direccionPuerta
     * @param direccionPuerta la nueva direccionPuerta
     */
    public void setDireccionPuerta(String direccionPuerta) {
        this.direccionPuerta = direccionPuerta;
    }
    /**
     * Método que nos da la info extra de la puerta
     * @return extra de la puerta
     */
    public String getExtra() {
        return extra;
    }
    /**
     * Método que cambia el extra de la puerta
     * @param extra el nuevo extra
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }
    
    @Override
    public String toString(){
        return "Dirección: " + direccionPuerta + ", tipo: " + tipo + ", extra: " + extra;
    }
}