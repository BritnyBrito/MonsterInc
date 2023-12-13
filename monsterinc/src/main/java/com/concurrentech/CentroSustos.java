package com.concurrentech;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class CentroSustos {
    private AlmacenPuertas almacenPuertas;
    private AlmacenTanques almacenTanques;
    int numMaxTanques;   
    int numTanques;
    LinkedList<Tanque> tanquesLlenos; 
    LinkedList<Estacion> estaciones;
    ReentrantLock candado;

    public CentroSustos(AlmacenPuertas almacenPuertas, AlmacenTanques almacenTanques){
        this.almacenPuertas = almacenPuertas;
        this.almacenTanques = almacenTanques;
        numMaxTanques = 1000;
        numTanques = 0;
        tanquesLlenos = new LinkedList<Tanque>();
        estaciones = new LinkedList<Estacion>();
        candado = new ReentrantLock();
    }


    // Método para simular un susto. Dado un par de monstruos,
    // selecciona un tanque de los disponibles y una puerta
    // del almacen, y lo asigna a una estacion. En caso 
    // de ya existir una estacion con los mismos monstruos,
    // se asigna el tanque y la puerta a esa estacion.
    // Se llena el tanque y se manda a la lista de tanques llenos.
    // En caso de que alguno de los monstruos sea Sully,
    // se fabrica un maxitanque y se llena.
    // Si no hay tanques disponibles, se deben fabricar.
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

    // Metodo para regresar los tanques llenos y vaciar la lista
    public LinkedList<Tanque> getTanquesLlenos() {
        LinkedList<Tanque> tanques = tanquesLlenos;
        tanquesLlenos = new LinkedList<Tanque>();
        numTanques = 0;
        return tanques;
    }
}
