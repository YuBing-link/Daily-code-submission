package SysteamUI;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private DataInputStream dis;
    private UIWorkFrame uiWorkFrame;
    private UIPrivateFrame uiPrivateFrame;
    public ClientThread(UIWorkFrame uiWorkFrame, Socket socket){
        this.socket = socket;
        this.uiWorkFrame = uiWorkFrame;
    }
    public ClientThread(UIPrivateFrame uiPrivateFrame, Socket socket){
        this.socket = socket;
        this.uiPrivateFrame=uiPrivateFrame;
    }



    @Override
    public void run() {
        try {
            dis = new DataInputStream(socket.getInputStream());
            while (true) {
                switch (dis.readInt()){
                    case 1->{
                        updateClientUserList();
                    }
                    case 2-> {
                        uiWorkFrame.updateChatmsg(dis.readUTF());
                    }
                    case 3->{
                        if(uiPrivateFrame != null) {
                            uiPrivateFrame.updateChatmsg(dis.readUTF());
                        } else {
                            // 如果没有打开私聊窗口，可以选择显示通知或忽略
                            System.out.println("收到私聊消息但窗口未打开");
                        }
                    }
                }
            }

        } catch (IOException e) {
           e.printStackTrace();
        }


    }




    private void updateClientUserList() {
        try {
            int count = dis.readInt();
            String[] names = new String[count];
            for (int i = 0; i <count ; i++) {
                names[i]=dis.readUTF();
            }
            uiWorkFrame.updateuser(names);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
