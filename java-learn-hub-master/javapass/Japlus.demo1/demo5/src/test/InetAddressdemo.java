package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressdemo {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println(ip);
            System.out.println(ip.getHostName());
            System.out.println(ip.getHostAddress());

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }


    }


}
