/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JMenuItem;

/**
 *
 * @author Fiedler
 */
public class Menupunkt extends JMenuItem implements Aktionsfaehig{
    private JComponent menu;
    public Menupunkt(){}
    public Menupunkt(String text){
        setText(text);
    }
    public void menuSetzen(JComponent menu){
        this.menu = menu;
    }
    public JComponent menuGeben(){
        return menu;
    }
    public void kommentarHinzufuegen(String htmltext){
        this.setToolTipText(htmltext);
    }

    public void actionListenerSetzen(ActionListener a) {
        this.addActionListener(a);
    }
}
