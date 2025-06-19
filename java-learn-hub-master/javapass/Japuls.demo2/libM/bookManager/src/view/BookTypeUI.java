package view;

import Dip.BookTypeDip;
import link_mysql.link;
import moudle.Booktype;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookTypeUI extends JFrame {
    private JButton saveButton;
    private JTextField typeNameField;
    private JTextArea typeDescArea;
    Font font;

    public BookTypeUI() {
        setTitle("添加书籍类型");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        JLabel titleLabel = new JLabel("添加书籍类型");
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // 创建一个面板用于放置输入组件
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // 初始化输入组件
        typeNameField = new JTextField();
        typeDescArea = new JTextArea(4, 20);
        typeDescArea.setLineWrap(true);
        typeDescArea.setWrapStyleWord(true);

        // 添加滚动面板用于多行文本
        JScrollPane descScrollPane = new JScrollPane(typeDescArea);

        // 添加标签和输入组件
        addLabelAndComponent(inputPanel, "书籍类型名:", typeNameField, gbc, 0);
        addLabelAndComponent(inputPanel, "书籍类型说明:", descScrollPane, gbc, 1);

        // 添加输入面板到中央
        add(inputPanel, BorderLayout.CENTER);

        // 创建一个面板用于放置保存按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
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
                saveBookTypeData();
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
        label.setFont(font);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(component, gbc);
    }

    // 保存书籍类型数据的方法
    private void saveBookTypeData() {
        String typeName = typeNameField.getText().trim();
        String typeDesc = typeDescArea.getText().trim();

        // 简单验证
        if (typeName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "书籍类型名不能为空", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // TODO: 这里添加实际的数据库保存逻辑
            Booktype bt=new Booktype(typeName,typeName);
            BookTypeDip.add(link.getcon(),bt);
            JOptionPane.showMessageDialog(this, "书籍类型保存成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // 关闭窗口

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "保存失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}