<<<<<<< HEAD
    private void createOnlineUsersPanel(JPanel mainPanel) {
        // 使用空数据模型初始化
        DefaultListModel<String> userListModel = new DefaultListModel<>();
        // 可以通过方法动态添加用户：userListModel.addElement("新用户");

        onlineUsersList = new JList<>(userListModel);
        onlineUsersList.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        onlineUsersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        onlineUsersList.setBackground(BG_COLOR);

        JScrollPane scrollPane = new JScrollPane(onlineUsersList);
        customizeScrollBar(scrollPane, BG_COLOR);

        mainPanel.add(scrollPane, BorderLayout.EAST);
    }

    private void customizeScrollBar(JScrollPane scrollPane, Color color) {
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setBackground(color);
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                // 直接设置受保护字段而非反射访问
                thumbColor = new Color(0xB0B0B0);
            }
        });
    }

    public void updateuser(String[] names) {
        if (onlineUsersList != null) {
            onlineUsersList.setListData(names);
        } else {
            System.err.println("onlineUsersList is not initialized");
        }
    }
}
=======
    private void createOnlineUsersPanel(JPanel mainPanel) {
        // 使用空数据模型初始化
        DefaultListModel<String> userListModel = new DefaultListModel<>();
        // 可以通过方法动态添加用户：userListModel.addElement("新用户");

        onlineUsersList = new JList<>(userListModel);
        onlineUsersList.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        onlineUsersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        onlineUsersList.setBackground(BG_COLOR);

        JScrollPane scrollPane = new JScrollPane(onlineUsersList);
        customizeScrollBar(scrollPane, BG_COLOR);

        mainPanel.add(scrollPane, BorderLayout.EAST);
    }

    private void customizeScrollBar(JScrollPane scrollPane, Color color) {
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setBackground(color);
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                // 直接设置受保护字段而非反射访问
                thumbColor = new Color(0xB0B0B0);
            }
        });
    }

    public void updateuser(String[] names) {
        if (onlineUsersList != null) {
            onlineUsersList.setListData(names);
        } else {
            System.err.println("onlineUsersList is not initialized");
        }
    }
}
>>>>>>> bdeb85edff313e367535c724cc175d32e969374e
