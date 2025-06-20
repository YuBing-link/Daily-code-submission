package Dip;

import link_mysql.link;
import moudle.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDip {

    public static void add(Connection con, Book book){

        String sql="insert into t_user (bookName, author,sex,price,bookDesc,bookTypeId) values (?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,book.getBookName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getSex());
            pstmt.setFloat(4, book.getPrice());
            pstmt.setString(5, book.getBookDesc());
            pstmt.setInt(6, book.getBookTypeId());

            pstmt.executeUpdate();
            pstmt.close();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static ResultSet query(){
        String sql="select *from t_book";
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
            PreparedStatement pstmt;
            if (name.equals("price")) {
                String sql = "select *from t_book where "+name+" = ?";
                pstmt = link.getcon().prepareStatement(sql);
                pstmt.setFloat(1, Float.parseFloat(str));
            } else if (name.equals("bookTypeId") || name.equals("id")) {
                String sql = "select *from t_book where "+ name+" = ?";
                pstmt = link.getcon().prepareStatement(sql);

                pstmt.setInt(1, Integer.parseInt(str));
            } else {
                String sql = "select *from t_book where "+name+" like ?";
                pstmt = link.getcon().prepareStatement(sql);

                pstmt.setString(1, str);
            }
            ResultSet rs = pstmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static boolean reset(int id, String bookName, String author, String sex, float price, String bookDesc, int bookTypeId) {
        String sql = "UPDATE t_book SET bookName = ?, author = ?, sex = ?, price = ?, bookDesc = ?, bookTypeId = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = link.getcon(); // 获取数据库连接
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookName);
            pstmt.setString(2, author);
            pstmt.setString(3, sex);
            pstmt.setFloat(4, price);
            pstmt.setString(5, bookDesc);
            pstmt.setInt(6, bookTypeId);
            pstmt.setInt(7, id);

            int count = pstmt.executeUpdate();
            // 如果更新成功（至少更新了一行），返回true
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            // 可以记录日志或抛出更具体的异常
            throw new RuntimeException("更新书籍信息失败", e);
        } finally {
            // 确保资源被关闭
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close(); // 如果连接不是由连接池管理，需要关闭
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }






}











