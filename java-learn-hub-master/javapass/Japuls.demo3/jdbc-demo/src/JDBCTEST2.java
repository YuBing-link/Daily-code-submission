import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JDBCTEST2 {
    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con =  DriverManager.getConnection("jdbc:mysql:///usermyt","root","40475625");
        //String sql = "update employees set full_name='撸管' where employee_id=10 ";
        String sql="select * from departments";
        Statement result = con.createStatement();
        ResultSet query = result.executeQuery(sql);
        List<departments> list=new ArrayList<>();
        departments d=new departments();

        while (query.next()){
            int id = query.getInt("department_id");
            String name = query.getString("department_name");
            String location = query.getString("location");
//            d.setDepartmentId(id);
//            d.setDepartmentName(name);
//            d.setLocation(location);
//            list.add(d);
        departments w=new departments(id, name, location);
        list.add(w);
        }
        System.out.println(list);
        query.close();
        result.close();
        con.close();

    }







}
