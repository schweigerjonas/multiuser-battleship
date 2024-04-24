/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fenster;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 *
 * @author Fiedler
 */
public class Browser extends Leinwand implements HyperlinkListener, ActionListener {
    private Textfeld tf = new Textfeld();
    private Knopf k = new Knopf("Laden");
    private Editor edi = new Editor();
    private int tfhoehe = 40;
    public Browser(int laenge, int breite) {
        setSize(laenge, breite);
        init();
    }

    public Browser(int laenge, int breite, String startseite) {
        setSize(laenge, breite);
        edi.webseiteLaden(startseite);
        init();
    }
    private void init(){
        k.addActionListener(this);
        edi.addHyperlinkListener(this);
        edi.setEditable(false);
        this.hinzufuegenOhneAktionen(tf, 3, 3);
        this.hinzufuegenOhneAktionen(k, 200, 3);
        this.hinzufuegen(edi, 0, tfhoehe,getWidth(),getHeight()-tfhoehe);
    }
    public void webSeiteLaden(String webadresse){
        edi.webseiteLaden(webadresse);
    }
    public void hyperlinkUpdate(HyperlinkEvent event) {
        HyperlinkEvent.EventType typ = event.getEventType();

        if (typ == HyperlinkEvent.EventType.ACTIVATED) {
            try {
                edi.setPage(event.getURL());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Can't follow link to " + event.getURL().toExternalForm(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==k){
            edi.webseiteLaden(tf.getText());
        }
    }
}
