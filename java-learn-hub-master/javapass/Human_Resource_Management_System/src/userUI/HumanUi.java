package userUI;

import InformationUI.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class HumanUi extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JButton addButton;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private ArrayList<Employee> employees=new ArrayList<>();

    public HumanUi(String name) {
        // 设置窗口标题
        setTitle("员工信息管理系统"+name+"登录成功");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建主面板并设置布局
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 第一行：输入框、搜索按钮、添加按钮
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        searchField = new JTextField(20);
        searchButton = new JButton("搜索");
        addButton = new JButton("添加");

        // 为搜索按钮添加事件监听器
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().trim();
            if (!searchText.isEmpty()) {
                filterTable(searchText); // 调用筛选方法
            } else {
                JOptionPane.showMessageDialog(null, "请输入搜索内容", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // 为添加按钮添加事件监听器
        addButton.addActionListener(e -> {
            String[] columnNames = {"ID", "姓名", "性别", "电话", "职位", "薪水", "部门"};
            // 创建对应数量的文本框作为输入组件
            JTextField[] textFields = new JTextField[columnNames.length];
            for (int i = 0; i < textFields.length; i++) {
                textFields[i] = new JTextField();
            }

            // 创建面板来排列标签和文本框
            JPanel panel = new JPanel(new GridLayout(0, 1));
            for (int i = 0; i < columnNames.length; i++) {
                panel.add(new JLabel(columnNames[i] + ":"));
                panel.add(textFields[i]);
            }

            // 顯示对话框
            int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "添加新员工",
                JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {
                // 从文本框获取输入值
                Object[] inputs = new Object[columnNames.length];
                for (int i = 0; i < textFields.length; i++) {
                    inputs[i] = textFields[i].getText().trim();
                }

                // 验证输入是否为空
                boolean isValid = true;
                for (Object input : inputs) {
                    if (input == null || ((String) input).trim().isEmpty()) {
                        isValid = false;
                        break;
                    }
                }

                if (!isValid) {
                    JOptionPane.showMessageDialog(null, "所有字段都必须填写", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 新增ID唯一性验证
                String newId = inputs[0].toString().trim();
                for (Employee emp : employees) {
                    if (emp.getID().equals(newId)) {
                        JOptionPane.showMessageDialog(null, "该ID已存在，请输入唯一ID", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // 新增电话号码格式验证（11位数字）
                String phone = inputs[3].toString().trim();
                if (!phone.matches("^\\d{11}$")) {
                    JOptionPane.showMessageDialog(null, "电话号码必须为11位数字", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // 验证并处理薪水字段
                    double salary = Double.parseDouble(inputs[5].toString().trim());
                    if (salary <= 0) {
                        throw new NumberFormatException("薪水必须为正数");
                    }

                    // 将新员工信息添加到表格和列表
                    tableModel.addRow(inputs);

                    Employee newEmployee = new Employee(
                        inputs[0].toString(),
                        inputs[1].toString(),
                        inputs[2].toString(),
                        phone,
                        inputs[4].toString(),
                        salary,
                        inputs[6].toString()
                    );
                    employees.add(newEmployee);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "薪水必须为有效正数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(addButton);

        // 中间：表格
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 设置表格不可编辑
            }
        };
        tableModel.setColumnIdentifiers(new String[]{"ID", "姓名","性别","电话", "职位", "薪水","部门"});
        employeeTable = new JTable(tableModel);

        // 添加10个员工模板
        addEmployeeTemplates();

        // 为表格添加右键菜单
        addPopupMenuToTable();

        // 将表格放入滚动面板
        JScrollPane scrollPane = new JScrollPane(employeeTable);

        // 组装主面板
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // 将主面板添加到窗口
        add(mainPanel);

        // 显示窗口
        setVisible(true);

    }

    // 新增方法：添加10个员工模板
    private void addEmployeeTemplates() {
        String[][] templates = {
            {"001", "张三", "男", "13800138000", "经理", "10000", "技术部"},
            {"002", "李四", "女", "13800138001", "工程师", "8000", "技术部"},
            {"003", "王五", "男", "13800138002", "设计师", "7000", "设计部"},
            {"004", "赵六", "女", "13800138003", "产品经理", "9000", "产品部"},
            {"005", "孙七", "男", "13800138004", "测试工程师", "6000", "测试部"},
            {"006", "周八", "女", "13800138005", "HR", "5000", "人事部"},
            {"007", "吴九", "男", "13800138006", "财务主管", "12000", "财务部"},
            {"008", "郑十", "女", "13800138007", "行政助理", "4000", "行政部"},
            {"009", "王十一", "男", "13800138008", "市场专员", "5500", "市场部"},
            {"010", "李十二", "女", "13800138009", "客服代表", "4500", "客服部"}
        };

        for (String[] template : templates) {
            tableModel.addRow(template);
            Employee employee = new Employee(
                template[0],
                template[1],
                template[2],
                template[3],
                template[4],
                Double.parseDouble(template[5]),
                template[6]
            );
            employees.add(employee);
        }
    }

    // 新增方法：筛选表格数据
    private void filterTable(String searchText) {
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        employeeTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // 忽略大小写匹配
    }

    private void addPopupMenuToTable() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("修改");
        JMenuItem deleteItem = new JMenuItem("删除");

        popupMenu.add(editItem);
        popupMenu.add(deleteItem);

        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopupMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopupMenu(e);
                }
            }

            private void showPopupMenu(MouseEvent e) {
                int row = employeeTable.rowAtPoint(e.getPoint());
                if (row >= 0 && row < employeeTable.getRowCount()) {
                    employeeTable.setRowSelectionInterval(row, row);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        // 添加删除菜单项的事件监听器
        deleteItem.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow >= 0) {
                String employeeId = (String) tableModel.getValueAt(selectedRow, 0); // 获取选中行的ID
                int confirm = JOptionPane.showConfirmDialog(null, "确认删除员工ID: " + employeeId, "删除提示", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // 从表格中删除选中行
                    tableModel.removeRow(selectedRow);

                    // 从 employees 列表中删除对应员工
                    employees.removeIf(employee -> employee.getID().equals(employeeId));
                }
            }
        });

        // 添加编辑菜单项的事件监听器
        editItem.addActionListener(e -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow >= 0) {
                String[] columnNames = {"ID", "姓名", "性别", "电话", "职位", "薪水", "部门"};
                JTextField[] textFields = new JTextField[columnNames.length];

                // 创建面板来排列标签和文本框
                JPanel panel = new JPanel(new GridLayout(0, 1));
                for (int i = 0; i < columnNames.length; i++) {
                    panel.add(new JLabel(columnNames[i] + ":"));
                    textFields[i] = new JTextField(
                        tableModel.getValueAt(selectedRow, i).toString()
                    );
                    panel.add(textFields[i]);
                }

                // 显示对话框
                int result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "编辑员工信息",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    // 验证所有字段非空
                    boolean isValid = true;
                    for (JTextField field : textFields) {
                        if (field.getText().trim().isEmpty()) {
                            isValid = false;
                            break;
                        }
                    }
                    if (!isValid) {
                        JOptionPane.showMessageDialog(null, "所有字段都必须填写", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // 验证ID唯一性（排除当前行）
                    String newId = textFields[0].getText().trim();
                    for (int i = 0; i < employees.size(); i++) {
                        if (i != selectedRow && employees.get(i).getID().equals(newId)) {
                            JOptionPane.showMessageDialog(null, "该ID已存在", "错误", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    // 验证电话号码格式
                    String phone = textFields[3].getText().trim();
                    if (!phone.matches("^\\d{11}$")) {
                        JOptionPane.showMessageDialog(null, "电话号码必须为11位数字", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // 验证薪水有效性
                    try {
                        double salary = Double.parseDouble(textFields[5].getText().trim());
                        if (salary <= 0) {
                            throw new NumberFormatException();
                        }

                        // 更新表格数据
                        for (int i = 0; i < columnNames.length; i++) {
                            tableModel.setValueAt(textFields[i].getText().trim(), selectedRow, i);
                        }

                        // 更新员工对象
                        Employee emp = employees.get(selectedRow);
                        emp.setName(textFields[1].getText().trim());
                        emp.setSex(textFields[2].getText().trim());
                        emp.setPhone(phone);
                        emp.setPosition(textFields[4].getText().trim());
                        emp.setSalary(salary);
                        emp.setDepartment(textFields[6].getText().trim());

                        JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "薪水必须为有效正数", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}