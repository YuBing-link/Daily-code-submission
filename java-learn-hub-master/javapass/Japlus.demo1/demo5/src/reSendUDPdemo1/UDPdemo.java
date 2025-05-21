package reSendUDPdemo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPdemo {
    public static void main(String[] args) {
        try {
            System.out.println("------------------客户端-------------------");
            DatagramSocket ds = new DatagramSocket();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("请输入：");
                String line = sc.nextLine();
                if(line.equals("exit")){
                    System.out.println("退出");
                    ds.close();
                    break;
                }
                DatagramPacket packet = new DatagramPacket(line.getBytes(),line.getBytes().length, InetAddress.getLocalHost(),8080);
                ds.send(packet);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
