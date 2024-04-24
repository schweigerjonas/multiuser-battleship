package fenster;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Fiedler
 */
public class Fenster extends JFrame implements ActionListener, ChangeListener {

    private Leinwand leinwand = new Leinwand();
    private Menuleiste menuleiste = new Menuleiste();
    private int laenge,  breite;

    public Fenster() {
        aufBildschirmgroesseSetzen();
        init();
    }

    public Fenster(int laenge, int breite) {
        init();
        this.laenge = laenge;
        this.breite = breite;
        setSize(laenge, breite);
    }

    private void init() {
        menuleiste.actionListenerSetzen(this);
        leinwand.setBounds(0, 0, getWidth(), getHeight());
        leinwand.actionListenerSetzen(this);
        leinwand.changeListenerSetzen(this);
        setContentPane(leinwand);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void aufBildschirmgroesseSetzen() {
        this.laenge = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.breite = Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(laenge, breite);
    }

    public void groesseSetzen(int laenge, int breite) {
        setSize(laenge, breite);
    }

    public void titelSetzen(String text) {
        setTitle(text);
    }

    public void hinzufuegenOhneAktionen(JComponent c, int x, int y) {
        leinwand.hinzufuegenOhneAktionen(c, x, y);
        repaint();
    }
    public void hinzufuegen(Schild s, int x, int y) {
        leinwand.hinzufuegen(s, x, y);
        repaint();
    }
    public void hinzufuegen(Aktionsfaehig b, int x, int y) {
        leinwand.hinzufuegen(b, x, y);
        repaint();
    }

    public void hinzufuegenOhneAktionen(JComponent c, double x, double y) {
        leinwand.hinzufuegenOhneAktionen(c, x, y);
        repaint();
    }
    public void hinzufuegen(Schild s, double x, double y) {
        leinwand.hinzufuegen(s, x, y);
        repaint();
    }
    public void hinzufuegen(Aktionsfaehig b, double x, double y) {
        leinwand.hinzufuegen(b, x, y);
        repaint();
    }

    public void hinzufuegen(Figur f, int x, int y) {
        leinwand.hinzufuegen(f, x, y);
        repaint();
    }

    public void hinzufuegen(Figur f, double x, double y) {
        leinwand.hinzufuegen(f, x, y);
        repaint();
    }

    public void hinzufuegen(Bild b, int x, int y) {
        leinwand.hinzufuegen(b, x, y);
        repaint();
    }

    public boolean entfernen(Figur f) {
        boolean erg = leinwand.entfernen(f);
        repaint();
        return erg;
    }

    public void farbeSetzen(String s) {
        leinwand.farbeSetzen(s);
    }

    public void farbeSetzen(int r, int g, int b) {
        leinwand.farbeSetzen(r, g, b);
    }

    public void farbeSetzen() {
        leinwand.farbeSetzen();
    }

    public void hinzufuegen(Leinwand leinwand, double x, double y) {
        this.leinwand.hinzufuegen(leinwand, x, y);
        leinwand.actionListenerSetzen(this);
        repaint();
    }

    public void hinzufuegen(Leinwand leinwand, int x, int y) {
        this.leinwand.hinzufuegen(leinwand, x, y);
        repaint();
    }

    public void hinzufuegen(Karteikasten k, int x, int y) {
        this.leinwand.hinzufuegen(k, x, y);
        repaint();
    }

    public void hinzufuegen(Schieberegler s, int x, int y) {
        leinwand.hinzufuegen(s, x, y);
        repaint();
    }

    public void hinzufuegen(Editor e, int x, int y, int l, int b) {
        leinwand.hinzufuegen(e, x, y, l, b);
        repaint();
    }
    public void hinzufuegen(Tabelle t, int x, int y, int l, int b) {
        leinwand.hinzufuegen(t, x, y, l, b);
        repaint();
    }

    public void hinzufuegen(Symbolleiste s) {
        this.add(s);
        s.actionListenerSetzen(this);
    }

    public void hinzufuegen(Symbolleiste s, int hoehe) {
        s.groesseSetzen(this, hoehe);
        hinzufuegen(s);
    }

    public void hinzufuegen(MausRechtsklickMenu mrklm, JComponent komp) {
        leinwand.hinzufuegen(mrklm, komp);
    }

    public void menuleisteHinzufuegen() {
        setJMenuBar(menuleiste);
    }

    public void menuleisteEntfernen() {
        setJMenuBar(null);
    }

    public void menuleisteHinzufuegen(String[] ueberschriften, String[][] menupunkte) {
        menuleiste.menuleisteHinzufuegen(ueberschriften, menupunkte);
        menuleiste.actionListenerSetzen(this);
        setJMenuBar(menuleiste);
        setVisible(true);
    }

    public void menuleisteHinzufuegen(Menuleiste menuleiste) {
        this.menuleiste = menuleiste;
        menuleiste.actionListenerFuerJedenPunkt(this);
        setJMenuBar(menuleiste);
        setVisible(true);
    }

    public void menuHinzufuegen(String ueberschrift, String[] menupunkte) {
        menuleiste.menuHinzufuegen(ueberschrift, menupunkte);
    }

    public void menuHinzufuegen(Menu menu) {
        for (int i = 0; i < menu.getItemCount(); i++) {
            menu.getItem(i).addActionListener(this);
        }
        menuleiste.add(menu);
        menu.actionListenerSetzen(this);
    }

    public void menuHinzufuegen(String ueberschrift) {
        menuleiste.menuHinzufuegen(ueberschrift);
    }
    public void menuEntfernen(Menu menu) {
        menuleiste.menuEntfernen(menu);
    }

    public boolean menupunktHinzufuegen(String ueberschrift, String menupunkt) {
        return menuleiste.menupunktHinzufuegen(ueberschrift, menupunkt);
    }

    public boolean menupunktHinzufuegen(int i, String menupunkt) {
        return menuleiste.menupunktHinzufuegen(i, menupunkt);
    }
    public void menupunktEntfernen(Menu m, String menupunkt) {
        m.menupunktEntfernen(menupunkt);
    }
    public void menupunktEntfernen(String menu, String menupunkt) {
        menuleiste.menupunktEntfernen(menu, menupunkt);
    }

    public boolean menupunktDeaktivieren(String ueberschrift, String menupunkt) {
        return menuleiste.menupunktDeaktivieren(ueberschrift, menupunkt);
    }

    public boolean menupunktAktivieren(String ueberschrift, String menupunkt) {
        return menuleiste.menupunktAktivieren(ueberschrift, menupunkt);
    }

    public boolean menupunktDeaktivieren(String menupunkt) {
        return menuleiste.menupunktDeaktivieren(menupunkt);
    }

    public boolean menupunktAktivieren(String menupunkt) {
        return menuleiste.menupunktAktivieren(menupunkt);
    }

    public boolean menupunktDeaktivieren(int i, int j) {
        return menuleiste.menupunktDeaktivieren(i, j);
    }

    public boolean menupunktAktivieren(int i, int j) {
        return menuleiste.menupunktAktivieren(i, j);
    }

    public boolean menuDeaktivieren(String ueberschrift) {
        return menuleiste.menuDeaktivieren(ueberschrift);
    }

    public boolean menuAktivieren(String ueberschrift) {
        return menuleiste.menuAktivieren(ueberschrift);
    }

    public boolean menuDeaktivieren(int i) {
        return menuleiste.menuDeaktivieren(i);
    }

    public boolean menuAktivieren(int i) {
        return menuleiste.menuAktivieren(i);
    }

    public void menuleisteAktivieren() {
        menuleiste.setEnabled(true);
    }

    public void menuleisteDeaktivieren() {
        menuleiste.setEnabled(false);
    }

    public Leinwand leinwandGeben() {
        return leinwand;
    }

    public int laengeGeben() {
        int fl = getWidth();
        if (fl == 0) {
            return laenge;
        } else {
            laenge = fl;
            return fl;
        }
    }

    public int breiteGeben() {
        int fl = getHeight();
        if (fl == 0) {
            return breite;
        } else {
            breite = fl;
            return fl;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass().isInstance(new Menupunkt())) {
            if (((Menupunkt) e.getSource()).menuGeben().getClass().isInstance(new Menu(""))) {
                Menupunkt jmi = (Menupunkt) e.getSource();
                Menu menu = (Menu) jmi.menuGeben();
                menuAuswahl(menu.getText(), jmi.getText());
            } else {
                Menupunkt jmi = (Menupunkt) e.getSource();
                JComponent komp = ((Menupunkt) (e.getSource())).menuGeben();
                menuAuswahl(komp, jmi.getText());
            }
        } else {
            if (e.getSource().getClass().isInstance(new Auswahl())) {
                JComboBox selectedChoice = (JComboBox) e.getSource();
                String s = (String) (selectedChoice.getSelectedItem());
                menuAuswahl(e.getSource(), s);
            } else {
                if(e.getSource() instanceof Radioknopf) {
                    Radioknopf r = (Radioknopf)(e.getSource());
                    if(r.gruppeGeben()==null){
                        aktion(r);
                    }
                    else{
                        menuAuswahl(r.gruppeGeben(),r.getText());
                    }
                } else {
                    aktion(e.getSource());
                }
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        wertAenderung(e.getSource());
    }

    public void aktion(Object objekt) {
    }

    public void wertAenderung(Object objekt) {
    }

    public void menuAuswahl(String ueberschrift, String menupunkt) {
    }

    public void menuAuswahl(Object objekt, String menupunkt) {
    }
}
