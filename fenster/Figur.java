/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JColorChooser;

/**
 *
 * @author Fiedler
 */
public abstract class Figur {
    protected Color farbe = Color.black;
    protected boolean fuellung = false;
    protected int x, y;
    protected Leinwand leinwand;
    public Figur(){}
    public Figur(String farbe, boolean fuellung){
        this.farbe = new Farbwandler().stringToColor(farbe);
        this.fuellung = fuellung;
    }
    public void zeichnen(Graphics g){
        g.setColor(farbe);
    }
    /**
     * Wird true übergeben, wird die Figur ausgefüllt,
     * andernfalls wird nur ihr Rand gezeichnet.
     */
    public void fuellungSetzen(boolean fuellung){
        this.fuellung = fuellung;
        if (leinwand != null)leinwand.repaint();
    }
    /**
     * Die Farbe wird als Zeichenkette übergeben,
     * zum Beispiel "weiss".
     */
    public void farbeSetzen(String s){
        farbe = new Farbwandler().stringToColor(s);
        if (leinwand != null)leinwand.repaint();
    }
    /**
     * r, g und b sind die rot-, grün- und blau-Anteile mit
     * Werten zwischen 0 und 255
     */
    public void farbeSetzen(int r, int g, int b){
        farbe = new Color(r,g,b);
        if (leinwand != null)leinwand.repaint();
    }
    public void farbeSetzen(){
        farbe = JColorChooser.showDialog(null, "Waehle neue Farbe", farbe );
        if (leinwand != null)leinwand.repaint();
    }
    /**
     *Koordinaten der linken oberen Ecke
     */
    public void koordinatenSetzen(int x, int y){
        this.x = x;
        this.y = y;
        if (leinwand != null)leinwand.repaint();
    }
    /** 
     * Die Methode gibt die x-Koordinate der linken oberen Ecke zurück.
     */
    public int xGeben(){
        return x;
    }
     /**
     * Die Methode gibt die y-Koordinate der linken oberen Ecke zurück.
     */
    public int yGeben(){
        return y;
    }
    public Leinwand leinwandGeben(){
        return leinwand;
    }
    public void leinwandSetzen(Leinwand l){
        leinwand = l;
    }
}
