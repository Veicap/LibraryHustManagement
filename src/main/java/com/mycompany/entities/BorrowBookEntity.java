/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import static com.mycompany.entities.BaseEntity.close;
import static com.mycompany.entities.BaseEntity.conn;
import static com.mycompany.entities.BaseEntity.open;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Book;
import models.BorrowBook;

/**
 *
 * @author Legion
 */
public class BorrowBookEntity extends BaseEntity {

    public static void BorrowBook(BorrowBook newBorrow) {
        open();

        try {
            String sql = "INSERT INTO BookBorrow (accountID, bookID, borrowDate, dueDate, title) VALUES (?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, newBorrow.getAccountID());
            statement.setInt(2, newBorrow.getBookID());
            statement.setDate(3, new Date(newBorrow.getBorrowDate().getTime()));
            statement.setDate(4, new Date(newBorrow.getDueDate().getTime()));
            statement.setString(5, newBorrow.getTitle());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }
    public static boolean IsExistedInBorrowBook(Integer bookID) {
        open();
        ResultSet resultSet = null;
        try {

            String query = "SELECT COUNT(*) FROM bookborrow WHERE bookID = ? AND returnDate IS NULL";
            statement = conn.prepareStatement(query);
            statement.setInt(1, bookID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return false;
    }
    public static boolean IsStudentBorrowingBook(Integer studentID) {
        open();
        ResultSet resultSet = null;
        try {

            String query = "SELECT COUNT(*) FROM bookborrow WHERE accountID = ? AND returnDate IS NULL";
            statement = conn.prepareStatement(query);
            statement.setInt(1, studentID);
           
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return false;
    }
    // Get All Data of Borrow Book
    public static ObservableList<BorrowBook> GetDataBorrowBooks() throws SQLException {
        open();
        List<BorrowBook> borrowBookList = new Vector<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM bookborrow")) {
            while (rs.next()) {
                BorrowBook borrowBook = new BorrowBook();
                borrowBook.setBorrowID(rs.getInt("borrowID"));
                borrowBook.setBookID(rs.getInt("bookID"));
                borrowBook.setAccountID(rs.getInt("accountID"));
                borrowBook.setBorrowDate(rs.getDate("borrowDate"));
                borrowBook.setDueDate(rs.getDate("dueDate"));
                borrowBook.setReturnDate(rs.getDate("returnDate"));
                borrowBook.setTitle(rs.getString("title"));
                borrowBookList.add(borrowBook);
            }
        } catch (Exception ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<BorrowBook> borrowBookDataList = FXCollections.observableList(borrowBookList);
        return borrowBookDataList;
    }
    
    public static ObservableList<BorrowBook> GetDataBorrowBooksByStudentID(Integer studentID) throws SQLException {
    open();  // Ensure this method properly initializes the connection
    List<BorrowBook> borrowBookList = new Vector<>();
    String sql = "SELECT * FROM bookborrow WHERE accountID = ?";
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, studentID);
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                BorrowBook borrowBook = new BorrowBook();
                borrowBook.setBorrowID(rs.getInt("borrowID"));
                borrowBook.setBookID(rs.getInt("bookID"));
                borrowBook.setAccountID(rs.getInt("accountID"));
                borrowBook.setBorrowDate(rs.getDate("borrowDate"));
                borrowBook.setDueDate(rs.getDate("dueDate"));
                borrowBook.setReturnDate(rs.getDate("returnDate"));
                borrowBook.setTitle(rs.getString("title"));
                borrowBookList.add(borrowBook);
            }
        } catch (SQLException e) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, "Error executing query", e);
            throw e;  // Rethrow exception if you need to handle it elsewhere
        }
    } catch (SQLException e) {
        Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, "Error preparing statement", e);
        throw e;  // Rethrow exception if you need to handle it elsewhere
    } finally {
        // Consider closing the connection if it is not managed elsewhere
        close();  // Ensure this method properly closes the connection
    }

    return FXCollections.observableList(borrowBookList);
}
    
    public static ObservableList<BorrowBook> GetDataBorrowSearch(Integer borrowID, Integer bookID, Integer accountID)
            throws SQLException {
        open();
        List<BorrowBook> borrowBookList = new Vector<>();
        Boolean isFirst = true;
        Boolean borrow = false;
        Boolean book = false;
        Boolean account = false;
        String sql = "SELECT * FROM bookborrow";
        if (borrowID != 0) {
            sql += " WHERE borrowID = ?";
            borrow = true;
            isFirst = false;
        }
        if (bookID != 0) {
            book = true;
            if (isFirst) {
                sql += " WHERE bookID = ?";
                isFirst = false;
            } else
                sql += " AND bookID = ?";
        }
        if (accountID != 0) {
            account = true;
            if (isFirst)
                sql += " WHERE accountID = ?";
            else
                sql += " AND accountID = ?";
        }

        try {
            statement = conn.prepareStatement(sql);
            if (borrow)
                statement.setInt(1, borrowID);
            if (book) {
                if (borrow)
                    statement.setInt(2, bookID);
                else
                    statement.setInt(1, bookID);
            }
            if (account) {
                if (borrow && book)
                    statement.setInt(3, accountID);
                else if (!borrow && !book)
                    statement.setInt(1, accountID);
                else
                    statement.setInt(2, accountID);
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                BorrowBook borrowBook = new BorrowBook();
                borrowBook.setBorrowID(rs.getInt("borrowID"));
                borrowBook.setBookID(rs.getInt("bookID"));
                borrowBook.setAccountID(rs.getInt("accountID"));
                borrowBook.setBorrowDate(rs.getDate("borrowDate"));
                borrowBook.setDueDate(rs.getDate("dueDate"));
                borrowBook.setReturnDate(rs.getDate("returnDate"));
                borrowBook.setTitle(rs.getString("title"));
                borrowBookList.add(borrowBook);
            }
        } catch (Exception ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<BorrowBook> borrowBookDataList = FXCollections.observableList(borrowBookList);
        return borrowBookDataList;
    }
    
    
    public static List<String> getBorrowIDList() {
        List<String> borrowID = new ArrayList<>();
        List<String> uniqueBorrowID = null;
        open();
        try {
            String sql = "SELECT borrowID FROM bookborrow";
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer ID = rs.getInt("borrowID");
                borrowID.add(Integer.toString(ID));
            }
            Set<String> uniqueID = new HashSet<>(borrowID);
            uniqueBorrowID = new ArrayList<>(uniqueID);
        } catch (Exception ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqueBorrowID;
    }
    public static List<String> getBorrowIDListByStudentID(Integer studentID) {
        List<String> borrowID = new ArrayList<>();
        List<String> uniqueBorrowID = null;
        open();
        try {
            String sql = "SELECT borrowID FROM bookborrow WHERE accountID = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, studentID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer ID = rs.getInt("borrowID");
                borrowID.add(Integer.toString(ID));
            }
            Set<String> uniqueID = new HashSet<>(borrowID);
            uniqueBorrowID = new ArrayList<>(uniqueID);
        } catch (Exception ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqueBorrowID;
    }

    public static List<String> getBookIDList() {
        List<String> bookID = new ArrayList<>();
        List<String> uniqueBookID = null;
        open();
        try {
            String sql = "SELECT bookID FROM bookborrow";
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer ID = rs.getInt("bookID");
                bookID.add(Integer.toString(ID));
            }
            Set<String> uniqueID = new HashSet<>(bookID);
            uniqueBookID = new ArrayList<>(uniqueID);
        } catch (Exception ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqueBookID;
    }
    
    public static List<String> getBookIDListByStudentID(Integer studentID) {
        List<String> borrowID = new ArrayList<>();
        List<String> uniqueBorrowID = null;
        open();
        try {
            String sql = "SELECT bookID FROM bookborrow WHERE accountID = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, studentID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer ID = rs.getInt("bookID");
                borrowID.add(Integer.toString(ID));
            }
            Set<String> uniqueID = new HashSet<>(borrowID);
            uniqueBorrowID = new ArrayList<>(uniqueID);
        } catch (Exception ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqueBorrowID;
    }
    public static List<String> getAccountIDList() {
        List<String> accountID = new ArrayList<>();
        List<String> uniqueAccountID = null;
        open();
        try {
            String sql = "SELECT accountID FROM bookborrow";
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer ID = rs.getInt("accountID");
                accountID.add(Integer.toString(ID));
            }
            Set<String> uniqueID = new HashSet<>(accountID);
            uniqueAccountID = new ArrayList<>(uniqueID);
        } catch (Exception ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqueAccountID;
    }

    public static List<String> getAccountIDListByStudentID(Integer studentID) {
        List<String> borrowID = new ArrayList<>();
        List<String> uniqueBorrowID = null;
        open();
        try {
            String sql = "SELECT accountID FROM bookborrow WHERE accountID = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, studentID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer ID = rs.getInt("accountID");
                borrowID.add(Integer.toString(ID));
            }
            Set<String> uniqueID = new HashSet<>(borrowID);
            uniqueBorrowID = new ArrayList<>(uniqueID);
        } catch (Exception ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqueBorrowID;
    }
    
    
    public static void returnBook(BorrowBook book) {
        open();
        try {
            String sql = "Update bookborrow SET returnDate = ? WHERE borrowID = ?";
            statement = conn.prepareStatement(sql);
            book.returnBook();
            statement.setDate(1, new Date(book.getReturnDate().getTime()));
            statement.setInt(2, book.getBorrowID());
            statement.execute();
        } catch (Exception ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
