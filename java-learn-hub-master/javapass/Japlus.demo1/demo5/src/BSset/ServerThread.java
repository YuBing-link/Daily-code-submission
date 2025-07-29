package BSset;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket socket;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type:text/html");
            pw.println();
            pw.println("<html>");
            pw.println("<body>");
            pw.println("<h1 style='color:red'>Hello World</h1>");
            pw.println("</body>");
            pw.println("</html>");
            pw.flush();
            pw.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("客户端下线ip："+socket.getInetAddress().getHostAddress());
        }
    }
}
