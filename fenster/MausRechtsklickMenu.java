/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fenster;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;

/**
 *
 * @author Fiedler
 */
public class MausRechtsklickMenu extends JPopupMenu implements Aktionsfaehig {
    private ActionListener al;
    private ArrayList<Menupunkt> mliste = new ArrayList<Menupunkt>();
    public MausRechtsklickMenu() {

    }

    public MausRechtsklickMenu(String[] menupunkte) {
        menupunkteHinzufuegen(menupunkte);
    }
    public void invokerSetzen(JComponent komp){
        setInvoker(komp);
        mouseListenerStarten();
    }
    public JComponent invokerGeben(){
        return (JComponent) getInvoker();
    }
    private void mouseListenerStarten(){
        getInvoker().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                if (me.isPopupTrigger()) {
                    show(me.getComponent(), me.getX(), me.getY());
                }
            }
        });
    }
    public void menupunkteHinzufuegen(String[] menupunkte) {       
        for (String mp : menupunkte) {
            menupunktHinzufuegen(mp);
        }      
    }

    public void menupunktHinzufuegen(String menupunkt) {
        Menupunkt jmi = new Menupunkt(menupunkt);
        jmi.menuSetzen(this);
        if(al!=null)jmi.addActionListener(al);
        if(getInvoker()!= null)jmi.menuSetzen(invokerGeben());
        mliste.add(jmi);
        this.add(jmi);       
    }

    public void aktivieren() {
        this.setEnabled(true);
    }

    public void deaktivieren() {
        this.setEnabled(false);
    }


    public void actionListenerSetzen(ActionListener a) {
        al =a;
        for(Menupunkt m: mliste){
            m.addActionListener(a);
        }
    }
}
