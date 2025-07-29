package TCPDEMO;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo1 {
    public static void main(String[] args) {
        try {
            System.out.println("---------------服务端----------------");
            Socket socket = new ServerSocket(8080).accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            while (true) {
                System.out.println( in.readUTF());
                System.out.println(socket.getInetAddress().getHostAddress());
                System.out.println(socket.getPort());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
