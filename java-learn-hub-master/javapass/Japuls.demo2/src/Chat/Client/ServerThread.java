package Chat.Client;

import javax.swing.text.DateFormatter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;

public class ServerThread extends Thread {
    private Socket socket;
    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (true) {
                switch (dis.readInt()){
                    case 1->{
                        //登录
                        String nickname=dis.readUTF();
                        Server.OnlineServer.put(socket,nickname);
                        updateClientUserList();
                    }
                    case 2->{
                        //群聊消息
                        SendAll(dis.readUTF());

                    }

                }
            }

        } catch (IOException e) {
            System.out.println("客户端下线:"+socket.getInetAddress().getHostAddress());
            Server.OnlineServer.remove(socket);
            updateClientUserList();
        }


    }

    private void SendAll(String s) {
       String msg=new StringBuffer().append(Server.OnlineServer.get(socket)).append(" ").append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a").format(LocalDateTime.now()))
                .append("\r\n").append(s).append("\r\n").toString();
                for (Socket socket : Server.OnlineServer.keySet()){
                    try {
                        DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
                        dos.writeInt(2);
                        dos.writeUTF(msg);
                        dos.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
    }

    private void updateClientUserList() {
        try {

            Collection<String> OnlineList = Server.OnlineServer.values();
            for (Socket socket:Server.OnlineServer.keySet()) {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(1);
                dos.writeInt(OnlineList.size());
                for (String s : OnlineList) {
                    dos.writeUTF(s);
                }
                dos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
