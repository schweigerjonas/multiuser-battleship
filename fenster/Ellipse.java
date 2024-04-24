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
public class Ellipse extends Figur{
    
    protected int mx,my, rx, ry;
    /**
     * rx: Radius in horizontaler Richtung
     * ry: Radius in vertikaler Richtung
     */
    public Ellipse(int rx, int ry){
        this.rx=rx;
        this.ry=ry;
    }
    public Ellipse(int rx, int ry, String farbe, boolean fuellung){
        super(farbe, fuellung);
        this.rx=rx;
        this.ry=ry;
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
            g.fillOval(x,y,dx,dy);
        }
        else{
            g.drawOval(x, y, dx, dy);
        }
    }
}
