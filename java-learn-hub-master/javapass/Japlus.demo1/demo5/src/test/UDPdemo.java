package test;

import java.io.IOException;
import java.net.*;

public class UDPdemo {
    public static void main(String[] args) {
        try {
            System.out.println("------------------客户端-------------------");
            DatagramSocket ds = new DatagramSocket();
            byte[] bytes="无敌了，6660".getBytes();
            DatagramPacket packet = new DatagramPacket(bytes,bytes.length, InetAddress.getLocalHost(),8080);
            ds.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
