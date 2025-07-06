package view;

import Dip.BookDip;
import Dip.BookTypeDip;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookTypeQueryUI extends JFrame {
    private int TableClickId;   // 编辑模式下选中的行索引
    private JComboBox<String> conditionComboBox;
    private JTextField searchField;
    private JButton searchButton;
    private JButton resetButton; // 新增重置按钮
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private JProgressBar progressBar;
    private JButton modifyButton; // 添加修改按钮

    public BookTypeQueryUI() {
        setTitle("书籍类型查询系统");
        setSize(800, 600);
        // 修改关闭操作
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 设置全局字体
        setUIFont(new Font("微软雅黑", Font.PLAIN, 12));

        // 创建顶部面板（包含搜索组件）
        JPanel topPanel = createTopPanel();

        // 创建表格模型和表格
        createTableComponents();

        // 创建状态栏
        JPanel statusPanel = createStatusPanel();

        // 添加修改按钮
        modifyButton = new JButton("修改");
        modifyButton.setPreferredSize(new Dimension(90, 30));
        modifyButton.setBackground(new Color(50, 150, 250));
        modifyButton.setForeground(Color.WHITE);
        modifyButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
        modifyButton.setFocusPainted(false);
        modifyButton.addActionListener(new ModifyButtonListener());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(modifyButton);

        // 添加组件到主窗口
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultTable), BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.EAST); // 将修改按钮面板添加到窗口东部

        // 初始加载所有书籍类型
        loadAllBookTypes();

        setVisible(true);
    }

    // 设置全局字体
    private void setUIFont(Font font) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof Font) {
                UIManager.put(key, font);
            }
        }
    }

    // 创建顶部面板
    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // 增加四周空白
        panel.setBackground(Color.WHITE);

        // 创建标题标签（居中显示）
        JLabel titleLabel = new JLabel("书籍类型查询系统");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        titleLabel.setForeground(new Color(30, 100, 200));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 组件水平居中
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // 文本居中
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 20, 0)); // 增加上下空白

        // 创建搜索面板
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(Color.WHITE);

        // 查询条件标签
        JLabel conditionLabel = new JLabel("查询条件:");
        conditionLabel.setForeground(new Color(50, 50, 50));
        searchPanel.add(conditionLabel);

        // 查询条件下拉框
        String[] conditions = {"bookTypeName", "bookTypeDesc"};
        conditionComboBox = new JComboBox<>(conditions);
        conditionComboBox.setPreferredSize(new Dimension(120, 30));
        conditionComboBox.setSelectedIndex(0); // 默认选择"bookTypeName"
        searchPanel.add(conditionComboBox);

        // 搜索输入框
        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(250, 30));
        searchField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        searchPanel.add(searchField);

        // 查询按钮
        searchButton = new JButton("查询");
        searchButton.setPreferredSize(new Dimension(90, 30));
        searchButton.setBackground(new Color(50, 150, 250));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(new SearchButtonListener());
        searchPanel.add(searchButton);

        // 重置按钮
        resetButton = new JButton("重置");
        resetButton.setPreferredSize(new Dimension(90, 30));
        resetButton.setBackground(new Color(255, 0, 0));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setText("");
                loadAllBookTypes();
            }
        });
        searchPanel.add(resetButton);

        // 添加到主面板（先添加标题，再添加搜索面板）
        panel.add(titleLabel);
        panel.add(searchPanel);

        return panel;
    }

    // 创建表格组件
    private void createTableComponents() {
        // 修改列名，加入id列
        String[] columnNames = {"ID", "类型名称", "类型描述"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 表格不可编辑
            }
        };

        // 创建表格
        resultTable = new JTable(tableModel);
        resultTable.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        resultTable.setRowHeight(30);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultTable.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 12));
        resultTable.getTableHeader().setBackground(new Color(240, 240, 240));

        // 设置列宽，调整id列宽度
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        resultTable.getColumnModel().getColumn(2).setPreferredWidth(400);

        // 设置表格样式
        resultTable.setGridColor(new Color(230, 230, 230));
        resultTable.setFillsViewportHeight(true);
    }

    // 创建状态栏
    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.setBackground(new Color(245, 245, 245));

        statusLabel = new JLabel("就绪");
        statusLabel.setFont(new Font("微软雅黑", Font.PLAIN, 11));
        statusLabel.setForeground(new Color(100, 100, 100));

        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(150, 20));
        progressBar.setVisible(false);

        panel.add(statusLabel, BorderLayout.WEST);
        panel.add(progressBar, BorderLayout.EAST);

        return panel;
    }

    // 加载所有书籍类型
    private void loadAllBookTypes() {
        statusLabel.setText("正在加载数据...");
        progressBar.setVisible(true);
        progressBar.setIndeterminate(true);

        // 在新线程中执行数据库操作
        new Thread(() -> {
            try {
                ResultSet rs = BookTypeDip.query();
                List<Object[]> data = new ArrayList<>();

                while (rs.next()) {
                    // 加入id数据
                    Object[] row = {
                            rs.getInt("id"),
                            rs.getString("bookTypeName"),
                            rs.getString("bookTypeDesc")
                    };
                    data.add(row);
                }

                // 在事件调度线程中更新UI
                SwingUtilities.invokeLater(() -> {
                    updateTable(data);
                    statusLabel.setText("数据加载完成，共 " + data.size() + " 条记录");
                    progressBar.setIndeterminate(false);
                    progressBar.setVisible(false);
                });
            } catch (SQLException e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("加载数据失败");
                    progressBar.setIndeterminate(false);
                    progressBar.setVisible(false);
                    JOptionPane.showMessageDialog(
                            BookTypeQueryUI.this,
                            "查询出错: " + e.getMessage(),
                            "错误",
                            JOptionPane.ERROR_MESSAGE
                    );
                });
            }
        }).start();
    }

    // 根据条件搜索书籍类型
    private void searchBookTypes(String condition, String keyword) {
        statusLabel.setText("正在搜索...");
        progressBar.setVisible(true);
        progressBar.setIndeterminate(true);

        // 在新线程中执行数据库操作
        new Thread(() -> {
            try {
                ResultSet rs = BookTypeDip.query(condition, keyword);
                List<Object[]> data = new ArrayList<>();

                while (rs.next()) {
                    Object[] row = {
                            rs.getInt("id"),
                            rs.getString("bookTypeName"),
                            rs.getString("bookTypeDesc")
                    };
                    data.add(row);
                }

                // 在事件调度线程中更新UI
                SwingUtilities.invokeLater(() -> {
                    updateTable(data);
                    statusLabel.setText("搜索完成，共 " + data.size() + " 条记录");
                    progressBar.setIndeterminate(false);
                    progressBar.setVisible(false);
                });
            } catch (SQLException e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("搜索失败");
                    progressBar.setIndeterminate(false);
                    progressBar.setVisible(false);
                    JOptionPane.showMessageDialog(
                            BookTypeQueryUI.this,
                            "查询出错: " + e.getMessage(),
                            "错误",
                            JOptionPane.ERROR_MESSAGE
                    );
                });
            }
        }).start();
    }

    // 更新表格数据
    private void updateTable(List<Object[]> data) {
        // 清空表格
        tableModel.setRowCount(0);

        // 添加数据
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    // 搜索按钮监听器
    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String condition = (String) conditionComboBox.getSelectedItem();
            String keyword = searchField.getText();
            searchBookTypes(condition, keyword);
        }
    }

    // 修改按钮监听器
    private class ModifyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = resultTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(BookTypeQueryUI.this, "请选择要修改的记录", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            enableEditMode(selectedRow);
        }
    }

    // 启用表格编辑模式
    private void enableEditMode(int selectedRow) {
        // 获取当前数据
        List<Object[]> data = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Object[] rowData = new Object[tableModel.getColumnCount()];
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                rowData[j] = tableModel.getValueAt(i, j);
            }
            data.add(rowData);
        }

        // 获取列名
        String[] columnNames = new String[tableModel.getColumnCount()];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = tableModel.getColumnName(i);
        }

        // 创建可编辑的表格模型
        tableModel = new DefaultTableModel(data.toArray(new Object[0][0]), columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return row == selectedRow && column != 0;
            }
        };

        // 设置自定义单元格渲染器
        resultTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component cell = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column
                );

                // 成员变量Clickid设置为编辑模式下的选中表格行索引
                TableClickId = selectedRow;

                // 编辑模式下，ID列标红
                if (row == selectedRow && column == 0) {
                    cell.setBackground(new Color(255, 200, 200));
                    cell.setForeground(Color.RED);
                } else if (row == selectedRow) {
                    cell.setBackground(new Color(255, 230, 230));
                } else {
                    cell.setBackground(table.getBackground());
                    cell.setForeground(table.getForeground());
                }
                return cell;
            }
        });

        resultTable.setModel(tableModel);

        // 移除旧监听器并添加新监听器
        modifyButton.removeActionListener(modifyButton.getActionListeners()[0]);
        modifyButton.setText("保存");
        modifyButton.addActionListener(e -> disableEditMode());

        resultTable.editCellAt(selectedRow, 1);
    }

    // 禁用表格编辑模式
    private void disableEditMode() {
        // 获取当前数据
        List<Object[]> data = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Object[] rowData = new Object[tableModel.getColumnCount()];
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                rowData[j] = tableModel.getValueAt(i, j);
            }
            data.add(rowData);
        }

        // 获取列名
        String[] columnNames = new String[tableModel.getColumnCount()];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = tableModel.getColumnName(i);
        }

        // 创建不可编辑的表格模型
        tableModel = new DefaultTableModel(data.toArray(new Object[0][0]), columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 恢复默认渲染器
        resultTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());

        resultTable.setModel(tableModel);

        // 移除旧监听器并添加新监听器
        modifyButton.removeActionListener(modifyButton.getActionListeners()[0]);
        modifyButton.setText("修改");
        modifyButton.addActionListener(new BookTypeQueryUI.ModifyButtonListener());
        System.out.println("斷點");


        // 【保存逻辑】
        int selectedRow = TableClickId;


        if (selectedRow != -1) {

            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String bookTypeName = (String) tableModel.getValueAt(selectedRow, 1);
            String bookTypeDesc = (String) tableModel.getValueAt(selectedRow, 2);



            // 调用保存方法

            new Thread(() -> {

                boolean success = BookTypeDip.reset(id, bookTypeName, bookTypeDesc);
                System.out.println(success);
                if (success) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(BookTypeQueryUI.this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);

                    });
                } else {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(BookTypeQueryUI.this, "修改失败", "错误", JOptionPane.ERROR_MESSAGE);
                    });
                }
            }).start();
        }
    }


}