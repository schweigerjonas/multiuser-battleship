/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import socket.EasyServerSocket;
import socket.EasySocket;

/**
 *
 * @author Frank
 */
public class SConnect extends Thread {

    private final EasyServerSocket serverSocket;
    private final Server server;

    public SConnect(int port, Server server) throws IOException {
        serverSocket = new EasyServerSocket(port);
        this.server = server;
    }

    @Override
    public void run() {
        while (!server.isShutDown()) {
            EasySocket clientSocket = serverSocket.connect();
            if (server.connectionPossible()) {
                SClient client = new SClient(clientSocket);
                SClientThread ct = new SClientThread(client, server);
                ct.start();
                server.newClient(client);
            }else{
                clientSocket.sendMessage("connection refused");
            }
        }
        try {
            serverSocket.close();

        } catch (IOException ex) {

        }
    }
}
