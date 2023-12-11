package com.concurrentech;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    Recolector recolector;
    Thread revisaRecolector;
    Timer timer;
    CentroReparacion centroReparacion = new CentroReparacion();
    @Test
    public void shouldAnswerWithTrue()
    {
        //assertTrue( true );
        //FabricaTanques fabricaTanques = new FabricaTanques();
        //fabricaTanques.inicia();
        
        /*timer = new Timer();
        recolector = new Recolector();
        revisaRecolector = new Thread(() -> alimentaCiudad());
        revisaRecolector.start();*/
        
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
        centroReparacion = new CentroReparacion();
        for(Tanque t:  tanques){
            /*recolector.vaciaTanque(t);
            try{
                Thread.sleep(0, 5 * 1000);
            }catch(InterruptedException e){} */
            centroReparacion.simultaRoto(t);
        }
        
    }
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
