/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Fiedler
 */
public class Leinwand extends JPanel implements Aktionsfaehig, Aenderungsfaehig{
    private ArrayList<Figur> figurenliste = new ArrayList<Figur>();
    private ActionListener actionListener;
    private ChangeListener changeListener;
    private Color farbe;
    private ArrayList<Aktionsfaehig> aliste = new ArrayList<Aktionsfaehig>();
    private ArrayList<Aenderungsfaehig> cliste = new ArrayList<Aenderungsfaehig>();

    public Leinwand(){
        setOpaque(false);
        setLayout(null);
        setVisible(true);
    }
    public Leinwand(int laenge, int breite){
        setOpaque(false);
        setSize(laenge, breite);
        setLayout(null);
        setVisible(true);       
    }
    public void groesseSetzen(int l, int b){
        setSize(l,b);
    }
    public void actionListenerSetzen(ActionListener a){
        actionListener = a;
        for(Aktionsfaehig akt: aliste){
            akt.actionListenerSetzen(a);
        }
    }
    public void changeListenerSetzen(ChangeListener a){
        changeListener = a;
        for(Aenderungsfaehig akt: cliste){
            akt.changeListenerSetzen(a);
        }
    }
    public boolean entfernen(Figur f){
        return figurenliste.remove(f);
    }
     /**
     * Bei Ellipsen (bzw. Kreisen) werden die Koordinaten des Mittelpunkts
     * übergeben, bei anderen Figuren die Koordinaten der linken oberen Ecke
     */
    public void hinzufuegen(Figur f, int x, int y){
        f.koordinatenSetzen(x, y);
        figurenliste.add(f);
        repaint();
    }
     /** 
     * Bei Ellipsen (bzw. Kreisen) werden die Koordinaten des Mittelpunkts
     * übergeben, bei anderen Figuren die Koordinaten der linken oberen Ecke.
     * Die Koordinaten x und y sind Werte zwischen 0 und 1 und sind Anteile
     * der Leinwand-Maße.
     */
    public void hinzufuegen(Figur f, double x, double y){
        hinzufuegen(f, (int)(x*getWidth()), (int)(y*getHeight()));
    }

    public void update(){
        repaint();
    }
    @Override
    public void paint(Graphics g){
        g.setColor(farbe);
        g.fillRect(0,0, getWidth(),getHeight());
        for(int i = 0; i < figurenliste.size(); i++){
            Figur fig = figurenliste.get(i);
            fig.zeichnen(g);
        }
        super.paint(g);
    }
    /**
     * Die Hintergrundfarbe wird als Zeichenkette übergeben,
     * zum Beispiel "weiss"
     */
    public void farbeSetzen(String s){
        farbe = new Farbwandler().stringToColor(s);
        repaint();
    }
    /**
     * Hintergrundfarbe
     * r, g und b sind die rot-, grün- und blau-Anteile mit
     * Werten zwischen 0 und 255
     */
    public void farbeSetzen(int r, int g, int b){
        farbe = new Color(r,g,b);
        repaint();
    }
    public void farbeSetzen(){
        farbe = JColorChooser.showDialog(null, "Wähle neue Farbe", getBackground());
        repaint();
    }
    public void farbeSetzen(Color c){
        farbe = c;
        repaint();
    }
    public void hinzufuegenOhneAktionen(JComponent c, int x, int y){
        c.setLocation(x,y);
        add(c);
    }
    public void hinzufuegen(Aktionsfaehig akt, int x, int y){
        JComponent b = (JComponent)akt;
        b.setLocation(x,y);
        add(b);
        aliste.add(akt);
        akt.actionListenerSetzen(actionListener);
    }
    public void hinzufuegenOhneAktionen(JComponent c, double x, double y){
        c.setLocation((int)(x*getWidth()),(int)(y*getHeight()));
        add(c);
    }
    public void hinzufuegen(Schild s, int x, int y){
        s.setLocation(x,y);
        add(s);
    }
    public void hinzufuegen(Schild s, double x, double y){
        s.setLocation((int)(x*getWidth()),(int)(y*getHeight()));
        add(s);
    }
    public void hinzufuegen(Aktionsfaehig akt, double x, double y){
        hinzufuegen(akt,(int)(getWidth()*x),(int)(getHeight()*y));
    }
 
    public void hinzufuegen(Leinwand l, double x, double y){
        l.setLocation((int)(x*getWidth()),(int)(y*getHeight()));
    }
    public void hinzufuegen(Leinwand l, int x, int y){
        l.setLocation(x,y);
        add(l);
        aliste.add(l);
        cliste.add(l);
        l.actionListenerSetzen(actionListener);
        l.changeListenerSetzen(changeListener);
    }
    public void hinzufuegen(Karteikasten k, int x, int y){
        k.setLocation(x,y);
        add(k);
        aliste.add(k);
        cliste.add(k);
        k.actionListenerSetzen(actionListener);
        k.changeListenerSetzen(changeListener);
    }
    public void hinzufuegen(Bild b, int x, int y, int laenge, int breite){
        double v = (double)laenge/(double)breite;
        double vb = b.verhaeltnisLaengeBreiteOriginalBild();
        int lb = b.laengeGeben();
        int bb = b.breiteGeben();
        int xx = 0, yy = 0;
        if(v > vb){
            if(bb > breite){
                bb = breite;
                lb = (int)((double)bb*vb);
            }
        }
        else{
            if(lb > laenge){
                lb = laenge;
                bb = (int)((double)lb/vb);
            }
        }
        b.groesseAendern(lb, bb);
        xx = (laenge-lb)/2;
        yy = (breite-bb)/2;
        hinzufuegen(b,x+xx,y+yy);
    }
    public void hinzufuegen(Bild b, int x, int y){
        b.koordinatenSetzen(x, y);
        figurenliste.add(b);
        b.imageObserverSetzen(this);
        repaint();
    }
    public void hinzufuegen(Bild b){
        hinzufuegen(b,0,0, getWidth(), getHeight());
    }
    public void hinzufuegen(Schieberegler s, int x, int y){
        s.setLocation(x, y);
        add(s);
        cliste.add(s);
        s.addChangeListener(changeListener);
    }
    public void hinzufuegen(Editor e, int x, int y, int l, int b){
        JScrollPane jscp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                       JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jscp.setViewportView(e);
        jscp.setLocation(x, y);
        jscp.setSize(l, b);
        add(jscp);
        if(e.getText().equals("")){
            e.setText(" ");
            e.setText("");
        }
    }
    public void hinzufuegen(Tabelle t, int x, int y, int l, int b){
        JScrollPane jscp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                       JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jscp.setViewportView(t);
        jscp.setLocation(x, y);
        jscp.setSize(l, b);
        add(jscp);
    }
    public void hinzufuegen(Symbolleiste s){
        this.add(s);
        aliste.add(s);
        s.actionListenerSetzen(actionListener);
    }
    public void hinzufuegen(Symbolleiste s, int hoehe){
        s.groesseSetzen(this, hoehe);
        hinzufuegen(s);
    }
    public void hinzufuegen(MausRechtsklickMenu mrklm, JComponent komp){
        aliste.add(mrklm);
        mrklm.actionListenerSetzen(actionListener);
        mrklm.invokerSetzen(komp);
    }

    public void bildLaden(){
       File f = new Dialogmanager().dateiAuswahl("Öffnen");
       Bild b = new Bild(f.getAbsolutePath());
       hinzufuegen(b,0,0,getWidth(),getHeight());
    }
    public void kommentarHinzufuegen(String htmltext){
        this.setToolTipText(htmltext);
    }
}
