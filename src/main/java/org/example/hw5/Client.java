package org.example.hw5;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client extends Thread{
    private String serverName;
    int port;
    private Socket server;

    public Client(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
    }
    public void clientConnect() throws IOException {
        System.out.println("Подключение к " + serverName + " на порт " + port);
        this.server = new Socket(this.serverName, this.port);
        System.out.println("Подкючились к " + server.getRemoteSocketAddress());
    }

    public void clientDisconnect() throws IOException {
        this.server.close();
    }

    public void sendMessage(String message){
        try {
            OutputStream outToServer = server.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(message);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
            clientConnect();
            MessageReaderService messageReaderService = new MessageReaderService(server);
            messageReaderService.start();
            while (true){
                Scanner in = new Scanner(System.in);
                System.out.println("Введите сообщение");
                String message = in.next();
                if (message.equals("stop")){
                    break;
                }
                sendMessage(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
