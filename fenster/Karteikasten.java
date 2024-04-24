/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Fiedler
 */
public class Karteikasten extends JTabbedPane implements Aktionsfaehig, Aenderungsfaehig{
    private ActionListener al;
    private ChangeListener cl;
    private int hoehenkorrektur=28;
    private ArrayList<Leinwand> llist = new ArrayList<Leinwand>();

    public Karteikasten(){
        super();
    }
    public Karteikasten(int laenge, int breite){
        super();
        setSize(laenge, breite);
    }
    public void actionListenerSetzen(ActionListener a){
        al = a;
        for (Leinwand l: llist ){
            l.actionListenerSetzen(a);
        }
    }
    public void changeListenerSetzen(ChangeListener c){
        cl = c;
        for (Leinwand l: llist ){
            l.changeListenerSetzen(c);
        }
    }
    public void hinzufuegen(String titel, Leinwand l){
        l.actionListenerSetzen(al);
        l.changeListenerSetzen(cl);
        addTab(titel, l);
        llist.add(l);
    }
    public void hinzufuegen(String titel, Bild b){
        int w = getWidth(); int h = getHeight()-hoehenkorrektur;
        Leinwand l = new Leinwand(w,h);
        l.farbeSetzen("schwarz");
        l.hinzufuegen(b);
        hinzufuegen(titel, l);
    }
    public void hinzufuegen(String titel, Editor e){
        int w = getWidth(); int h = getHeight()-hoehenkorrektur;
        Leinwand l = new Leinwand(w,h);
        l.farbeSetzen("schwarz");
        l.hinzufuegen(e, 0, 0,w,h);
        hinzufuegen(titel, l);
    }
   public void hinzufuegen(String titel, Browser b){
        int w = getWidth(); int h = getHeight()-hoehenkorrektur;
        Leinwand l = new Leinwand(w,h);
        l.farbeSetzen("schwarz");
        l.hinzufuegen(b, 0, 0);
        hinzufuegen(titel, l);
    }
}
