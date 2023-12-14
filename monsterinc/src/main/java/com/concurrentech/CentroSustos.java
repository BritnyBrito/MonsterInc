package com.concurrentech;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase que representa un centro de sustos.
 * Permite simular el llenado de tanques con sustos.
 */
public class CentroSustos {
    private AlmacenPuertas almacenPuertas;
    private AlmacenTanques almacenTanques;
    int numMaxTanques;   
    int numTanques;
    LinkedList<Tanque> tanquesLlenos; 
    LinkedList<Estacion> estaciones;
    ReentrantLock candado;

    /**
     * Constructor de la clase CentroSustos
     * @param almacenPuertas el almacen de puertas asociado
     * @param almacenTanques el almacen de tanques asociado
     */
    public CentroSustos(AlmacenPuertas almacenPuertas, AlmacenTanques almacenTanques){
        this.almacenPuertas = almacenPuertas;
        this.almacenTanques = almacenTanques;
        numMaxTanques = 1000;
        numTanques = 0;
        tanquesLlenos = new LinkedList<Tanque>();
        estaciones = new LinkedList<Estacion>();
        candado = new ReentrantLock();
    }



    /**
     * Método para simular el centro de sustos
     * @param m1 el primer monstruo a participar
     * @param m2 el segundo monstruo a participar
     */
    public void susto(Monstruo m1, Monstruo m2) {
        // Seleccionamos un tanque estandar
        Tanque tanque = almacenTanques.getTanque("Estandar");
        // Seleccionamos un tipo de puerta al azar de los existentes
        // y con esto obtenemos una puerta del almacen
        String tipo = Math.random() < 0.5 ? "adulto" : "niño";
        Puerta puerta = almacenPuertas.getPuerta(tipo);
        // Buscamos una estacion con los mismos monstruos
        Estacion estacion = null;
        for (Estacion e : estaciones) {
            if (e.getMonstruoA().equals(m1) && e.getMonstruoB().equals(m2)) {
                estacion = e;
                break;
            }
        }
        // Si no existe una estacion con los mismos monstruos,
        // creamos una nueva
        if (estacion == null) {
            estacion = new Estacion(m1, m2, tanque, puerta);
            estaciones.add(estacion);
        }
        // Asignamos el tanque y la puerta a la estacion
        estacion.setTanque(tanque);
        estacion.setPuerta(puerta);
        // Llenamos el tanque
        tanque.setEnergia(tanque.getCapacidad());
        // Si alguno de los monstruos es Sully, llenamos un maxitanque
        if (m1.getTipo().equals("Sully") || m2.getTipo().equals("Sully")) {
            Tanque maxitanque = almacenTanques.getTanque("Maxitanque");
            maxitanque.setEnergia(maxitanque.getCapacidad());
            tanquesLlenos.add(maxitanque);
            // Notificamos el susto
            System.out.println("Sully ha hecho un susto y llenado un maxitanque!");
        } else {
            System.out.println("Los monstruos han hecho un susto y llenado un tanque!");
        }
        // Quitamos el tanque y la puerta de la estacion
        estacion.setTanque(null);
        estacion.setPuerta(null);
        // Agregamos el tanque a la lista de tanques llenos
        candado.lock();
        if (numTanques < numMaxTanques) {
            tanquesLlenos.add(tanque);
            numTanques++;
        }
        candado.unlock();
        // Regresamos la puerta a su almacen
        almacenPuertas.agregaPuerta(puerta);
    }


    /**
     * Regresa la lista de tanques llenos
     * @return la lista de tanques llenos
     */
    public LinkedList<Tanque> getTanquesLlenos() {
        LinkedList<Tanque> tanques = tanquesLlenos;
        tanquesLlenos = new LinkedList<Tanque>();
        numTanques = 0;
        return tanques;
    }
}
