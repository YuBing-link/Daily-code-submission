package Dip;

import link_mysql.link;
import moudle.Booktype;

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
        String sql="select *from t_booktype";
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

    public static boolean reset(int id, String bookTypeName, String bookTypeDesc) {
        String sql = "UPDATE t_booktype SET bookTypeName = ?, bookTypeDesc = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = link.getcon(); // 获取数据库连接
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookTypeName);
            pstmt.setString(2, bookTypeDesc);
            pstmt.setInt(3, id); // 修正参数索引

            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("更新书籍类型失败", e);
        } finally {
            // 确保资源被关闭
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
