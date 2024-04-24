/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fenster;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author Fiedler
 */
public class Menuleiste extends JMenuBar {

    private ActionListener al;

    public Menuleiste() {
    }

    public void menuleisteHinzufuegen(String[] ueberschriften, String[][] menupunkte) {
        for (int i = 0; i < ueberschriften.length; i++) {
            Menu m =new Menu(ueberschriften[i]);
            add(m);
            for (int j = 0; j < menupunkte[i].length; j++) {
                Menupunkt item = new Menupunkt(menupunkte[i][j]);
                item.menuSetzen(m);
                if (al != null) {
                    item.addActionListener(al);
                }
                this.getMenu(i).add(item);
            }
        }
    }

    public void actionListenerSetzen(ActionListener a) {
        al = a;
    }

    public void actionListenerFuerJedenPunkt(ActionListener a) {
        al = a;
        for (int i = 0; i < this.getMenuCount(); i++) {
            for (int j = 0; j < this.getMenu(i).getItemCount(); j++) {
                getMenu(i).getItem(j).addActionListener(a);
            }
        }
    }

    public void menuHinzufuegen(String ueberschrift, String[] menupunkte) {
        Menu menu = new Menu(ueberschrift);
        for (String s : menupunkte) {
            Menupunkt item = new Menupunkt(s);
            item.menuSetzen(menu);
            if (al != null) {
                item.addActionListener(al);
            }
            menu.add(item);
        }
        add(menu);
    }

    public void menuHinzufuegen(String ueberschrift) {
        Menu m = new Menu(ueberschrift);
        m.actionListenerSetzen(al);
        add(m);
    }
    public void menuEntfernen(Menu m) {
        remove(m);
    }
    public boolean menupunktHinzufuegen(String ueberschrift, String menupunkt) {
        for (int i = 0; i < this.getMenuCount(); i++) {
            if (getMenu(i).getText().equals(ueberschrift)) {
                Menupunkt item = new Menupunkt(menupunkt);
                item.menuSetzen((Menu)getMenu(i));
                if (al != null) {
                    item.addActionListener(al);
                }
                getMenu(i).add(item);
                return true;
            }
        }
        return false;
    }

    public boolean menupunktHinzufuegen(int i, String menupunkt) {
        if (i < this.getMenuCount()) {
            Menupunkt item = new Menupunkt(menupunkt);
            item.menuSetzen((Menu)getMenu(i));
            if (al != null) {
                item.addActionListener(al);
            }
            getMenu(i).add(item);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean menuDeaktivieren(int i) {
        if (i < this.getMenuCount()) {
            this.getMenu(i).setEnabled(false);
            return true;
        } else {
            return false;
        }
    }

    public boolean menuAktivieren(int i) {
        if (i < this.getMenuCount()) {
            this.getMenu(i).setEnabled(true);
            return true;
        } else {
            return false;
        }
    }
    public void menupunktEntfernen(Menu m, String menupunkt) {
        m.menupunktEntfernen(menupunkt);
    }
    public void menupunktEntfernen(String menu, String menupunkt) {
        for (int i = 0; i < getMenuCount(); i++) {
            if (menu.equals(this.getMenu(i).getText())) {
                ((Menu)getMenu(i)).menupunktEntfernen(menupunkt);
                return;
            }
        }
    }
    public boolean menuDeaktivieren(String ueberschrift) {
        for (int i = 0; i < getMenuCount(); i++) {
            if (ueberschrift.equals(this.getMenu(i).getText())) {
                this.getMenu(i).setEnabled(false);
                return true;
            }
        }
        return false;
    }

    public boolean menuAktivieren(String ueberschrift) {
        for (int i = 0; i < getMenuCount(); i++) {
            if (ueberschrift.equals(this.getMenu(i).getText())) {
                this.getMenu(i).setEnabled(true);
                return true;
            }
        }
        return false;
    }

    public boolean menupunktDeaktivieren(String ueberschrift, String menupunkt) {
        for (int i = 0; i < getMenuCount(); i++) {
            Menu m = (Menu) getMenu(i);
            if (m.getText().equals(ueberschrift)) {
                return m.menupunktDeaktivieren(menupunkt);
            }
        }
        return false;
    }

    public boolean menupunktAktivieren(String ueberschrift, String menupunkt) {
        for (int i = 0; i < getMenuCount(); i++) {
            Menu m = (Menu) getMenu(i);
            if (getMenu(i).getText().equals(ueberschrift)) {
                return m.menupunktAktivieren(menupunkt);
            }
        }
        return false;
    }

    public boolean menupunktDeaktivieren(String menupunkt) {
        for (int i = 0; i < getMenuCount(); i++) {
            Menu m = (Menu) getMenu(i);
            return m.menupunktDeaktivieren(menupunkt);
        }
        return false;
    }
    public boolean menupunktAktivieren(String menupunkt) {
        for (int i = 0; i < getMenuCount(); i++) {
            Menu m = (Menu) getMenu(i);
            return m.menupunktAktivieren(menupunkt);
        }
        return false;
    }
    public boolean menupunktDeaktivieren(int i, int j){
        if(i<getMenuCount()){
            Menu m = (Menu)getMenu(j);
            return m.menupunktDeaktivieren(j);
        }
        else{
            return false;
        }
    }
    public boolean menupunktAktivieren(int i, int j){
        if(i<getMenuCount()){
            Menu m = (Menu)getMenu(j);
            return m.menupunktAktivieren(j);
        }
        else{
            return false;
        }
    }
    public Menu menuGeben(String ueberschrift){
        for (int i = 0; i < getMenuCount(); i++) {
            if (ueberschrift.equals(this.getMenu(i).getText())) {
                return (Menu)getMenu(i);
            }
        }
        return null;
    }
    public Menu menuGeben(int i){
         if(i<getMenuCount()){
            return (Menu)getMenu(i);
        }
        else{
            return null;
        }
    }
    public Menupunkt menupunktGeben(String ueberschrift, String menupunkt){
        Menu menu = menuGeben(ueberschrift);
        if(menu == null){
            return null;
        }
        else {
            return menu.menupunktGeben(menupunkt);
        }
    }
    public Menupunkt menupunktGeben(String menupunkt){
        for (int i = 0; i < getMenuCount(); i++) {
            if(((Menu)getMenu(i)).menupunktGeben(menupunkt)!=null){
                return ((Menu)getMenu(i)).menupunktGeben(menupunkt);
            }
        }
        return null;
    }
    public Menupunkt menpunktGeben(int i, int j){
        if((i<getMenuCount()) && (j<getMenu(i).getItemCount())){
            return menuGeben(i).menupunktGeben(j);
        }
        else{
            return null;
        }
    }

}
