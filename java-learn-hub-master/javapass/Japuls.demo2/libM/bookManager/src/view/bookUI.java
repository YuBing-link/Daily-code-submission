package view;

import Dip.BookDip;
import link_mysql.link;
import moudle.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bookUI extends JFrame {
    private JButton saveButton;

    private JTextField bookNameField;
    private JTextField authorField;
    private JTextField sexField;
    private JTextField priceField;
    private JTextArea bookDescArea;
    private JTextField bookTypeIdField;



    public bookUI() {
        setTitle("添加书籍");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 修改为关闭窗口而非退出程序
        setLocationRelativeTo(null);

        // 设置全局字体
        Font font = new Font("微软雅黑", Font.PLAIN, 14);
        Font titleFont = new Font("微软雅黑", Font.BOLD, 18);

        // 使用 BorderLayout 布局
        setLayout(new BorderLayout());

        // 标题面板
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(240, 240, 240));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        JLabel titleLabel = new JLabel("添加书籍信息");
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // 创建一个面板用于放置输入组件
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // 初始化输入组件

        bookNameField = new JTextField();
        authorField = new JTextField();
        sexField = new JTextField();
        priceField = new JTextField();
        bookDescArea = new JTextArea(4, 20);
        bookDescArea.setLineWrap(true);
        bookDescArea.setWrapStyleWord(true);
        bookTypeIdField = new JTextField();

        // 添加滚动面板用于书籍简介
        JScrollPane descScrollPane = new JScrollPane(bookDescArea);

        // 添加标签和输入组件

        addLabelAndComponent(inputPanel, "书名:", bookNameField, gbc, 1);
        addLabelAndComponent(inputPanel, "作者:", authorField, gbc, 2);
        addLabelAndComponent(inputPanel, "性别:", sexField, gbc, 3);
        addLabelAndComponent(inputPanel, "价格:", priceField, gbc, 4);
        addLabelAndComponent(inputPanel, "书籍简介:", descScrollPane, gbc, 5);
        addLabelAndComponent(inputPanel, "书籍类型ID:", bookTypeIdField, gbc, 6);

        // 添加输入面板到中央
        add(inputPanel, BorderLayout.CENTER);

        // 创建一个面板用于放置保存按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));
        saveButton = new JButton("保存");
        saveButton.setBackground(new Color(0, 128, 255));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(font);
        saveButton.setFocusPainted(false);
        saveButton.setPreferredSize(new Dimension(120, 40));

        // 添加按钮点击事件
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBookData();
            }
        });

        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // 辅助方法：添加标签和组件到面板
    private void addLabelAndComponent(JPanel panel, String labelText, JComponent component,
                                      GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(component, gbc);
    }

    // 保存书籍数据的方法
    private void saveBookData() {

        String bookName = bookNameField.getText().trim();
        String author = authorField.getText().trim();
        String sex = sexField.getText().trim();
        String price = (priceField.getText().trim());
        String bookDesc = bookDescArea.getText().trim();
        String bookTypeId = bookTypeIdField.getText().trim();

        // 简单验证
        if (bookName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "书名不能为空", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {


            if (!price.isEmpty()) {
                Double.parseDouble(price); // 验证价格是否为数字
            }

            if (!bookTypeId.isEmpty()) {
                Integer.parseInt(bookTypeId); // 验证类型ID是否为数字
            }

            // TODO: 这里添加实际的数据库保存逻辑

            Book b=new Book(bookName,author,sex,Float.parseFloat(price),bookDesc,Integer.valueOf(bookTypeId));
            BookDip.add(link.getcon(),b);
            JOptionPane.showMessageDialog(this, "书籍信息保存成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // 关闭窗口

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入有效的数字格式", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "保存失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}