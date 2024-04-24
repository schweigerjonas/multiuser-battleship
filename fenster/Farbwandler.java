/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fenster;

import java.awt.Color;

/**
 *
 * @author Fiedler
 */
public class Farbwandler {
    private Color[] colors ={Color.black, Color.blue, Color.cyan, Color.gray, Color.green,
        Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow};
    private String[] strings = {"schwarz", "blau", "tuerkis","grau","gruen","magenta","orange",
        "pink","rot","weiss","gelb"};

    public Color stringToColor(String s){
        for(int i = 0; i < strings.length; i++){
            if(s.equals(strings[i])){
                return colors[i];
            }
        }
        return Color.lightGray;
    }
    public Color[] returnColors(){
        return colors;
    }
    public String[] returnStrings(){
        return strings;
    }
}
