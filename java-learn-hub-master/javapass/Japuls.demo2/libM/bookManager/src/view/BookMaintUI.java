package view;

import Dip.BookDip;
import Dip.BookTypeDip;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
public class BookMaintUI extends JFrame {
    private JComboBox<String> conditionComboBox;
    private JTextField searchField;
    private JButton searchButton;
    private JButton resetButton; // 新增：重置按钮
    private static JTable resultTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private JProgressBar progressBar;
    private JButton modifyButton; // 修改按钮

    public BookMaintUI() {
        setTitle("书籍查询系统");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 设置全局字体
        setUIFont(new Font("微软雅黑", Font.PLAIN, 12));

        // 创建顶部面板（包含搜索组件 + 重置按钮）
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
        add(buttonPanel, BorderLayout.EAST);

        // 初始加载所有书籍
        loadAllBooks();

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

    // 创建顶部面板（含搜索、查询、重置按钮）
    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);

        // 标题
        JLabel titleLabel = new JLabel("书籍查询系统");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        titleLabel.setForeground(new Color(50, 150, 250));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // 搜索面板（查询条件 + 输入框 + 查询按钮 + 重置按钮）
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(Color.WHITE);

        JLabel conditionLabel = new JLabel("查询条件:");
        conditionLabel.setForeground(new Color(50, 50, 50));
        searchPanel.add(conditionLabel);

        String[] conditions = {"id", "bookName", "author", "sex", "price", "bookDesc", "bookTypeId"};
        conditionComboBox = new JComboBox<>(conditions);
        conditionComboBox.setPreferredSize(new Dimension(100, 30));
        conditionComboBox.setSelectedIndex(1);
        searchPanel.add(conditionComboBox);

        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(250, 30));
        searchField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        searchPanel.add(searchField);

        searchButton = new JButton("查询");
        searchButton.setPreferredSize(new Dimension(90, 30));
        searchButton.setBackground(new Color(50, 150, 250));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
        searchButton.setFocusPainted(false);
        searchButton.addActionListener(new SearchButtonListener());
        searchPanel.add(searchButton);

        // 新增：重置按钮
        resetButton = new JButton("重置");
        resetButton.setPreferredSize(new Dimension(90, 30));
        resetButton.setBackground(new Color(200, 200, 200)); // 灰色背景
        resetButton.setForeground(Color.BLACK);
        resetButton.setFont(new Font("微软雅黑", Font.BOLD, 12));
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(e -> {
            searchField.setText(""); // 清空搜索框
            loadAllBooks(); // 重新加载所有数据
        });
        searchPanel.add(resetButton);

        panel.add(titleLabel);
        panel.add(searchPanel);

        return panel;
    }

    // 创建表格组件
    private void createTableComponents() {
        String[] columnNames = {"ID", "书名", "作者", "性别", "价格", "描述", "类型ID"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 表格不可编辑
            }
        };

        resultTable = new JTable(tableModel);
        resultTable.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        resultTable.setRowHeight(30);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultTable.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 12));
        resultTable.getTableHeader().setBackground(new Color(240, 240, 240));

        // 设置列宽
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        resultTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        resultTable.getColumnModel().getColumn(3).setPreferredWidth(60);
        resultTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        resultTable.getColumnModel().getColumn(5).setPreferredWidth(250);
        resultTable.getColumnModel().getColumn(6).setPreferredWidth(70);

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

    // 加载所有书籍
    private void loadAllBooks() {
        statusLabel.setText("正在加载数据...");
        progressBar.setVisible(true);
        progressBar.setIndeterminate(true);

        new Thread(() -> {
            try {
                ResultSet rs = BookDip.query();
                List<Object[]> data = new ArrayList<>();

                while (rs.next()) {
                    Object[] row = {
                            rs.getInt("id"),
                            rs.getString("bookName"),
                            rs.getString("author"),
                            rs.getString("sex"),
                            rs.getFloat("price"),
                            rs.getString("bookDesc"),
                            rs.getInt("bookTypeId")
                    };
                    data.add(row);
                }

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
                            BookMaintUI.this,
                            "查询出错: " + e.getMessage(),
                            "错误",
                            JOptionPane.ERROR_MESSAGE
                    );
                });
            }
        }).start();
    }

    // 根据条件搜索书籍
    private void searchBooks(String condition, String keyword) {
        statusLabel.setText("正在搜索...");
        progressBar.setVisible(true);
        progressBar.setIndeterminate(true);

        new Thread(() -> {
            try {
                ResultSet rs = BookDip.query(condition, keyword);
                List<Object[]> data = new ArrayList<>();

                while (rs.next()) {
                    Object[] row = {
                            rs.getInt("id"),
                            rs.getString("bookName"),
                            rs.getString("author"),
                            rs.getString("sex"),
                            rs.getFloat("price"),
                            rs.getString("bookDesc"),
                            rs.getInt("bookTypeId")
                    };
                    data.add(row);
                }

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
                            BookMaintUI.this,
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
        tableModel.setRowCount(0);
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
            searchBooks(condition, keyword);
        }
    }


    // 修改按钮监听器
    // 修改按钮监听器
    public class ModifyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = resultTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(BookMaintUI.this, "请选择要修改的记录", "提示", JOptionPane.WARNING_MESSAGE);
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
        modifyButton.addActionListener(new ModifyButtonListener());

        // 【保存逻辑】
        int selectedRow = resultTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String bookName = (String) tableModel.getValueAt(selectedRow, 1);
            String author = (String) tableModel.getValueAt(selectedRow, 2);
            String sex = (String) tableModel.getValueAt(selectedRow, 3);
            float price = (float) tableModel.getValueAt(selectedRow, 4);
            String bookDesc = (String) tableModel.getValueAt(selectedRow, 5);
            int bookTypeId = (int) tableModel.getValueAt(selectedRow, 6);

            // 调用保存方法
            // BookDip.updateBook(id, bookName, author, sex, price, bookDesc, bookTypeId);
            new Thread(() -> {
                boolean success = BookDip.reset(id, bookName, author, sex, price, bookDesc, bookTypeId);
                if (success) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(BookMaintUI.this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        loadAllBooks();
                    });
                } else {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(BookMaintUI.this, "修改失败", "错误", JOptionPane.ERROR_MESSAGE);
                    });
                }
            }).start();
        }
    }

}
