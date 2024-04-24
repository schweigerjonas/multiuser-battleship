/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

/**
 *
 * @author Fiedler
 */
public class Kreis extends Ellipse{
    /**
     * Der Radius in Pixeln muss übergeben werden.
     */
    public Kreis(int r, String farbe, boolean fuellung){
        super(r,r, farbe, fuellung);
    }
    public Kreis(int r){
        super(r,r);
    }
}
