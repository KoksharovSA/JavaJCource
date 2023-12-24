package org.example.hw5;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageReaderService extends Thread{
    private Socket server;
    private DataInputStream in;

    public MessageReaderService(Socket server) throws IOException {
        this.server = server;
        this.in = new DataInputStream(this.server.getInputStream());;
    }

    @Override
    public void run() {
        while (true){
            String word;
            try {
                while (true) {
                    word = in.readUTF();
                    System.out.println(word);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
