package Chat.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

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
                    case 3->{
                        SendPrivate(dis.readUTF(),dis.readUTF());
                    }

                }
            }

        } catch (IOException e) {
            System.out.println("客户端下线:"+socket.getInetAddress().getHostAddress());
            Server.OnlineServer.remove(socket);
            updateClientUserList();
        }


    }

    private void SendPrivate(String userName, String m) {
        String msg = new StringBuffer().append(Server.OnlineServer.get(socket))
            .append(" ").append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a").format(LocalDateTime.now()))
            .append("\r\n").append(m).append("\r\n").toString();

        try {
            for (Socket targetSocket : Server.OnlineServer.keySet()) {
                if (Server.OnlineServer.get(targetSocket).equals(userName)) {
                    DataOutputStream dos = new DataOutputStream(targetSocket.getOutputStream());
                    dos.writeInt(3);
                    dos.writeUTF(msg);
                    dos.flush();
                    break; // 找到目标用户后立即退出循环
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
