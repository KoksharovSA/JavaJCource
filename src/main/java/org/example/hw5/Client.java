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
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF(message);
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
            clientConnect();
            MessageReaderService messageReaderService = new MessageReaderService(server);
            messageReaderService.setDaemon(true);
            messageReaderService.start();
            while (!Thread.currentThread().isInterrupted()){
                Scanner in = new Scanner(System.in);
                System.out.println("Введите сообщение");
                String message = in.next();
                if (message.equals("stop")){
                    break;
                }
                sendMessage(message);
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            if (Thread.currentThread().isInterrupted()){
                try {
                    server.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
