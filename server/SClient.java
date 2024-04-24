/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import socket.EasySocket;

/**
 *
 * @author Frank
 */
public class SClient {   
    protected EasySocket socket;
    protected String name;
    protected int id;
    private Object referencedObject;
    private boolean isDisconnected = false;
    
    public SClient(EasySocket socket){      
        this.socket = socket;
    }

    /**
     * @return the socket
     */
    public EasySocket getSocket() {
        return socket;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the referencedObject
     */
    public Object getReferencedObject() {
        return referencedObject;
    }

    /**
     * @param referencedObject the referencedObject to set
     */
    public void setReferencedObject(Object referencedObject) {
        this.referencedObject = referencedObject;
    }
    public void disconnect(){
        this.isDisconnected = true;
        socket.close();
    }

    /**
     * @return the isDisconnected
     */
    public boolean isDisconnected() {
        return isDisconnected;
    }
}
