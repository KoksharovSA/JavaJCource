package org.example.hw5;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientResponseService extends Thread {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private List<Socket> clientSocketList;
    public ClientResponseService(Socket socket, List<Socket> clientSocketList) throws IOException {
        this.socket = socket;
        this.clientSocketList = clientSocketList;
        in = new DataInputStream(this.socket.getInputStream());
    }

    @Override
    public void run() {
        String word;
        try {
            while (true) {
                word = in.readUTF();
                if (word.equals("stop")) {
                    break;
                }
                System.out.println("Клиент " + socket.getRemoteSocketAddress() + " :" + word);
                for(Socket sock: clientSocketList){

                    out = new DataOutputStream(sock.getOutputStream());
                    out.writeUTF("Клиент " + socket.getRemoteSocketAddress() + " :" + word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
