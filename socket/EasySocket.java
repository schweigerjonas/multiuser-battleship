/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.*;
import java.net.*;

public class EasySocket{
    public PrintWriter out;
    public BufferedReader in;
    public Socket socket;

    public EasySocket(String adresse, int port)throws IOException{
        this(new Socket(adresse, port));
    }
    public EasySocket(Socket socket)throws IOException{
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public void sendMessage(String message){
        out.println(message);
    }

    public String readMessage(){
        String erg;
        try{
           erg = in.readLine(); 
        }catch(Exception e){erg = "not readable";}
        return erg;
    }
    public void close(){
        try{
           socket.close(); 
        }catch(Exception e){}
    }
    public boolean isConnected(){
        return socket.isConnected();
    }
}