package SysteamUI;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private DataInputStream dis;
    private UIWorkFrame uiWorkFrame;
    public ClientThread(UIWorkFrame uiWorkFrame, Socket socket){
        this.socket = socket;
        this.uiWorkFrame = uiWorkFrame;
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
                    case 2->{
                        uiWorkFrame.updateChatmsg(dis.readUTF());
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
