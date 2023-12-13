package com.concurrentech;

import java.util.ArrayList;
/**
    Clase para representar los vestidores
*/
public class Vestidor {
    // constraseña del vestidor
    private String contrasena;
    // lista que contiene los elementos guardados en el vestidor 
    private ArrayList<String> elementos;
    /**
     * Constructor de un vestidor
     * @param contrasena
     */
    public Vestidor(String contrasena){
        this.elementos = new ArrayList<>();
        this.contrasena = contrasena;
    }
    /**
     * Método para agregar un elemento al vestidor
     * @param elemento elemento a agregar al vestidor
     * @param contrasena contrasenaseña del vestidor
     */
    public void agregaElemento(String elemento, String contrasena){
        // verifica que en efecto la contaseña sea correcta
        if(this.contrasena.equals(contrasena)){
            elementos.add(elemento);
            System.out.println(elemento + " agregado a casillero" );
        }
    }
    /**
     * Método para eliminar un elemento al vestidor
     * @param elemento elemento a eliminar al vestidor
     * @param contrasena contrasenaseña del vestidor
     */
    public void eliminaElemento(String elemento, String contrasena){
        // verifica que en efecto la contaseña sea correcta
        if(this.contrasena.equals(contrasena)){
            if (elementos.contains(elemento)) {
                elementos.remove(elemento);
                //Integer.valueOf(
                System.out.println(elemento + " eliminado");
            } else {
                System.out.println("No esta " + elemento + " que quieres eliminar");
            }
        }
        
    }
    /**
     * Método para modificar un elemento al vestidor
     * @param elemento elemento a modificar del vestidor
     * @param elementoNuevo elemento modificado del vestidor
     * @param contrasena contrasenaseña del vestidor
     */
    public void modificaElemento(String elemento, String elementoNuevo, String contrasena){
        // verifica que en efecto la contaseña sea correcta
        if(this.contrasena.equals(contrasena)){
            if (elementos.contains(elemento)) {
                int i = elementos.indexOf(elemento);
                elementos.set(i, elementoNuevo);
                System.out.println(elemento + " modificado");
            } else {
                System.out.println("No esta " + elemento + " que quieres modificar");
            }
        }
        
    }
}
