package org.example.hw5;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    private ServerSocket serverSocket;
    List<Socket> clientSocketList = new ArrayList<>();

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        while (true) {
            try {

                System.out.println("Ожидание клиента на порт " +
                        serverSocket.getLocalPort() + "...");
                Socket client = serverSocket.accept();
                clientSocketList.add(client);
                System.out.println("К серверу подключился : " + client.getRemoteSocketAddress());
                ClientResponseService clientResponseService = new ClientResponseService(client, clientSocketList);
                clientResponseService.start();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

        }
    }
}
