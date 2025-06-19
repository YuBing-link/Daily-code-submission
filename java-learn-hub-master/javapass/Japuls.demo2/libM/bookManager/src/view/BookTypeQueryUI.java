package view;

import Dip.BookTypeDip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookTypeQueryUI extends JFrame {
    private JComboBox<String> conditionComboBox;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    ResultSet rs=null;
    public BookTypeQueryUI() {
        setTitle("书籍类型查询系统");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 顶部搜索面板
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        // 查询条件下拉框
        String[] conditions = {"bookTypeName","bookTypeDesc"};
        conditionComboBox = new JComboBox<>(conditions);
        conditionComboBox.setPreferredSize(new Dimension(100, 30));

        // 搜索输入框
        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(200, 30));

        // 查询按钮
        searchButton = new JButton("查询");
        searchButton.setPreferredSize(new Dimension(80, 30));
        searchButton.setBackground(new Color(50, 150, 250));
        searchButton.setForeground(Color.WHITE);

        // 添加组件到搜索面板
        searchPanel.add(conditionComboBox);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // 结果显示区域
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);


        // 查询按钮事件

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCondition = (String) conditionComboBox.getSelectedItem();
                String keyword = searchField.getText().trim();

                if (keyword.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            BookTypeQueryUI.this,
                            "请输入查询关键词",
                            "提示",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                // 清空结果区域
                resultArea.setText("正在查询...\n");

                // 执行数据库查询

                try {
                    rs = BookTypeDip.query(selectedCondition, keyword);

                    // 检查是否有结果
                    if (!rs.isBeforeFirst()) {
                        resultArea.setText("未找到匹配的书籍类型记录。");
                        return;
                    }

                    // 设置表头
                    StringBuilder resultText = new StringBuilder();
                    resultText.append("书籍类型查询结果:\n");
                    resultText.append("--------------------------------\n");

                    // 遍历结果集并追加到文本区域
                    while (rs.next()) {
                        String bookTypeName = rs.getString("bookTypeName");
                        String bookTypeDesc = rs.getString("bookTypeDesc");

                        resultText.append("类型名称: ").append(bookTypeName).append("\n");
                        resultText.append("类型描述: ").append(bookTypeDesc).append("\n");
                        resultText.append("--------------------------------\n");
                    }

                    // 更新结果区域
                    resultArea.setText(resultText.toString());

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    resultArea.setText("查询出错: " + ex.getMessage());
                } finally {
                    // 确保资源被关闭
                    if (rs != null) {
                        // 如果 BookTypeDip.query() 返回的 ResultSet 关联的 Statement 可关闭
                        // 则需要在这里关闭 rs 或调用 BookTypeDip.close() 方法
                        // rs.close();
                    }
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }


}