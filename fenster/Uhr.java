/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

/**
 *
 * @author Administrator
 */
public class Uhr //implements Runnable{
{private long wartezeit;
    private long startzeit;

    public Uhr(){
    }

    public void warten(long ms){
        long t0 = System.currentTimeMillis();
        long t1 = t0 + ms;
        while(System.currentTimeMillis() < t1){
            // tue nichts
        }
    }
 /*   public void starten(long ms){
        this.wartezeit = ms;
        Thread t = new Thread(this);
        startzeit = System.currentTimeMillis();
        t.start();
    }
    public long abgelaufeneZeit(){
        return System.currentTimeMillis()-startzeit;
    }
    public long warteZeitGeben(){
        return wartezeit;
    }
    public double anteilAbgelaufenerZeit(){
        return (double)(wartezeit)/(double)(abgelaufeneZeit());
    }
    @Override
    public void run() {
        try {
            Thread.sleep(wartezeit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        abgelaufen();
        Thread.currentThread().interrupt();
    }
    public void abgelaufen(){}*/
}
