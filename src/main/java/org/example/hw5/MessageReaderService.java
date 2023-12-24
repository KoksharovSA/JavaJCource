package org.example.hw5;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageReaderService extends Thread {
    private Socket server;
    private DataInputStream in;

    public MessageReaderService(Socket server) throws IOException {
        this.server = server;
    }

    @Override
    public void run() {
        String word;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                this.in = new DataInputStream(this.server.getInputStream());
                word = in.readUTF();
                System.out.println(word);
                in.close();
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
