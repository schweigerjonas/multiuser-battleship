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
public class Rundeck extends Figur{
    protected int laenge, breite, bogenlaenge,bogenbreite;

    /**
     * Die Länge und die Breite in Pixeln müssen übergeben werden.
     */
    public Rundeck(int l, int b, int bl, int bb, String farbe, boolean fuellung){
        super(farbe, fuellung);
        this.laenge=l;
        this.breite=b;
        bogenlaenge = bl;
        bogenbreite = bb;
    }
    public Rundeck(int l, int b, int bl, int bb){
        this.laenge=l;
        this.breite=b;
        bogenlaenge = bl;
        bogenbreite = bb;
    }
    @Override
    public void zeichnen(Graphics g){
        super.zeichnen(g);
        if (fuellung){
            g.fillRoundRect(x,y,laenge,breite,bogenlaenge, bogenbreite);
        }
        else{
            g.drawRoundRect(x,y,laenge,breite,bogenlaenge, bogenbreite);
        }
    }
}
