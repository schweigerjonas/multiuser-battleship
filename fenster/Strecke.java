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
public class Strecke extends Figur {
    protected int dx, dy;
    private boolean gerade=false;

    /**
     * Die Länge und die Richtung der Strecke können durch
     * einen Vektor beschrieben werden,dessen Koordinaten
     * dx und dy vom Nutzer übergeben werden müssen.
     */
    public Strecke(int dx, int dy, String farbe){
        super(farbe, false);
        this.dx = dx;
        this.dy = dy;
    }
    public Strecke(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }
    public Strecke(int dx, int dy, boolean gerade){
        this.dx = dx;
        this.dy = dy;
        this.gerade = gerade;
    }
    @Override
    public void zeichnen(Graphics g){
        super.zeichnen(g);
        if(gerade){
            g.drawLine(x-5000*dx, y-5000*dy, x+10000*dx, y+10000*dy);
        }else{
            g.drawLine(x,y,x+dx,y+dy);
        }
    }
}
