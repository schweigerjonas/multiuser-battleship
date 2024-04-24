/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JToolBar;

/**
 *
 * @author Fiedler
 */
public class Symbolleiste extends JToolBar implements Aktionsfaehig{
    private ActionListener al;
    private ArrayList<Aktionsfaehig> aliste = new ArrayList<Aktionsfaehig>();
    private int hoehe;

    public Symbolleiste(){
        init();
    }
     public Symbolleiste(int laenge,int hoehe){
        groesseSetzen(laenge,hoehe);
        init();
    }
    /**
     * Übergabe der Beschriftung des Knopfes
     */
    public Symbolleiste(int hoehe){
        hoeheSetzen(hoehe);
        init();
    }
    public void init(){
        groesseSetzen(1024,24);
    }
    public void titelSetzen(String text){
        this.setName(text);
    }
    public void hoeheSetzen(int hoehe){
          this.hoehe =hoehe;
          this.setSize(getWidth(),hoehe);
    }
    public void groesseSetzen(int laenge, int hoehe){
        this.hoehe = hoehe;
        this.setSize(laenge, hoehe);
    }
    public void groesseSetzen(Fenster f, int hoehe){
        groesseSetzen(f.laengeGeben()-10,hoehe);
    }
    public void groesseSetzen(Leinwand l, int hoehe){
        groesseSetzen(l.getWidth()-10,hoehe);
    }
    public void beweglichSetzen(boolean beweglich){
        setFloatable(beweglich);
    }
    /**
     * Die Hintergrundfarbe wird als Zeichenkette übergeben,
     * zum Beispiel "weiss"
     */
    public void farbeSetzen(String s){
        this.setBackground(new Farbwandler().stringToColor(s));
    }
    /**
     * Hintergrundfarbe
     * r, g und b sind die rot-, grün- und blau-Anteile mit
     * Werten zwischen 0 und 255
     */
    public void farbeSetzen(int r, int g, int b){
        this.setBackground(new Color(r,g,b));
    }
    public void farbeSetzen(){
        setBackground(JColorChooser.showDialog(null, "Wähle neue Farbe", getBackground()));
    }

    public void aktivieren(){
        setEnabled(true);
    }
    public void deaktivieren(){
        setEnabled(false);
    }
    public void fokusSetzen(){
        requestFocus();
    }
    public void hinzufuegen(Aktionsfaehig komp){
        if(al!=null)komp.actionListenerSetzen(al);
        aliste.add(komp);
        JComponent jcp = (JComponent)komp;
        int w = jcp.getWidth();
        this.add(jcp);
    }

    public void actionListenerSetzen(ActionListener a) {
        al = a;
        for(Aktionsfaehig af: aliste){
            af.actionListenerSetzen(a);
            JComponent jcp = (JComponent)af;
            jcp.setSize(jcp.getWidth(),hoehe);
        }
    }
 
}
