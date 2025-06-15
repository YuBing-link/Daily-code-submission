import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTEST3 {
    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con =  DriverManager.getConnection("jdbc:mysql:///usermyt","root","40475625");
        //String sql = "update employees set full_name='撸管' where employee_id=10 ";
        String sql="select * from departments where department_id=? and department_name=?";

        PreparedStatement stmt = con.prepareStatement(sql);
        int id =1;
        String name="人力资源";
        stmt.setInt(1,id );
        stmt.setString(2,name);
        ResultSet query = stmt.executeQuery();
        if (query.next())
            System.out.println(1);
        else   System.out.println(0);

            query.close();
            con.close();

    }
}
