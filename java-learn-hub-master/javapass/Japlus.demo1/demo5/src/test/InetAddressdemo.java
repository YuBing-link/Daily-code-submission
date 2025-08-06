<<<<<<< HEAD
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
=======
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
>>>>>>> bdeb85edff313e367535c724cc175d32e969374e
