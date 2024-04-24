/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;

/**
 *
 * @author Fiedler
 */
public class Auswahl extends JComboBox implements Aktionsfaehig{
    
    public Auswahl(){}
    public Auswahl(String liste, int laenge, int breite){
        super(liste.split(","));
        groesseSetzen(laenge, breite);
        listenerSetzen();
    }
    public Auswahl(String[] liste, int laenge, int breite){
        super(liste);
        groesseSetzen(laenge, breite);
        listenerSetzen();
    }
    public Auswahl(String liste){
        super(liste.split(","));
        listenerSetzen();
    }
    public Auswahl(String[] liste){
        super(liste);
        listenerSetzen();
    }
    public void setzeEingabefaehig(boolean eingabefaehig){
        setEditable(eingabefaehig);
    }
    public boolean startauswahlSetzen(int index){
        if(this.getItemCount() > index){
            this.setSelectedIndex(index);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean startauswahlSetzen(String s){
        for(int i = 0; i < this.getItemCount(); i++){
            if(((String)(getItemAt(i))).equals(s)){
                this.setSelectedIndex(i);
                return true;
            }
        }
        return false;
    }
    public void groesseSetzen(int l, int b){
        setSize(l,b);
    }
    public void actionListenerSetzen(ActionListener a){
        addActionListener(a);
    }
    private void listenerSetzen(){
        setKeySelectionManager( new JComboBox.KeySelectionManager(){
              public int selectionForKey( char aKey, ComboBoxModel aModel ){
                    int pos = Math.abs( aKey - 1 - '0' );
                    return pos >= aModel.getSize() ? aModel.getSize() - 1 : pos;
              }
        } );
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
    public void schriftgroesseSetzen(int schriftgroesse){
        Font f = getFont();
        int style = f.getStyle();
        String schriftart =f.getFamily();
        setFont(new Font(schriftart, style, schriftgroesse));
    }
    public void schriftartSetzen(String schriftart){
        Font f = getFont();
        int style = f.getStyle();
        int schriftgroesse =f.getSize();
        setFont(new Font(schriftart, style, schriftgroesse));
    }
    public String auswahlGeben(){
        return (String)(getSelectedItem());
    }
    public int auswahlAlsZahlGeben(){
        return Integer.parseInt((String)(getSelectedItem()));
    }
    public void auswahlSetzen(String text){
        this.setSelectedItem(text);
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
    public void kommentarHinzufuegen(String htmltext){
        this.setToolTipText(htmltext);
    }
}
