/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

/**
 *
 * @author Fiedler
 */
public class Bildknopf extends Knopf{
    public Bildknopf(){}
    public Bildknopf(Bild b, int x, int y){
        groesseSetzen(x,y);
        bildSetzen(b);
    }
    public void bildSetzen(Bild b){
        BildIcon bi = new BildIcon();
        bi.setImage(b.bildGeben());
        this.setIcon(bi);
    }
}
