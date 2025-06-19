package view;

import Dip.BookDip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMaintUI extends JFrame {
    private JComboBox<String> conditionComboBox;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;

    public BookMaintUI() {
        setTitle("书籍查询系统");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 顶部搜索面板
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        // 查询条件下拉框
        String[] conditions = {"id","bookName","author","sex","price","bookDesc","bookTypeId"};
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
                            BookMaintUI.this,
                            "请输入查询关键词",
                            "提示",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                // TODO: 根据 selectedCondition 和 keyword 执行数据库查询
                // 示例：获取数据库连接并执行查询
                // try (Connection conn = DatabaseUtil.getConnection();
                //      PreparedStatement pstmt = createQueryStatement(conn, selectedCondition, keyword);
                //      ResultSet rs = pstmt.executeQuery()) {
                //     // 处理结果集并显示到 resultArea
                // } catch (SQLException ex) {
                //     ex.printStackTrace();
                //     resultArea.setText("查询出错: " + ex.getMessage());
                // }
                ResultSet rs= BookDip.query(selectedCondition,keyword);
                StringBuilder resultText = new StringBuilder();
                try {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String bookName = rs.getString("bookName");
                        String author = rs.getString("author");
                        String sex = rs.getString("sex");
                        float price = rs.getFloat("price");
                        String bookDesc = rs.getString("bookDesc");
                        int bookTypeId = rs.getInt("bookTypeId");
                        resultText.append("ID:").append(id).append('\n');
                        resultText.append("书名:").append(bookName).append('\n');
                        resultText.append("作者:").append(sex).append('\n');
                        resultText.append("性别:").append(price).append('\n');
                        resultText.append("价格:").append(bookDesc).append('\n');
                        resultText.append("类型ID:").append(bookTypeId).append('\n');
                        resultText.append("--------------------------------\n");
                    }

                    // 更新结果区域
                    resultArea.setText(resultText.toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }


}