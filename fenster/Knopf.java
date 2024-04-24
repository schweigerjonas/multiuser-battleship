/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;

/**
 *
 * @author Fiedler
 */
public class Knopf extends JButton implements Aktionsfaehig{

    public Knopf(){
        groesseSetzen(120,30);
    }
    /**
     * Übergabe der Beschriftung des Knopfes
     */
    public Knopf(String text){
        setText(text);
        groesseSetzen(120,30);
    }
    public void groesseSetzen(int laenge, int breite){
        this.setSize(laenge, breite);
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
    /**
     * Die Schriftfarbe wird als Zeichenkette übergeben,
     * zum Beispiel "weiss"
     */
    public void schriftfarbeSetzen(String s){
        this.setForeground(new Farbwandler().stringToColor(s));
    }
    public void schriftfarbeSetzen(){
        setForeground(JColorChooser.showDialog(null, "Wähle neue Farbe", getForeground()));
    }
     /**
     * r, g und b sind die rot-, grün- und blau-Anteile mit
     * Werten zwischen 0 und 255
     */
    public void schriftfarbeSetzen(int r, int g, int b){
        this.setForeground(new Color(r,g,b));
    }
    public void textSetzen(String text){
        setText(text);
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

    public void actionListenerSetzen(ActionListener a) {
        this.addActionListener(a);
    }
    public void kommentarHinzufuegen(String htmltext){
        this.setToolTipText(htmltext);
    }
}
