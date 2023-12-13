package com.concurrentech;
/**
    Clase para representar a los baños 
*/
public class Banno extends Filtro{
    // tipo de monstruo que usa el baño
    private String tipoMonstruo;
    // identificador del baño
    private int identificador;
    /**
     *  Constructor de la clase Banno
     * @param hilos cuantos hilos a lo más pueden intentar ir al baño
     * @param maxHilosConcurrentes cuantos monstruos pueden usar el baño
     * @param identificador identificador del baño
     */
    public Banno(int hilos, int maxHilosConcurrentes, int identificador) {
        super( hilos,  maxHilosConcurrentes);
        this.identificador = identificador;
        this.tipoMonstruo = "NA";
    }
    /**
     * Método que nos dice el tipoMonstruo
     * @return tipoMonstruo del monstruo
     */
    public String getTipoMonstruo() {
        return tipoMonstruo;
    }
    /**
     * Método que cambia el tipoMonstruo
     * @param tipoMonstruo nuevo tipoMonstuo
     */
    public void setTipoMonstruo(String tipoMonstruo) {
        this.tipoMonstruo = tipoMonstruo;
    }
    /**
     * Método que nos dice el identificador
     * @return el identificador del monstruo
     */
    public int getIdentificador() {
        return identificador;
    }
    /**
     * Método que cambia el identificador
     * @param identificador nuevo identificador
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

}
