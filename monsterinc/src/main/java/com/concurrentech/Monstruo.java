package com.concurrentech;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
    Clase para representar a los monstruos
*/
public class Monstruo {
    // lista de identificadores de baños a los que puede entrar este monstrio
    private ArrayList<Integer> tiposBannos;
    // contraseña del vestidor 
    private String contrasena;
    // Vestido único del monstruo
    private Vestidor locker;
    // tipo de monstruo
    private String tipo;
    // candado para registrar su tipo
    private static Lock lock = new ReentrantLock();
    /**
    *   Constructor del monstruo
    *   @param tiposBanno baños a los que puede acceder
    *   @param contrasena contraseña del vestidor
    *   @param tipo tipo de monstruo
    */
    public Monstruo(ArrayList<Integer> tiposBanno, String contrasena, String tipo){
        this.tiposBannos =  tiposBanno;
        this.contrasena = contrasena;
        this.tipo = tipo;
        locker = new Vestidor(contrasena);
    }

    /**
    *   Método para simular una ida al vestidor en la cual se agrega un 
    *   elemento al vestidor
    *   @param elemento: el elemento a agregar al vestidor
    */
    public void simulaVestidor(String elemento){
        locker.agregaElemento(elemento, contrasena);
        locker.modificaElemento(elemento, elemento + " nuevo", contrasena);
        locker.eliminaElemento(elemento + " nuevo", contrasena);
    }
    /**
    *   Método para simular una ida al baño 
    *   @param bannos lista de todos los baños disponibles 
    */
    public void simulaBanno(ArrayList<Banno> bannos){
        idaBanno(bannos, seleccionaBanno(bannos));
    }
    /**
    *   Método para simular una ida a un baño especifico 
    *   @param banno el baño al que se va a ir
    *   @param bannos lista de todos los baños disponibles 
    */
    private  void idaBanno(ArrayList<Banno> bannos,Banno banno){
        String tipoBanno = String.valueOf(banno.getIdentificador());
        System.out.println("tratando de entrar al banno: " + tipoBanno);
        banno.acquire();
        //revisamos el banno tipo
        try{
            lock.lock();
            banno.setTipoMonstruo(tipo);
            if (!banno.getTipoMonstruo().equals("NA") && !banno.getTipoMonstruo().equals(tipo)){
                int eleccionPos = tiposBannos.indexOf(banno.getIdentificador());
                tiposBannos.remove(eleccionPos);
                banno = seleccionaBanno(bannos);
                idaBanno(bannos, banno);
                return;
            }
        }finally{
            lock.unlock();
        }
        System.out.println("Entrando al banno: " + tipoBanno);
        banno.release();
        System.out.println("Saliendo del banno: " + tipoBanno);
    }
    /**
    *   Método para elegir a que baño ir: un que admita monstruos nuestro tipo
    *   @param bannos lista de todos los baños disponibles 
    */
    private Banno seleccionaBanno(ArrayList<Banno> bannos){
        Random rand = new Random(); 
        int eleccionPos = rand.nextInt(tiposBannos.size()); 
        Banno banno = bannos.get(tiposBannos.get(eleccionPos));
        //revisar que el tipo de monstruo del bano nos incluya
        if (!banno.getTipoMonstruo().equals("NA") && !banno.getTipoMonstruo().equals(tipo)){
            tiposBannos.remove(eleccionPos);
            return seleccionaBanno(bannos);
        }
        return banno;
    }
}
