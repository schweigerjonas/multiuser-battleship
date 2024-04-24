/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Fiedler
 */
public class Dialogmanager{

    public Dialogmanager(){
     
    }
    public void nachrichtZeigen(String text){
        JOptionPane.showMessageDialog( null, text);
    }
    public String textEingeben(String text){
        return JOptionPane.showInputDialog(text);
    }
    public int ganzeZahlEingeben(String text){
        return Integer.parseInt(JOptionPane.showInputDialog(text));
    }
    public double dezimalZahlEingeben(String text){
        return Double.parseDouble(JOptionPane.showInputDialog(text));
    }
    public String auswaehlen(String beschreibung, String text,String[] optionen){
        return (String) JOptionPane.showInputDialog( null,
              beschreibung, text, JOptionPane.QUESTION_MESSAGE,
              null, optionen, optionen[0] );
    }

    public int jaNeinAbbruch(String text){
        String[] optionen = { "Ja", "Nein", "Abbruch" };
        int n = JOptionPane.showOptionDialog( null,
              text,      // Fragetext
              "Ja/Nein/Abbrechen",  // Titel
              JOptionPane.YES_NO_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE,  // Icon
              null, optionen,optionen[0] );
        if(n == JOptionPane.YES_OPTION)return 1;
        if(n == JOptionPane.NO_OPTION)return -1;
        return 0;
    }

    public boolean jaNein(String text){
        String[] optionen = { "Ja", "Nein" };
        int n = JOptionPane.showOptionDialog( null,
              text,      // Fragetext
              "Ja/Nein",  // Titel
              JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE,  // Icon
              null, optionen,optionen[0] );
        return (n == JOptionPane.YES_OPTION);
    }

    public File dateiAuswahl(String knopfBeschriftung){
        JFileChooser fc = new JFileChooser();
        int state = fc.showDialog(null, knopfBeschriftung);

        if ( state == JFileChooser.APPROVE_OPTION ){
            File file = fc.getSelectedFile();
            System.out.println( file.getName() );
            return file;
        }
        else{
            System.out.println( "Auswahl abgebrochen" );
            return null;
        }
    }

    public void speichern(Object o, File f) {
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
        } catch (IOException e) // Schreib- Leseprobleme
        {
            System.err.println("Schreibprobleme!");
        }
    }

    public void speichern(Object o, String pfad) {
        try {
            FileOutputStream fos = new FileOutputStream(pfad);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
            oos.close();
        } catch (IOException e) // Schreib- Leseprobleme
        {
            System.err.println("Schreibprobleme!");
        }
    }

    public Object laden(String pfad) {
        Object o = null;
        try {
            FileInputStream fis = new FileInputStream(pfad);
            ObjectInputStream ois = new ObjectInputStream(fis);
            o = ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) // Datei gibt’s nich’
        {
            System.err.println("Datei existiert nicht!");
        } catch (IOException e) // Schreib- Leseprobleme
        {
            System.err.println("Leseprobleme!");
        } catch (ClassNotFoundException e) {
            System.err.println("Klasse existiert nicht!");
        }
        return o;
    }
    public void listeSpeichern(String pfad, ArrayList<Object> oliste){
        try {
            FileOutputStream fos = new FileOutputStream(pfad);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for(Object o: oliste){
                oos.writeObject(o);
            }
            oos.close();
        } catch (IOException e) // Schreib- Leseprobleme
        {
            System.err.println("Schreibprobleme!");
        }
    }
    public ArrayList<Object> listeLaden(String pfad){
        ArrayList<Object> erg = new ArrayList<Object>();
        try {
            FileInputStream fis = new FileInputStream(pfad);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            while(o!=null){
                erg.add(o);
                o = ois.readObject();
            }
            ois.close();
        } catch (FileNotFoundException e) // Datei gibt’s nich’
        {
            System.err.println("Datei existiert nicht!");
        } catch (IOException e) // Schreib- Leseprobleme
        {
            System.err.println("Leseprobleme!");
        } catch (ClassNotFoundException e) {
            System.err.println("Klasse existiert nicht!");
        }
        return erg;
    }
    public Object laden(File f) {
        Object o = null;
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            o = ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) // Datei gibt’s nich’
        {
            System.err.println("Datei existiert nicht!");
        } catch (IOException e) // Schreib- Leseprobleme
        {
            System.err.println("Leseprobleme!");
        } catch (ClassNotFoundException e) {
            System.err.println("Klasse existiert nicht!");
        }
        return o;
    }

    public void speichern(Object o){
        speichern(o, this.dateiAuswahl("Speichern"));
    }

    public Object laden(){
        return laden(this.dateiAuswahl("Laden"));
    }
}
