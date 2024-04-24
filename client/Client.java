/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Frank
 */
import java.io.*;
import socket.EasySocket;

public abstract class Client{
    protected EasySocket socket;
    public String name = "Client";
    protected String finalMessage = "close";
    
    public Client(){}
    public Client(String name){
        this.name = name;
    }
    
    public void connect(String address, int port) throws IOException{
        socket = new EasySocket(address, port);
        getSocket().sendMessage(name);
        new CListener(this).start();
    }
    public void disconnect(){
        getSocket().sendMessage(getFinalMessage());
        socket.close();
    }
    public abstract void receiveMessage(String text);

    public void sendMessage(String message){
        getSocket().sendMessage(message);
    }

    /**
     * @return the socket
     */
    public EasySocket getSocket() {
        return socket;
    }

    /**
     * @param finalMessage the finalMessage to set
     */
    public void setFinalMessage(String finalMessage) {
        this.finalMessage = finalMessage;
    }

    /**
     * @return the finalMessage
     */
    public String getFinalMessage() {
        return finalMessage;
    }
    public int toInt(String s){
        return Integer.parseInt(s);
    }
    public double toDouble(String s){
        return Double.parseDouble(s);
    }
    public float toFloat(String s){
        return Float.parseFloat(s);
    }
    public boolean toBoolean(String s){
        return Boolean.parseBoolean(s);
    }
    public byte toByte(String s){
        return Byte.parseByte(s);
    }
    public long toLong(String s){
        return Long.parseLong(s);
    }
}
