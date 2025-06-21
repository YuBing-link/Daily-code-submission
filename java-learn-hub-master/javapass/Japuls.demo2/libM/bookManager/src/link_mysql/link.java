package link_mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class link {
    public static Connection getcon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_book?serverTimezone=Asia/Shanghai","root","root");
            return con;
        } catch (Exception e) {
            System.err.println("数据库连接出错: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }



}
