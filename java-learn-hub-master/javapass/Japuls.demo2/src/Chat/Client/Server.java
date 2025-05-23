package Chat.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static Map<Socket, String > OnlineServer = new HashMap<>();
    public static void main(String[] args) {
        System.out.println("服务端启动。。。。。。。。。");
        try {

            ServerSocket serverSocket = new ServerSocket(Constant.PORT);
            while (true) {
                System.out.println("等待客服端连接。。。。。。。。。");
                new ServerThread(serverSocket.accept()).start();
                System.out.println("客服端连接成功。。。。。。。。。");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
