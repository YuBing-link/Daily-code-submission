package SysteamUI;

import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UIPrivateFrame extends JFrame {
    /**
     * 界面组件与网络连接定义
     * messageArea - 文本消息显示区域
     * inputField - 用户输入文本框
     * sendButton - 消息发送按钮
     * socket - 客户端通信套接字
     */
    private JTextArea messageArea;
    private JTextField inputField;
    private JButton sendButton;
    private Socket socket;
    private String selectedUser;

    public UIPrivateFrame(Socket socket, String selectedUser) {
        this.socket = socket;
        this.selectedUser = selectedUser; // 存储目标用户名
        initializeUI();
        new ClientThread(this, socket).start();
    }

    private void initializeUI() {
        setTitle("私聊窗口");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 设置简约主题色
        Color bgColor = new Color(245, 245, 245);
        Color accentColor = new Color(0, 123, 255);
        
        // 消息显示区域
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setBackground(Color.WHITE);
        messageArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(scrollPane, BorderLayout.CENTER);

        // 底部输入区域
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        bottomPanel.setBackground(bgColor);
        
        // 输入框
        inputField = new JTextField();
        inputField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentColor, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        bottomPanel.add(inputField, BorderLayout.CENTER);
        
        // 发送按钮
        sendButton = new JButton("发送");
        sendButton.setBackground(accentColor);
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
        sendButton.setFocusPainted(false);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        sendButton.addActionListener(e ->{
           SendPrivatemsg(selectedUser,inputField.getText());
        });



        add(bottomPanel, BorderLayout.SOUTH);
        
        // 设置窗口居中显示
        setLocationRelativeTo(null);
        
        // 添加快捷键支持
        getRootPane().setDefaultButton(sendButton);



    }

    private void SendPrivatemsg(String username, String inputFieldText) {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(3);
            dos.writeUTF(username);
            dos.writeUTF(inputFieldText);
            dos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateChatmsg(String s) {
        messageArea.append(s + "\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }
}
