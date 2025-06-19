package Dip;

import moudle.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDip {
    public static void add(Connection con, User user){

            String sql="insert into t_user (userName,password) values (?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,user.getUserName());
            pstmt.setString(2,user.getPassword());
            pstmt.executeUpdate();
            pstmt.close();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }





}
