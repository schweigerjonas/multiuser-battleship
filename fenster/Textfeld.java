/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JTextField;

/**
 *
 * @author Fiedler
 */
public class Textfeld extends JTextField implements Aktionsfaehig{
    public Textfeld(){
        groesseSetzen(200,30);
    }
    public Textfeld(String text){
        setText(text);
        groesseSetzen(200,30);
    }
    public void groesseSetzen(int laenge, int breite){
        this.setSize(laenge, breite);
    }
    public String textGeben(){
        return getText();
    }
    public void farbeSetzen(String s){
        this.setBackground(new Farbwandler().stringToColor(s));
    }
    public void farbeSetzen(int r, int g, int b){
        this.setBackground(new Color(r,g,b));
    }
    public void farbeSetzen(){
        setBackground(JColorChooser.showDialog(null, "Wähle neue Farbe", getBackground()));
    }
    public void schriftfarbeSetzen(){
        setForeground(JColorChooser.showDialog(null, "Wähle neue Farbe", getForeground()));
    }
    public void schriftfarbeSetzen(String s){
        this.setForeground(new Farbwandler().stringToColor(s));
    }
    public void schriftfarbeSetzen(int r, int g, int b){
        this.setForeground(new Color(r,g,b));
    }
    public void textSetzen(String text){
        setText(text);
    }
    public void textSetzen(int zahl){
        setText(String.valueOf(zahl));
    }
    public void textSetzen(double zahl){
        setText(String.valueOf(zahl));
    }
    public void textHinzufuegen(String text){
        setText(getText()+text);
    }
    public void textHinzufuegen(int zahl){
        setText(getText()+String.valueOf(zahl));
    }
    public void textHinzufuegen(double zahl){
        setText(getText()+String.valueOf(zahl));
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
    public void schriftstilSetzen(String stil){
        Font f = getFont();
        int schriftgroesse = f.getSize();
        String schriftart =f.getFamily();
        int style = Font.PLAIN;
        if(stil.equals("kursiv")){
            style = Font.ITALIC;
        }
        if(stil.equals("fett")){
            style = Font.BOLD;
        }
        setFont(new Font(schriftart, style, schriftgroesse));
    }
    public void eingabeMoeglichSetzen(boolean eingabeMoeglich){
        setEditable(eingabeMoeglich);
    }
    public void kommentarHinzufuegen(String htmltext){
        this.setToolTipText(htmltext);
    }

    public void actionListenerSetzen(ActionListener a) {
        this.addActionListener(a);
    }
}
