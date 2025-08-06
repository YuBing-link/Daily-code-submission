<<<<<<< HEAD
package BSset;

import reTCPDEMO.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class ServerDemo1 {
    public static void main(String[] args) {
        try {


                System.out.println("---------------服务端----------------");
                ServerSocket serverSocket = new ServerSocket(8080);
            ExecutorService pool=new ThreadPoolExecutor(3,10,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

            while (true) {
                Socket socket = serverSocket.accept();
                pool.execute(new ServerThread(socket));
                System.out.println("客户端上线ip："+socket.getInetAddress().getHostAddress());
            }


        } catch (IOException e) {
            e.printStackTrace();

        }


    }

}
=======
package BSset;

import reTCPDEMO.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class ServerDemo1 {
    public static void main(String[] args) {
        try {


                System.out.println("---------------服务端----------------");
                ServerSocket serverSocket = new ServerSocket(8080);
            ExecutorService pool=new ThreadPoolExecutor(3,10,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

            while (true) {
                Socket socket = serverSocket.accept();
                pool.execute(new ServerThread(socket));
                System.out.println("客户端上线ip："+socket.getInetAddress().getHostAddress());
            }


        } catch (IOException e) {
            e.printStackTrace();

        }


    }

}
>>>>>>> bdeb85edff313e367535c724cc175d32e969374e
