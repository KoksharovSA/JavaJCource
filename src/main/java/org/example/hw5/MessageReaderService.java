package org.example.hw5;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageReaderService extends Thread {
    private Socket server;
    private DataInputStream in;

    public MessageReaderService(Socket server) throws IOException {
        this.server = server;
        this.in = new DataInputStream(this.server.getInputStream());
    }

    @Override
    public void run() {
        String word;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                word = in.readUTF();
                System.out.println(word);
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            if (Thread.currentThread().isInterrupted()){
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
