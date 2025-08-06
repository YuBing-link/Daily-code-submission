<<<<<<< HEAD
package userUI;

import InformationUI.Uesr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LoadingUi extends JFrame implements ActionListener {

    private static ArrayList<Uesr> uesrs =new ArrayList<>();
    static {
        uesrs.add(new Uesr("yubing", "120756".toCharArray()));
        uesrs.add(new Uesr("ta", "54sy".toCharArray()));
    }

    // 密码输入框，用于用户输入密码
    private JPasswordField passField;

    // 面板，用于布局和包含其他UI组件
    private JPanel panel;

    // GridBag约束对象，用于管理组件在容器中的布局
    private GridBagConstraints gbc;

    // 显示用户名的标签，用于在界面上展示用户信息
    private JLabel usershow;

    // 用户名标签，用于提示用户输入用户名
    private JLabel userLabel;
    // 用户名输入框，用于用户输入用户名
    private JTextField userField;

    // 密码标签，用于提示用户输入密码
    private JLabel passLabel;

    // 登录按钮，用户点击以尝试登录
    private JButton loginButton;
    //注册按钮
    private JButton RegistrationButton;
    //注意
    private JLabel Notice;
    public LoadingUi() {
        // 设置窗口标题
        setTitle("登录界面");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建主面板并设置布局
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 设置组件之间的间距

        // 添加标题标签
        usershow = new JLabel("人事登录系统");
        usershow.setFont(new Font("楷体", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(usershow, gbc);

        // 用户名标签
        userLabel = new JLabel("用户名:");
        userLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // 重置 gridwidth
        panel.add(userLabel, gbc);

        // 用户名输入框
        userField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(userField, gbc);
        userField.addActionListener(this);
        // 密码标签
        passLabel = new JLabel("密码:");
        passLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);

        // 密码输入框
        passField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passField, gbc);
        passField.addActionListener(this);
        // 登录按钮
        loginButton = new JButton("登录");
        loginButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
        loginButton.setBackground(new Color(70, 130, 180)); // 设置背景颜色
        loginButton.setForeground(Color.WHITE); // 设置文字颜色
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // 按钮跨两列
        gbc.anchor = GridBagConstraints.WEST; // 左对齐
        panel.add(loginButton, gbc);
        loginButton.addActionListener(this);
        RegistrationButton = new JButton("注册");
        RegistrationButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
        RegistrationButton.setBackground(new Color(70, 130, 180)); // 设置背景颜色
        RegistrationButton.setForeground(Color.WHITE); // 设置文字颜色
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // 按钮跨两列
        gbc.anchor = GridBagConstraints.EAST; // 右对齐
        panel.add(RegistrationButton, gbc);
        RegistrationButton.addActionListener(this);
        // 添加事件监听器到注册按钮

        Notice = new JLabel("注意:若未注册，请点击注册按钮注册");
        Notice.setFont(new Font("楷体", Font.PLAIN, 10));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(Notice, gbc);
        // 将面板添加到窗口
        add(panel);


        // 显示窗口
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            login();
        }
        else if(e.getSource() == RegistrationButton){
            register();
        }
    }

    private void login() {
        boolean a=false;
        for (int i = 0; i < uesrs.size(); i++) {
            if(uesrs.get(i).getName().equals(userField.getText())){
                a = false;
                if(Arrays.equals(passField.getPassword(),uesrs.get(i).getPassword())){
                    new HumanUi(uesrs.get(i).getName());
                    this.dispose();
                    return;
                }else{
                    JOptionPane.showMessageDialog(this,"密码错误");
                    return;
                }
            }
            else {
                a = true;
            }

        }
        if(a){
            JOptionPane.showMessageDialog(this,"用户名不存在");
        }
    }
    private void register(){
        // 获取用户输入的用户名和密码
        String name = JOptionPane.showInputDialog(this, "请输入用户名（至少三位）：");
        String password = JOptionPane.showInputDialog(this, "请输入密码（至少六位）：");

        // 验证用户名和密码是否为空
        if (name == null || password == null || name.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 验证用户名和密码长度是否符合要求
        if (name.length() < 3 || password.length() < 6) {
            JOptionPane.showMessageDialog(this, "用户名至少三位，密码至少六位", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 将用户信息存储到系统中
        uesrs.add(new Uesr(name, password.toCharArray()));
        JOptionPane.showMessageDialog(this, "注册成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

    }
=======
package userUI;

import InformationUI.Uesr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LoadingUi extends JFrame implements ActionListener {

    private static ArrayList<Uesr> uesrs =new ArrayList<>();
    static {
        uesrs.add(new Uesr("yubing", "120756".toCharArray()));
        uesrs.add(new Uesr("ta", "54sy".toCharArray()));
    }

    // 密码输入框，用于用户输入密码
    private JPasswordField passField;

    // 面板，用于布局和包含其他UI组件
    private JPanel panel;

    // GridBag约束对象，用于管理组件在容器中的布局
    private GridBagConstraints gbc;

    // 显示用户名的标签，用于在界面上展示用户信息
    private JLabel usershow;

    // 用户名标签，用于提示用户输入用户名
    private JLabel userLabel;
    // 用户名输入框，用于用户输入用户名
    private JTextField userField;

    // 密码标签，用于提示用户输入密码
    private JLabel passLabel;

    // 登录按钮，用户点击以尝试登录
    private JButton loginButton;
    //注册按钮
    private JButton RegistrationButton;
    //注意
    private JLabel Notice;
    public LoadingUi() {
        // 设置窗口标题
        setTitle("登录界面");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建主面板并设置布局
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 设置组件之间的间距

        // 添加标题标签
        usershow = new JLabel("人事登录系统");
        usershow.setFont(new Font("楷体", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(usershow, gbc);

        // 用户名标签
        userLabel = new JLabel("用户名:");
        userLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // 重置 gridwidth
        panel.add(userLabel, gbc);

        // 用户名输入框
        userField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(userField, gbc);
        userField.addActionListener(this);
        // 密码标签
        passLabel = new JLabel("密码:");
        passLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);

        // 密码输入框
        passField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passField, gbc);
        passField.addActionListener(this);
        // 登录按钮
        loginButton = new JButton("登录");
        loginButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
        loginButton.setBackground(new Color(70, 130, 180)); // 设置背景颜色
        loginButton.setForeground(Color.WHITE); // 设置文字颜色
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // 按钮跨两列
        gbc.anchor = GridBagConstraints.WEST; // 左对齐
        panel.add(loginButton, gbc);
        loginButton.addActionListener(this);
        RegistrationButton = new JButton("注册");
        RegistrationButton.setFont(new Font("微软雅黑", Font.BOLD, 14));
        RegistrationButton.setBackground(new Color(70, 130, 180)); // 设置背景颜色
        RegistrationButton.setForeground(Color.WHITE); // 设置文字颜色
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // 按钮跨两列
        gbc.anchor = GridBagConstraints.EAST; // 右对齐
        panel.add(RegistrationButton, gbc);
        RegistrationButton.addActionListener(this);
        // 添加事件监听器到注册按钮

        Notice = new JLabel("注意:若未注册，请点击注册按钮注册");
        Notice.setFont(new Font("楷体", Font.PLAIN, 10));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(Notice, gbc);
        // 将面板添加到窗口
        add(panel);


        // 显示窗口
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            login();
        }
        else if(e.getSource() == RegistrationButton){
            register();
        }
    }

    private void login() {
        boolean a=false;
        for (int i = 0; i < uesrs.size(); i++) {
            if(uesrs.get(i).getName().equals(userField.getText())){
                a = false;
                if(Arrays.equals(passField.getPassword(),uesrs.get(i).getPassword())){
                    new HumanUi(uesrs.get(i).getName());
                    this.dispose();
                    return;
                }else{
                    JOptionPane.showMessageDialog(this,"密码错误");
                    return;
                }
            }
            else {
                a = true;
            }

        }
        if(a){
            JOptionPane.showMessageDialog(this,"用户名不存在");
        }
    }
    private void register(){
        // 获取用户输入的用户名和密码
        String name = JOptionPane.showInputDialog(this, "请输入用户名（至少三位）：");
        String password = JOptionPane.showInputDialog(this, "请输入密码（至少六位）：");

        // 验证用户名和密码是否为空
        if (name == null || password == null || name.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 验证用户名和密码长度是否符合要求
        if (name.length() < 3 || password.length() < 6) {
            JOptionPane.showMessageDialog(this, "用户名至少三位，密码至少六位", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 将用户信息存储到系统中
        uesrs.add(new Uesr(name, password.toCharArray()));
        JOptionPane.showMessageDialog(this, "注册成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

    }
>>>>>>> bdeb85edff313e367535c724cc175d32e969374e
}