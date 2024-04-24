/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Administrator
 */
public class Text extends Figur{
    protected int schriftgroesse = 12;
    private String inhalt;
    private String schriftart = "TimesRoman";
    private int schriftstil = Font.PLAIN;

    public Text(){}
    /**
     * Die Länge und die Breite in Pixeln müssen übergeben werden.
     */
    public Text(String text, int schriftgroesse){
       inhalt = text;
       this.schriftgroesse = schriftgroesse;
    }
    public Text(String text){
       inhalt = text;
    }
    public Text(String text, int schriftgroesse, String farbe){
       inhalt = text;
       this.schriftgroesse = schriftgroesse;
       farbeSetzen(farbe);
    }
    public void schriftgroesseSetzen(int groesse){
        schriftgroesse = groesse;
        if (leinwand != null)leinwand.repaint();
    }
    public void schriftartSetzen(String art){
        schriftart = art;
        if (leinwand != null)leinwand.repaint();
    }
    public void fettSetzen(){
        schriftstil = Font.BOLD;
        if (leinwand != null)leinwand.repaint();
    }
    public void normalSetzen(){
        schriftstil = Font.PLAIN;
        if (leinwand != null)leinwand.repaint();
    }
    public void kursivSetzen(){
        schriftstil = Font.ITALIC;
        if (leinwand != null)leinwand.repaint();
    }
    public void textSetzen(String text){
        inhalt = text;
        if (leinwand != null)leinwand.repaint();

    }
    public void textSetzen(int zahl){
        inhalt =String.valueOf(zahl);
        if (leinwand != null)leinwand.repaint();
    }
    public void textSetzen(double zahl){
        inhalt =String.valueOf(zahl);
        if (leinwand != null)leinwand.repaint();
    }
    public void textHinzufuegen(String text){
        inhalt =inhalt+text;
        if (leinwand != null)leinwand.repaint();
    }
    public void textHinzufuegen(int zahl){
        inhalt = inhalt+String.valueOf(zahl);
        if (leinwand != null)leinwand.repaint();
    }
    public void textHinzufuegen(double zahl){
        inhalt = inhalt+String.valueOf(zahl);
        if (leinwand != null)leinwand.repaint();
    }
    @Override
    public void zeichnen(Graphics g){
        super.zeichnen(g);
        Font f = new Font(schriftart, schriftstil, schriftgroesse);
        g.setFont(f);
        g.drawString(inhalt, x, y);
    }
}
