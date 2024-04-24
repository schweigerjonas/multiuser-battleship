/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

/**
 *
 * @author Fiedler
 */
class Kreisbogen extends Bogen{
    /**
     * Der Radius in Pixeln muss übergeben werden.
     */
    public Kreisbogen(int r, int startwinkel, int winkel, String farbe, boolean fuellung){
        super(r,r, startwinkel, winkel, farbe, fuellung);
    }
    public Kreisbogen(int r, int startwinkel, int winkel){
        super(r,r,startwinkel, winkel);
    }
}
