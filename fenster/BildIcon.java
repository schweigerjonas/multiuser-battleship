/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Fiedler
 */
public class BildIcon extends ImageIcon{
    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y){
        super.paintIcon(c,g,x,y);
        g.drawImage(this.getImage(), 0,0,c.getWidth(), c.getHeight(), c);
    }

}
