package TCPDEMO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo1 {

    public static void main(String[] args) {
        try {
            System.out.println("---------------客户端-----------------");
            Socket socket = new Socket("127.0.0.1",8080);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            while(true) {
                System.out.println("请输入：");
                String line = sc.nextLine();
                if(line.equals("exit")){
                    System.out.println("退出");
                    socket.close();
                    break;
                }
                out.writeUTF(line);
                out.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
