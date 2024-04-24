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
public class Bogen extends Figur{
    private int mx, my, rx, ry, startwinkel,winkel;

    public Bogen(int rx, int ry, int startwinkel, int winkel){
        this.rx=rx;
        this.ry=ry;
        this.startwinkel = startwinkel;
        this.winkel = winkel;
    }
    public Bogen(int rx, int ry, int startwinkel, int winkel, String farbe, boolean fuellung){
        super(farbe, fuellung);
        this.rx=rx;
        this.ry=ry;
        this.startwinkel = startwinkel;
        this.winkel = winkel;
    }
    /**
     *Übergabe der Koordinaten des Mittelpunkts mx und my
     */
    @Override
    public void koordinatenSetzen(int mx, int my){
        this.mx = mx;
        this.my = my;
        x = mx - rx;
        y = my - ry;
    }
    @Override
    public void zeichnen(Graphics g){
        super.zeichnen(g);
        int dx = 2 * rx;
        int dy = 2 * ry;
        if (fuellung){
            g.fillArc(x,y,dx,dy,startwinkel,winkel);
        }
        else{
            g.drawArc(x, y, dx, dy,startwinkel,winkel);
        }
    }
}
