<<<<<<< HEAD
package reTCPDEMO;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo1 {
    public static void main(String[] args) {
        try {


                System.out.println("---------------服务端----------------");
                ServerSocket serverSocket = new ServerSocket(8080);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerThread(socket)).start();
                System.out.println("客户端上线ip："+socket.getInetAddress().getHostAddress());
            }


        } catch (IOException e) {
            e.printStackTrace();

        }


    }

}
=======
package reTCPDEMO;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo1 {
    public static void main(String[] args) {
        try {


                System.out.println("---------------服务端----------------");
                ServerSocket serverSocket = new ServerSocket(8080);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerThread(socket)).start();
                System.out.println("客户端上线ip："+socket.getInetAddress().getHostAddress());
            }


        } catch (IOException e) {
            e.printStackTrace();

        }


    }

}
>>>>>>> bdeb85edff313e367535c724cc175d32e969374e
