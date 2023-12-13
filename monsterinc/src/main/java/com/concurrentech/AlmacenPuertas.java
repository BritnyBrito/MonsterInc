package com.concurrentech;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class AlmacenPuertas {
    // Lista de puertas de niños
    private ArrayList<Puerta> puertasNinos;
    // Lista de puertas de adultos
    private ArrayList<Puerta> puertasAdultos;
    // Candado para proteger el almacén
    private ReentrantLock candado;

    /**
     * Constructor de la clase AlmacenPuertas
     */
    public AlmacenPuertas(){
        puertasNinos = new ArrayList<Puerta>();
        puertasAdultos = new ArrayList<Puerta>();
        candado = new ReentrantLock();
    }

    /**
     * Método que agrega una puerta al almacén
     * @param puerta la puerta a agregar
     */
    public void agregaPuerta(Puerta puerta){
        // Se obtiene el tipo de la puerta
        String tipo = puerta.getTipo();
        // Se protege el almacén
        candado.lock();
        // Se cambia estado de la puerta a disponible
        puerta.setEstado("disponible");
        // Se agrega la puerta dependiendo su tipo
        if(tipo.equals("nino")){
            puertasNinos.add(puerta);
        }else{
            puertasAdultos.add(puerta);
        }
        // Se desbloquea el almacén
        candado.unlock();
    }
    
    /**
     * Método que regresa una puerta del almacén
     * dado un tipo de puerta, la puerta debe estar disponible.
     * En caso de no encontrar una puerta del tipo dado, o
     * de que no haya puertas disponibles, el método
     * espera hasta que haya una puerta disponible.
     * Al sacar una puerta del almacen, se elimina de la lista.
     * @param tipo el tipo de puerta a buscar
     * @return la puerta encontrada
     */
    public Puerta getPuerta(String tipo){
        // Se protege el almacén
        candado.lock();
        // Se busca una puerta del tipo dado
        Puerta puerta = null;
        if(tipo.equals("nino")){
            for (Puerta p : puertasNinos) {
                if (p.getEstado().equals("disponible")) {
                    puerta = p;
                    break;
                }
            }
        }else{
            for (Puerta p : puertasAdultos) {
                if (p.getEstado().equals("disponible")) {
                    puerta = p;
                    break;
                }
            }
        }
        // Si no hay puertas disponibles, se espera
        while(puerta == null){
            try {
                candado.unlock();
                Thread.sleep(1000);
                candado.lock();
                if(tipo.equals("nino")){
                    for (Puerta p : puertasNinos) {
                        if (p.getEstado().equals("disponible")) {
                            puerta = p;
                            break;
                        }
                    }
                }else{
                    for (Puerta p : puertasAdultos) {
                        if (p.getEstado().equals("disponible")) {
                            puerta = p;
                            break;
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Se cambia el estado de la puerta a ocupada
        puerta.setEstado("ocupada");
        // Se elimina la puerta de la lista
        if(tipo.equals("nino")){
            puertasNinos.remove(puerta);
        }else{
            puertasAdultos.remove(puerta);
        }
        // Se desbloquea el almacén
        candado.unlock();
        // Se regresa la puerta
        return puerta;
    }
}