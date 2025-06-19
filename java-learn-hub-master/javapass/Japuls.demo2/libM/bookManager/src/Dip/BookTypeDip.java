package Dip;

import link_mysql.link;
import moudle.Booktype;
import moudle.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookTypeDip {
    public static void add(Connection con, Booktype booktype){

        String sql="insert into t_user (bookTypeName, bookTypeDesc) values (?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,booktype.getBookTypeName());
            pstmt.setString(2,booktype.getBookTypeDesc());
            pstmt.executeUpdate();
            pstmt.close();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static ResultSet query(){
        String sql="select *from t_bookype";
        try {
            PreparedStatement pstmt = link.getcon().prepareStatement(sql);
            ResultSet rs= pstmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static ResultSet query(String name,String str) {


        try {
            String sql = "select *from t_booktype where "+name+" like ?";
            PreparedStatement pstmt = link.getcon().prepareStatement(sql);

            pstmt.setString(1,"%"+str+"%");
            ResultSet rs= pstmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
}
