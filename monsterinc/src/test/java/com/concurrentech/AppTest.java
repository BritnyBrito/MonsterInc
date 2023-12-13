package com.concurrentech;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    FabricaPuertas fabricaPuertas;
    FabricaTanques fabricaTanques;
    Recolector recolector;
    Thread revisaRecolector;
    Timer timer;
    CentroReparacion centroReparacion;
    @Test
    public void shouldAnswerWithTrue()
    {   /* 
        // Fabrica de puertas
        fabricaPuertas = new FabricaPuertas();
        fabricaPuertas.inicia();
        // fabrica de tanques
        fabricaTanques = new FabricaTanques();
        fabricaTanques.inicia();
        // recolector
        timer = new Timer();
        recolector = new Recolector();
        revisaRecolector = new Thread(() -> alimentaCiudad());
        revisaRecolector.start();
        Tanque[] tanques = {
            new Tanque("a", 1),
            new Tanque("b", 2),
            new Tanque("a", 3),
            new Tanque("c", 1),
            new Tanque("a", 2),
            new Tanque("a", 3),
            new Tanque("b", 1),
            new Tanque("a", 1),
            new Tanque("a", 2),
            new Tanque("a", 3),
            new Tanque("b", 1),
        };
        for(Tanque t:  tanques){
            /*recolector.vaciaTanque(t);
            try{
                Thread.sleep(0, 5 * 1000);
            }catch(InterruptedException e){} */
            //centroReparacion.simultaRoto(t);
        /* }

        // centro reparacion
        centroReparacion = new CentroReparacion();
        Tanque[] tanques = {
            new Tanque("a", 1),
            new Tanque("b", 2),
            new Tanque("a", 3),
            new Tanque("c", 1),
            new Tanque("a", 2),
            new Tanque("a", 3),
            new Tanque("b", 1),
            new Tanque("a", 1),
            new Tanque("a", 2),
            new Tanque("a", 3),
            new Tanque("b", 1),
        };

        for(Tanque t:  tanques){xs
            centroReparacion.simultaRoto(t);   
        }*/
        Main2 m = new Main2();
        String[] args = {};
        try {
            m.main(args);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /*
    Metodo que se aseguro que se le brinde energía a la ciudad cada TIEMPO_CIUDAD
    */
    private void alimentaCiudad(){
       

        // Schedule the task to run every 30 seconds (30,000 milliseconds)
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                recolector.vaciaRecolector();
            }
        }, 0, 30 * 1000); // Initial delay is 0, and the period is 30 seconds
    }
}
