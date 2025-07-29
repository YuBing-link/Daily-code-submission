package reSendUDPdemo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerDome {
    public static void main(String[] args) {

        try {
            System.out.println("---------------服务端----------------");
            DatagramSocket socket = new DatagramSocket(8080);
            byte[] buf = new byte[1024*64];
            DatagramPacket p = new DatagramPacket(buf, 0, buf.length);
            while (true) {
                socket.receive(p);
                System.out.println(new String(buf,0, p.getLength()));
                System.out.println("ip"+p.getAddress().getHostAddress());
                System.out.println("端口"+p.getPort());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
