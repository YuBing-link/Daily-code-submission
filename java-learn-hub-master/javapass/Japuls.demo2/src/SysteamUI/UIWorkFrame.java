package SysteamUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*; // 包含MouseAdapter等事件类
import java.io.*; // 包含DataOutputStream等IO类
import java.net.*; // 包含Socket等网络类

public class UIWorkFrame extends JFrame  {
    // 聊天消息区域
    private JTextArea chatArea;
    // 输入区域
    private JTextArea inputArea;
    // 在线人员列表
    private JList<String> onlineUsersList;
    // 私聊按钮相关
    private JPopupMenu userContextMenu;
    private JMenuItem privateChatItem;
    // 发送按钮
    private JButton sendButton;
    // 主题颜色
    private static final Color PRIMARY_COLOR = new Color(0x2ECC71);
    private static final Color BG_COLOR = new Color(0xF5F5F5);
    private static final Color SCROLL_COLOR = new Color(0xE0E0E0);
    private Socket socket;
    public UIWorkFrame() {
        initializeUI();
    }

    public UIWorkFrame( Socket socket, String text) {


            this.socket = socket;
            setTitle(STR."\{text}群聊室");
            initializeUI(); // 确保UI初始化
            new ClientThread(this, socket).start();

    }

    private void initializeUI() {
        // 设置窗口基本属性

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 主面板使用BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 创建聊天消息区域
        createChatArea(mainPanel);
        // 创建在线人员区域
        createOnlineUsersPanel(mainPanel);
        // 创建输入区域
        createInputPanel(mainPanel);

        add(mainPanel);
        setVisible(true);
    }

    // 创建聊天消息显示区域
    private void createChatArea(JPanel mainPanel) {
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        chatArea.setBackground(Color.WHITE);

        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        chatScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatScrollPane.setBackground(Color.WHITE);

        // 自定义滚动条样式
        customizeScrollBar(chatScrollPane, SCROLL_COLOR);

        mainPanel.add(chatScrollPane, BorderLayout.CENTER);
    }

    // 创建在线人员列表区域
    private void createOnlineUsersPanel(JPanel mainPanel) {
        // 使用空数据模型初始化
        DefaultListModel<String> userListModel = new DefaultListModel<>();
        // 可以通过方法动态添加用户：userListModel.addElement("新用户");

        onlineUsersList = new JList<>(userListModel);
        onlineUsersList.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        onlineUsersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        onlineUsersList.setBackground(BG_COLOR);

        // 初始化右键菜单
        userContextMenu = new JPopupMenu();
        privateChatItem = new JMenuItem("私聊");
        userContextMenu.add(privateChatItem);

        // 添加右键点击事件
        onlineUsersList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int location = onlineUsersList.locationToIndex(e.getPoint());
                    if (location != -1) {
                        onlineUsersList.setSelectedIndex(location);

                        // 自定义私聊按钮样式
                        privateChatItem.setText("发起私聊"); // 自定义按钮文本
                        privateChatItem.setFont(new Font("微软雅黑", Font.BOLD, 14));
                        privateChatItem.setForeground(new Color(0x2ECC71));

                        // 添加点击事件处理
                        privateChatItem.addActionListener(e1 -> {
                            String selectedUser = onlineUsersList.getSelectedValue();
                            if (selectedUser != null) {
                                // 开始私聊会话并显示私聊窗口
                                startPrivateChat(selectedUser);
                                new UIPrivateFrame(socket, selectedUser).setVisible(true);
                            }
                        });

                        userContextMenu.show(onlineUsersList, e.getX(), e.getY());
                    }
                }
            }
        });

        JScrollPane usersScrollPane = new JScrollPane(onlineUsersList);
        usersScrollPane.setBorder(BorderFactory.createTitledBorder("在线人员"));
        usersScrollPane.setPreferredSize(new Dimension(180, 0)); // 右侧面板宽度
        usersScrollPane.setBackground(BG_COLOR);

        // 自定义滚动条样式
        customizeScrollBar(usersScrollPane, SCROLL_COLOR);

        mainPanel.add(usersScrollPane, BorderLayout.EAST);
    }

    // 创建输入区域
    private void createInputPanel(JPanel mainPanel) {
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBackground(BG_COLOR);

        // 输入区域
        inputArea = new JTextArea(4, 30);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        inputArea.setBackground(Color.WHITE);
        inputArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        inputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inputScrollPane.setBackground(BG_COLOR);

        // 自定义滚动条样式
        customizeScrollBar(inputScrollPane, SCROLL_COLOR);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.setBackground(BG_COLOR);

        sendButton = new JButton("发送");
        sendButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
        sendButton.setBackground(PRIMARY_COLOR);
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        buttonPanel.add(sendButton);
        sendButton.addActionListener(e->{
            SendServer(inputArea.getText());
            inputArea.setText("");
        });



        inputPanel.add(inputScrollPane, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);
    }

    private void SendServer(String text) {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(2);
            dos.writeUTF(text);
            dos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    // 自定义滚动条样式
    private void customizeScrollBar(JScrollPane scrollPane, Color color) {
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setBackground(color);
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                // 使用反射访问受保护字段
                try {
                    java.lang.reflect.Field field = BasicScrollBarUI.class.getDeclaredField("thumbColor");
                    field.setAccessible(true);
                    field.set(this, new Color(0xB0B0B0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void updateuser(String[] names) {
        onlineUsersList.setListData(names);
    }

    public void updateChatmsg(String s) {

        chatArea.append(s);
    }

    // 自定义私聊处理方法（需补充具体实现）
    private void startPrivateChat(String username) {
        // 这里可以添加私聊窗口创建逻辑或消息发送逻辑
        // 示例：发送私聊请求到服务器
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(3); // 自定义私聊协议类型
            dos.writeUTF("[私聊请求]" + username);
            dos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}