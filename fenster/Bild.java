/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Bild extends Figur{
    protected int laenge = 0, breite = 0;
    private BufferedImage image;
    private boolean korrektGeladen = true;
    private ImageObserver imageObserver;
    /**
     * Die Länge und die Breite in Pixeln müssen übergeben werden.
     */
    public Bild(String pfad){
        bildLaden(pfad);
        if(korrektGeladen){
            laenge = image.getWidth(); 
            breite = image.getHeight();
        }
    }
    public Bild(String pfad, int l, int b){
        bildLaden(pfad);
        this.laenge=l;
        this.breite=b;
    }
    private boolean bildLaden(String pfad){
        try{
            image = ImageIO.read(new File(pfad));

        }
        catch(IOException e){
            System.out.println("Datei nicht gefunden!");
            korrektGeladen = false;
            return false;
        }
        return true;
    }
    public void imageObserverSetzen(ImageObserver io){
        imageObserver = io;
    }
    @Override
    public void zeichnen(Graphics g){
        if(korrektGeladen){
            g.drawImage(image, x, y, laenge, breite, imageObserver);
        }
    }
    public int laengeGeben(){
        return laenge;
    }
    public int breiteGeben(){
        return breite;
    }
    public void groesseAendern(int l, int b){
        laenge = l; breite = b;
    }
    public double verhaeltnisLaengeBreiteOriginalBild(){
        return (double)image.getWidth()/(double)image.getHeight();
    }
    public BufferedImage bildGeben(){
        return image;
    }
}
