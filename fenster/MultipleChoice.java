/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;

/**
 *
 * @author Fiedler
 */
public class MultipleChoice extends Leinwand{
    private ActionListener al;
    private ArrayList<Radioknopf> bliste = new ArrayList<Radioknopf>();
    private ButtonGroup bg = new ButtonGroup();

    public MultipleChoice() {
        setSize(120,100);
    }

    public MultipleChoice(String[] menupunkte) {
        menupunkteHinzufuegen(menupunkte);
        setSize(120,20*menupunkte.length);
    }

    public void menupunkteHinzufuegen(String[] menupunkte) {
        for (String mp : menupunkte) {
            menupunktHinzufuegen(mp);
        }
    }

    public void menupunktHinzufuegen(String menupunkt) {
        Radioknopf rk = new Radioknopf(menupunkt);
        if(al!=null)rk.addActionListener(al);
        bliste.add(rk);
        rk.setBackground(getBackground());
        rk.setForeground(getForeground());
        bg.add(rk);
        rk.gruppeSetzen(this);
        hinzufuegen( rk, 0, -20 + 20*bliste.size());
    }

    public String auswahlGeben(){
        for(Radioknopf rk: bliste){
            if(rk.isSelected()){
                return rk.getText();
            }
        }
        return null;
    }
    public int auswahlIndex(){
        for(int i=0; i< bliste.size();i++){
            if(bliste.get(i).isSelected()){
                return i;
            }
        }
        return -1;
    }
    @Override
    public void farbeSetzen(String s){
        super.farbeSetzen(s);
        setBackground(new Farbwandler().stringToColor(s));
        farbeAngleichen();
    }
    private void farbeAngleichen(){
        for(Radioknopf rk: bliste){
            rk.setBackground(getBackground());
            rk.setForeground(getForeground());
        }
    }
    /**
     * Hintergrundfarbe
     * r, g und b sind die rot-, grün- und blau-Anteile mit
     * Werten zwischen 0 und 255
     */
    @Override
    public void farbeSetzen(int r, int g, int b){
        super.farbeSetzen(r,g,b);
        setBackground(new Color(r,g,b));
        farbeAngleichen();
    }
    @Override
    public void farbeSetzen(){
        Color c =JColorChooser.showDialog(null, "Wähle neue Farbe", getBackground());
        super.farbeSetzen(c);
        setBackground(c);
        farbeAngleichen();
    }
    /**
     * Die Schriftfarbe wird als Zeichenkette übergeben,
     * zum Beispiel "weiss"
     */
    public void schriftfarbeSetzen(String s){
        setForeground(new Farbwandler().stringToColor(s));
        farbeAngleichen();
    }
    public void schriftfarbeSetzen(){
        setForeground(JColorChooser.showDialog(null, "Wähle neue Farbe", getForeground()));
        farbeAngleichen();
    }
}
