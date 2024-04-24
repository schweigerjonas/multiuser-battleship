/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Color;
import java.awt.Scrollbar;
import javax.swing.JColorChooser;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;


public class Schieberegler extends JSlider implements Aenderungsfaehig{
    private int min=0, max=25;

    public Schieberegler(int laenge, boolean vertikal){
        if(vertikal){
            this.setOrientation(Scrollbar.VERTICAL);
            groesseSetzen(16,laenge);
        }
        else{
            this.setOrientation(Scrollbar.HORIZONTAL);
            groesseSetzen(laenge, 16);
        }
        this.setMaximum(max);
        this.setMinimum(0);
        setPaintTicks(false);
    }
    public Schieberegler(int laenge, int dicke, boolean vertikal, int max, int start){
        if(max <= min){
            max = min +9;
        }
        if(start < min){
            start = min;
        }
        if(start > max){
            start = max;
        }
        this.max = max;
   
        if(vertikal){
            this.setOrientation(Scrollbar.VERTICAL);
            groesseSetzen(dicke,laenge);
        }
        else{
            this.setOrientation(Scrollbar.HORIZONTAL);
            groesseSetzen(laenge, dicke);
        }
        this.setMaximum(max);
        this.setValue(start);
        this.setMinimum(min);
        setPaintTicks(false);
    }

    public int positionGeben(){
        return this.getValue();
    }
    public void positionSetzen(int pos){
        if(pos < min)pos = min;
        if(pos > max)pos = max;
        this.setValue(pos);
    }
    public void groesseSetzen(int laenge, int breite){
        this.setSize(laenge, breite);
    }
    /**
     * Die Hintergrundfarbe wird als Zeichenkette übergeben,
     * zum Beispiel "weiß"
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
    public void kleineStricheSetzen(int abstand){
        setPaintTicks(true);
        setMinorTickSpacing(abstand);
  
    }
    public void grosseStricheSetzen(int abstand){
        setPaintTicks(true);
        setMajorTickSpacing(abstand);
    }
    public void zahlenSetzen(boolean zahlen){
        setPaintLabels(zahlen);
    }
    public void spurZeichnen(boolean spur){
        setPaintTrack(spur);
    }

    public void changeListenerSetzen(ChangeListener cl) {
        this.addChangeListener(cl);
    }
    public void kommentarHinzufuegen(String htmltext){
        this.setToolTipText(htmltext);
    }
}
