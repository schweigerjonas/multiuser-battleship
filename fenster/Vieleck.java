/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Fiedler
 */
public class Vieleck extends Figur{
    private ArrayList<Punkt> punkte = new ArrayList<Punkt>();

    /**
     * Hinzufuegen eines weiteren Punktes zum Vieleck
     * Die Koordinaten werden relativ zum ersten Punkt angegeben.
     */
    public void punktHinzufuegen(int x, int y){
        punkte.add(new Punkt(x,y));
    }
    
    @Override
    public void zeichnen(Graphics g){
        super.zeichnen(g);
        int eckenzahl = punkte.size()+1;
        int[] ex = new int[eckenzahl];
        int[] ey = new int[eckenzahl];
        ex[0] = x;
        ey[0] = y;
        int zaehler = 1;
        for(Punkt p: punkte){
            ex[zaehler] = p.getX()+x;
            ey[zaehler] = p.getY()+y;
            zaehler++;
        }
        if (fuellung){
            g.fillPolygon(ex, ey, eckenzahl);
        }
        else{
            g.drawPolygon(ex, ey, eckenzahl);
        }
    }
}
