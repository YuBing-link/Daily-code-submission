package view;

import link_mysql.link;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton; // 新增重置按钮

    public LoginUI() {
        setTitle("图书管理系统 - 登录");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置界面居中显示
        setLocationRelativeTo(null);

        // 设置全局字体
        Font font = new Font("微软雅黑", Font.PLAIN, 14);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("PasswordField.font", font);
        UIManager.put("Button.font", font);

        // 使用 BorderLayout 布局
        setLayout(new BorderLayout());

        // 创建一个面板用于放置输入组件
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("用户名:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("密码:");
        passwordField = new JPasswordField();

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        // 创建一个面板用于放置登录按钮和重置按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        loginButton = new JButton("登录");
        loginButton.setBackground(new Color(0, 128, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        resetButton = new JButton("重置"); // 新增重置按钮
        resetButton.setBackground(new Color(255, 0, 0)); // 红色背景
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);

        buttonPanel.add(loginButton);
        buttonPanel.add(resetButton); // 添加重置按钮到面板

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    if (authenticateUser(username, password)) {
                        JOptionPane.showMessageDialog(LoginUI.this, "登录成功");
                        new LibraryManagementSystemUI().setVisible(true);
                        LoginUI.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(LoginUI.this, "用户名或密码错误");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginUI.this, "数据库连接错误，请稍后重试");
                }
            }
        });

        // 重置按钮点击事件
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        setVisible(true);
    }

    private boolean authenticateUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM t_user WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = link.getcon().prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginUI();
            }
        });
    }
}