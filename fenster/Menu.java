/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fenster;

import java.awt.event.ActionListener;
import javax.swing.JMenu;

/**
 *
 * @author Fiedler
 */
public class Menu extends JMenu implements Aktionsfaehig{
    private ActionListener al;
    public Menu(String ueberschrift) {
        this.setText(ueberschrift);
    }

    public Menu(String ueberschrift, String[] menupunkte) {
        this.setText(ueberschrift);
        menupunkteHinzufuegen(menupunkte);
    }

    public void menupunkteHinzufuegen(String[] menupunkte) {
        for (String mp : menupunkte) {
            Menupunkt jmi = new Menupunkt(mp);
            jmi.menuSetzen(this);
            jmi.actionListenerSetzen(al);
            this.add(jmi);
        }
    }

    public void menupunktHinzufuegen(String menupunkt) {
        Menupunkt jmi = new Menupunkt(menupunkt);
        jmi.menuSetzen(this);
        jmi.actionListenerSetzen(al);
        this.add(jmi);
    }
    public void menupunktEntfernen(String menupunkt) {
        for(int i = 0; i < this.getItemCount(); i++){
            if(getItem(i).getText().equals(menupunkt)){
                remove(getItem(i));
                return;
            }
        }
    }
    public void aktivieren(){
        this.setEnabled(true);
    }
    public void deaktivieren(){
        this.setEnabled(false);
    }
    public boolean menupunktDeaktivieren(String menupunkt){
        for(int i = 0; i < this.getItemCount(); i++){
            if(getItem(i).getText().equals(menupunkt)){
                getItem(i).setEnabled(false);
                return true;
            }
        }
        return false;
    }
    public boolean menupunktAktivieren(String menupunkt){
        for(int i = 0; i < this.getItemCount(); i++){
            if(getItem(i).getText().equals(menupunkt)){
                getItem(i).setEnabled(true);
                return true;
            }
        }
        return false;
    }
    public boolean menupunktDeaktivieren(int j){
        if(j<getItemCount()){
            getItem(j).setEnabled(false);
            return true;
        }
        else{
            return false;
        }
    }
    public boolean menupunktAktivieren(int j){
        if(j<getItemCount()){
            getItem(j).setEnabled(true);
            return true;
        }
        else{
            return false;
        }
    }
    public Menupunkt menupunktGeben(String menupunkt){
        for (int i = 0; i < getItemCount(); i++) {
            if (menupunkt.equals(this.getItem(i).getText())) {
                return (Menupunkt)this.getItem(i);
            }
        }
        return null;
    }
    public Menupunkt menupunktGeben(int i){
         if(i<getItemCount()){
            return (Menupunkt)this.getItem(i);
        }
        else{
            return null;
        }
    }
    public void kommentarHinzufuegen(String htmltext){
        this.setToolTipText(htmltext);
    }

    public void alleEntfernen() {
        while(getItemCount()>0){
            this.remove(0);
        }
    }

    public void actionListenerSetzen(ActionListener a) {
        this.addActionListener(a);
        al = a;
        for (int i = 0; i < getItemCount(); i++){
            ((Menupunkt)getItem(i)).actionListenerSetzen(a);
        }
    }
}
