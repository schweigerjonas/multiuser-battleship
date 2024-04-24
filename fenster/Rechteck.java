/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Graphics;

/**
 *
 * @author Fiedler
 */
public class Rechteck extends Figur{
    protected int laenge, breite;

    /**
     * Die Länge und die Breite in Pixeln müssen übergeben werden.
     */
    public Rechteck(int l, int b, String farbe, boolean fuellung){
        super(farbe, fuellung);
        this.laenge=l;
        this.breite=b;
    }
    public Rechteck(int l, int b){
        this.laenge=l;
        this.breite=b;
    }
    @Override
    public void zeichnen(Graphics g){
        super.zeichnen(g);
        if (fuellung){
            g.fillRect(x,y,laenge,breite);
        }
        else{
            g.drawRect(x,y,laenge,breite);
        }
    }
}
