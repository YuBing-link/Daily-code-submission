package SysteamUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UIStartFrame extends JFrame implements ActionListener {
    private JTextField nicknameField;
    private JButton loginButton, logoutButton;
    private static final Color PRIMARY_COLOR = new Color(0x4A90E2);
    private static final Color HOVER_COLOR = new Color(0x357ABD);
    private static final Color TEXTFIELD_FOCUS_COLOR = new Color(0x4A90E2);
    private Socket socket;
    public UIStartFrame() {
      InitFrame();
    }

    public void InitFrame(){
        setTitle("登录界面");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 创建顶部面板
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("欢迎使用系统", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        titleLabel.setForeground(PRIMARY_COLOR);
        topPanel.add(titleLabel);

        // 创建中间面板
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // 昵称输入行
        JPanel nickPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        nickPanel.setBackground(Color.WHITE);
        JLabel nickLabel = new JLabel("昵称");
        nickLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        nicknameField = new JTextField(15);
        nicknameField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        nickPanel.add(nickLabel);
        nickPanel.add(nicknameField);

        // 按钮行
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        loginButton = createStyledButton("登录");
        logoutButton = createStyledButton("登出");
        
        // 添加鼠标监听器
        addMouseListeners(loginButton);
        addMouseListeners(logoutButton);
        
        buttonPanel.add(loginButton);
        buttonPanel.add(logoutButton);

        // 添加组件
        centerPanel.add(nickPanel);
        centerPanel.add(buttonPanel);
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        // 设置窗口居中
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 创建样式按钮
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.addActionListener(this);
        return button;
    }

    // 添加鼠标监听器方法
    private void addMouseListeners(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(HOVER_COLOR);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
                setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(TEXTFIELD_FOCUS_COLOR);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(HOVER_COLOR);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 判断触发事件的来源
        if (e.getSource() == loginButton) {
            // 登录按钮点击处理
            // 这里可以添加登录验证逻辑
            // 用户自定义的反应内容可以从这里开始写

            if(!nicknameField.getText().isEmpty()) {

                login();
                new UIWorkFrame(socket);
                dispose();
            }else {
                JOptionPane.showConfirmDialog(this, "请输入昵称");
            }
            // TODO: 用户可在此添加自定义逻辑
        } else if (e.getSource() == logoutButton) {
            // 登出按钮点击处理
            // 这里可以添加登出清理逻辑
            // 用户自定义的反应内容可以从这里开始写
            dispose();
            // TODO: 用户可在此添加自定义逻辑
        }
    }

    private void login() {
        try {
            socket=new Socket(Constant.Server_IP,Constant.Server_PROT);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(1);
            dos.writeUTF(nicknameField.getText());
            dos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // 测试方法

}