package Dip;

import link_mysql.link;
import moudle.Book;
import moudle.Booktype;

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


}











