/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fenster;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JColorChooser;
import javax.swing.JLabel;

/**
 *
 * @author Fiedler
 */
public class Schild extends JLabel {

    public Schild() {
        groesseSetzen(120, 30);
        durchsichtigSetzen(false);
    }

    /**
     * Übergabe der Beschriftung des Knopfes
     */
    public Schild(String text) {
        setText(text);
        groesseSetzen(120, 30);
        durchsichtigSetzen(false);
    }

    public Schild(String text, int l, int b) {
        groesseSetzen(l, b);
        setText(text);
        durchsichtigSetzen(false);
    }

    public void groesseSetzen(int laenge, int breite) {
        this.setSize(laenge, breite);
    }

    /**
     * Die Hintergrundfarbe wird als Zeichenkette übergeben,
     * zum Beispiel "weiss"
     */
    public void farbeSetzen(String s) {
        this.setBackground(new Farbwandler().stringToColor(s));
    }

    /**
     * Hintergrundfarbe
     * r, g und b sind die rot-, grün- und blau-Anteile mit
     * Werten zwischen 0 und 255
     */
    public void farbeSetzen(int r, int g, int b) {
        this.setBackground(new Color(r, g, b));
    }

    public void farbeSetzen(Color c) {
        this.setBackground(c);
        repaint();
    }

    public void farbeSetzen() {
        setBackground(JColorChooser.showDialog(null, "Wähle neue Farbe", getBackground()));
    }

    /**
     * Die Schriftfarbe wird als Zeichenkette übergeben,
     * zum Beispiel "weiss"
     */
    public void schriftfarbeSetzen(String s) {
        this.setForeground(new Farbwandler().stringToColor(s));
    }

    public void schriftfarbeSetzen(Color c) {
        this.setForeground(c);
        repaint();
    }

    public void schriftfarbeSetzen() {
        setForeground(JColorChooser.showDialog(null, "Wähle neue Farbe", getForeground()));
    }

    /**
     * r, g und b sind die rot-, grün- und blau-Anteile mit
     * Werten zwischen 0 und 255
     */
    public void schriftfarbeSetzen(int r, int g, int b) {
        this.setForeground(new Color(r, g, b));
    }

    public void textSetzen(String text) {
        setText(text);
    }

    public void schriftgroesseSetzen(int schriftgroesse) {
        Font f = getFont();
        int style = f.getStyle();
        String schriftart = f.getFamily();
        setFont(new Font(schriftart, style, schriftgroesse));
    }

    public void schriftartSetzen(String schriftart) {
        Font f = getFont();
        int style = f.getStyle();
        int schriftgroesse = f.getSize();
        setFont(new Font(schriftart, style, schriftgroesse));
    }

    public void durchsichtigSetzen(boolean durchsichtig) {
        setOpaque(!durchsichtig);
        repaint();
    }

    public void kommentarHinzufuegen(String htmltext) {
        this.setToolTipText(htmltext);
    }
}
