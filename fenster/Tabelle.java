/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fenster;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Fiedler
 */
public class Tabelle extends JTable {

    public Tabelle(String[][] daten, String[] spaltennamen) {
        super(daten, spaltennamen);
        this.setEnabled(false);
        init();
    }

    public Tabelle(int zeilen, int spalten) {
        this.setModel(new Tabellenmodell(zeilen, spalten));
        init();
    }

    public Tabelle(int zeilen, int spalten, String[] spaltennamen) {
        this.setModel(new Tabellenmodell(zeilen, spalten, spaltennamen));
        init();
    }
    public void init(){
        this.setRowSelectionAllowed(false);
        this.setRowHeight(20);
        /*for(int i = 0; i <this.getColumnModel().getColumnCount(); i++){
            this.getColumn(this.getColumnModel().getColumn(i).getIdentifier()).setMinWidth(180);
        }*/
    }
    public void groesseSetzen(int laenge, int breite) {
        setSize(laenge, breite);
    }
    public void inhaltSetzen(String inhalt, int i, int j){
        getModel().setValueAt(inhalt, i, j);
    }
    public String inhaltGeben(int i, int j){
        TableModel tm = this.getModel();
        return (String)tm.getValueAt(i, j);
    }
    public void eingabeMoeglichSetzen(int i, int j, boolean moeglich){
        TableModel tm = this.getModel();
        ((Tabellenmodell)tm).setEditable(i,j,moeglich);
    }
    public void eingabeMoeglichSetzenAlle(boolean moeglich){
        TableModel tm = this.getModel();
        ((Tabellenmodell)tm).alleEditierbarSetzen(moeglich);
    }
    public void kommentarHinzufuegen(String htmltext){
        this.setToolTipText(htmltext);
    }
    public void spaltenhoeheSetzen(int hoehe){
        setRowHeight(hoehe);
    }
}

class Tabellenmodell extends AbstractTableModel {

    private int zeilen,  spalten;
    private String[] spaltennamen;
    private boolean[][] editable;
    String[][] inhalte;

    public Tabellenmodell(int z, int s) {
        init(z,s);
    }

    public Tabellenmodell(int z, int s, String[] spaltennamen) {
        init(z,s);
        this.spaltennamen = spaltennamen;
    }
    public void init(int z, int s){
        zeilen = z;
        spalten = s;
        inhalte = new String[z][s];
        editable = new boolean[z][s];
        alleEditierbarSetzen(true);
    }
    public void alleEditierbarSetzen(boolean e){
        for(int i = 0; i < zeilen; i++){
            for(int j = 0; j < spalten; j++){
                editable[i][j]=true;
            }
        }
    }
    public int getRowCount() {
        return zeilen;
    }

    public int getColumnCount() {
        return spalten;
    }

    @Override
    public void setValueAt(Object wert, int i, int j){
        inhalte[i][j] = (String)wert;
        fireTableCellUpdated(i,j);
    }

    public Object getValueAt(int row, int col) {
        return inhalte[row][col];
    }

    @Override
    public String getColumnName(int spalte) {
        if (spalte < spalten) {
            if (spaltennamen == null) {
                return super.getColumnName(spalte);
            } else {
                return spaltennamen[spalte];
            }
        } else {
            return "";
        }
    }
    @Override
    public boolean isCellEditable(int i, int j){
        return editable[i][j];
    }
    public void setEditable(int i, int j, boolean e){
        editable[i][j] = e;
    }
}
