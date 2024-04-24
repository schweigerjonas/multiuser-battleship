/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fenster;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JEditorPane;

/**
 *
 * @author Fiedler
 */
public class Editor extends JEditorPane {

    /* public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setSize(400,400);
    f.setVisible(true);

    Editor e = new Editor("");
    e.setText(" ");
    e.setText("");
    e.setEditable(true);
    //f.add(e);

    JScrollPane jscp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jscp.setViewportView(e);
    jscp.setLocation(0, 0);
    jscp.setSize(500, 500);
    f.add(jscp);

    }
     */
    public Editor(){}
    public Editor(String url) {
        dateiLaden(url);
       
    }

    public Editor(int laenge, int breite) {
        setSize(laenge, breite);
    }

    public void groesseSetzen(int laenge, int breite) {
        this.setSize(laenge, breite);
    }

    public String textGeben() {
        return getText();
    }

    public void farbeSetzen(String s) {
        this.setBackground(new Farbwandler().stringToColor(s));
    }

    public void farbeSetzen(int r, int g, int b) {
        this.setBackground(new Color(r, g, b));
    }

    public void schriftfarbeSetzen(String s) {
        this.setForeground(new Farbwandler().stringToColor(s));
    }

    public void schriftfarbeSetzen(int r, int g, int b) {
        this.setForeground(new Color(r, g, b));
    }

    public void textSetzen(String text) {
        this.setText(text);
    }

    public void textSetzen(int zahl) {
        setText(String.valueOf(zahl));
    }

    public void textSetzen(double zahl) {
        setText(String.valueOf(zahl));
    }

    public void textHinzufuegen(String text) {
        setText(getText() + text);
    }

    public void textHinzufuegen(int zahl) {
        setText(getText() + String.valueOf(zahl));
    }

    public void textHinzufuegen(double zahl) {
        setText(getText() + String.valueOf(zahl));
    }

    public void aktivieren() {
        setEnabled(true);
    }
    public String[] schriftartenGeben(){
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }
    public void schriftartWaehlen(){
        schriftartSetzen(new Dialogmanager().auswaehlen("", "Schriftarten",schriftartenGeben()));
    }
    public void deaktivieren() {
        setEnabled(false);
    }

    public void fokusSetzen() {
        requestFocus();
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
    
    public void eingabeMoeglichSetzen(boolean eingabeMoeglich) {
        setEditable(eingabeMoeglich);
    }

    public void farbeSetzen() {
        setBackground(JColorChooser.showDialog(null, "Wähle neue Farbe", getBackground()));
    }

    public void schriftfarbeSetzen() {
        setForeground(JColorChooser.showDialog(null, "Wähle neue Farbe", getForeground()));
    }

    public void dateiLaden(String pfad) {
        try {
            URL u = new URL("file", "localhost", pfad);
            setPage(u);
        } catch (IOException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dateiLaden() {
        File f = new Dialogmanager().dateiAuswahl("Öffnen");
        if(f!=null)dateiLaden(f.getAbsolutePath());
    }
    public void speichern(){
        speichern(new Dialogmanager().dateiAuswahl("Speichern").getAbsolutePath());
    }
    public void speichern(String pfad){
        try {
            FileOutputStream fos = new FileOutputStream(pfad);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.getText());
            oos.close();
        } catch (IOException e) // Schreib- Leseprobleme
        {
            System.err.println("Schreibprobleme!");
        }
    }
    public void webseiteLaden(String webadresse) {
        try {
            setPage(new URL("http://"+webadresse));
        } catch (IOException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void textformatSetzen(String format){
        setContentType("text/"+format);
    }
    public void kommentarHinzufuegen(String htmltext){
        this.setToolTipText(htmltext);
    }
}
