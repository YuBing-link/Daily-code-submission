import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class JDBCTEST {
    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usermyt","root","40475625");
        String sql = "update employees set full_name='撸管' where employee_id=10 ";
        Statement result = con.createStatement();
        System.out.println(result.execute(sql));

        result.close();
        con.close();

    }

}


