/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Frank
 */
public class SClientThread extends Thread{
    private final SClient client;
    private final Server server;
    private final String finalMessage = "close";
    
    public SClientThread(SClient client, Server server){
        this.client = client;
        this.server = server;
    }

    @Override
    public void run(){
        String message;
        message = client.getSocket().readMessage();
        client.setName(message);
        client.setId(server.createId());
        do {
            message = client.getSocket().readMessage();
            if(message.equals(finalMessage)){
                server.disconnect(client);
            }else{
                server.receiveMessage(message, client);
            }
        } while (!client.isDisconnected() && !server.isShutDown());
        client.getSocket().close();
    }
}