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
    }

    @Override
    public void run() {
        String word;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                in = new DataInputStream(this.socket.getInputStream());
                word = in.readUTF();
                in.close();
                if (word.equals("stop")) {
                    break;
                }
                System.out.println("Клиент " + socket.getRemoteSocketAddress() + " :" + word);
                for(Socket sock: clientSocketList){
                    out = new DataOutputStream(sock.getOutputStream());
                    out.writeUTF("Клиент " + socket.getRemoteSocketAddress() + " :" + word);
                    out.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            if (Thread.currentThread().isInterrupted()){
                try {
                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
