package reTCPDEMO;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket socket;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            while (true) {
                System.out.println(in.readUTF());
                System.out.println(socket.getInetAddress().getHostAddress());
                System.out.println(socket.getPort());
            }
        } catch (IOException e) {
            System.out.println("客户端下线ip："+socket.getInetAddress().getHostAddress());
        }
    }
}
