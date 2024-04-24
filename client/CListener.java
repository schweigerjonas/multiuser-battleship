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
public class CListener extends Thread {
    private final Client client;
    private boolean closed = false;

    public CListener(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (!closed) {
            String message = client.getSocket().readMessage();
            if (message == null) {
                closed = true;
            } else {
                client.receiveMessage(message);
                
                if (message.equals(client.getFinalMessage())) {
                    closed = true;
                }
            }
        }
    }
}
