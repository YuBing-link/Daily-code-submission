package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystemUI extends JFrame {

    public LibraryManagementSystemUI() {
        setTitle("图书管理系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 设置全局字体
        Font titleFont = new Font("微软雅黑", Font.BOLD, 22);

        // 创建菜单栏
        createMenuBar();

        // 添加标题面板
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(240, 240, 240));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        JLabel titleLabel = new JLabel("图书管理系统");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(50, 50, 50));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // 添加欢迎信息面板
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        JLabel welcomeLabel = new JLabel("欢迎使用图书管理系统");
        welcomeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createVerticalGlue());

        add(welcomePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // 创建菜单栏
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        Font menuFont = new Font("微软雅黑", Font.PLAIN, 14);

        // 图书管理菜单
        JMenu bookMenu = new JMenu("图书管理");
        bookMenu.setFont(menuFont);

        JMenuItem addBookItem = new JMenuItem("图书添加");
        addBookItem.setFont(menuFont);
        addBookItem.addActionListener(e -> new bookUI());

        JMenuItem maintainBookItem = new JMenuItem("图书维护");
        maintainBookItem.setFont(menuFont);
        // TODO: 添加图书维护功能
        maintainBookItem.addActionListener(e -> new BookMaintUI());
        bookMenu.add(addBookItem);
        bookMenu.add(maintainBookItem);

        // 图书类别菜单
        JMenu categoryMenu = new JMenu("图书类别");
        categoryMenu.setFont(menuFont);

        JMenuItem addCategoryItem = new JMenuItem("图书类别添加");
        addCategoryItem.setFont(menuFont);
        addCategoryItem.addActionListener(e -> new BookTypeUI());

        JMenuItem maintainCategoryItem = new JMenuItem("图书类别维护");
        maintainCategoryItem.setFont(menuFont);
        // TODO: 添加图书类别维护功能
        maintainCategoryItem.addActionListener(e -> new BookTypeQueryUI());
        categoryMenu.add(addCategoryItem);
        categoryMenu.add(maintainCategoryItem);

        // 系统菜单
        JMenu systemMenu = new JMenu("系统");
        systemMenu.setFont(menuFont);

        JMenuItem manageAdminItem = new JMenuItem("管理人员");
        manageAdminItem.setFont(menuFont);
        manageAdminItem.addActionListener(e -> new userUI());

        JMenuItem aboutItem = new JMenuItem("关于我们");
        aboutItem.setFont(menuFont);
        // TODO: 添加关于我们功能

        JMenuItem exitItem = new JMenuItem("安全退出");
        exitItem.setFont(menuFont);
        exitItem.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "确定要退出系统吗？", "确认退出", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        systemMenu.add(manageAdminItem);
        systemMenu.add(aboutItem);
        systemMenu.addSeparator();
        systemMenu.add(exitItem);

        // 将菜单添加到菜单栏
        menuBar.add(bookMenu);
        menuBar.add(categoryMenu);
        menuBar.add(systemMenu);

        // 设置菜单栏
        setJMenuBar(menuBar);
    }


}