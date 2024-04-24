/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Frank
 */
public abstract class Server {   
    private final ArrayList<SClient> clients = new ArrayList<>();
    private final int port;
    protected int defaultPort = 4444;
    //protected String helloText = ", welcome to our beautiful service!";
    private boolean isShutDown = false;
    public int nextId = 0;
    private int maxNumberOfClients = 999999999;
    //public Serverprogram serverprogram;

    public Server(int port) {
        this.port = port;
    }
    
    public Server() {
        this.port = defaultPort;
    }
    public Server(int port, int maxNumberOfClients){
        this(port);
        this.maxNumberOfClients = maxNumberOfClients;
    }
    public void start() throws IOException {
        new SConnect(port, this).start();
    }

    public abstract void receiveMessage(String message, SClient client);

    public void sendMessage(String message, SClient client) {
        try {
            client.getSocket().sendMessage(message);
        } catch (Exception e) {
            clientLost(client);
        }
    }
    public void disconnect(SClient client){
        client.disconnect();
        clients.remove(client);
    }
    public void sendAll(String message) {
        for (int i = 0; i < clients.size(); i++) {
            this.sendMessage(message, clients.get(i));
        }
    }

    public void newClient(SClient client) {
        clients.add(client);
    }

    public void shutDown() {
        this.isShutDown = true;
    }

    public boolean isShutDown() {
        return isShutDown;
    }

    public synchronized int createId() {
        int erg = nextId;
        nextId++;
        return erg;
    }
    public void removeClient(SClient client){
        if(clients.contains(client)){
            this.sendMessage("removed", client);
            clients.remove(client);
        }
    }
    public void clientLost(SClient client) {
        clients.remove(client);
        System.out.println("lost client " + client.getId() + " " + client.getName());
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

    /**
     * @return the maxNumberOfClients
     */
    public int getMaxNumberOfClients() {
        return maxNumberOfClients;
    }

    /**
     * @param maxNumberOfClients the maxNumberOfClients to set
     */
    public void setMaxNumberOfClients(int maxNumberOfClients) {
        this.maxNumberOfClients = maxNumberOfClients;
    }
    public boolean connectionPossible(){
        return clients.size() < this.maxNumberOfClients;
    }
    public int aktNumberOfClients(){
        return clients.size();
    }
}
