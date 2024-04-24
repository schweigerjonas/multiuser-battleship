/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.*;
import java.net.*;

public class EasyServerSocket extends ServerSocket{
    
    public EasyServerSocket(int port)throws IOException{
        super(port);
    }
    public EasySocket connect(){
        EasySocket es = null; 
        try{
           Socket s = super.accept();
           es = new EasySocket(s);
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return es;
    }  
}