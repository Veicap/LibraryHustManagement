/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Book;
/**
 *
 * @author Legion
 */
public class BookEntity extends BaseEntity {
    public static void AddBook(Book newBook) {
        open();

        try {
            String sql = "INSERT INTO Book (title, author, genre, publisher, publicationDate ,totalBook, availLeft) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);

            statement.setString(1, newBook.getBookTitle());
            statement.setString(2, newBook.getBookAuthor());
            statement.setString(3, newBook.getGenre());
            statement.setString(4, newBook.getPublisher());
            statement.setDate(5, new Date(newBook.getPublicationDate().getTime()));
            statement.setInt(6, newBook.getTotalBook());
            statement.setInt(7, newBook.getAvailBook());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }
    public static boolean IsExisted(Book newBook) {
        open();
        ResultSet resultSet = null;
        try {

            String query = "SELECT COUNT(*) FROM book WHERE title = ? AND author = ? AND genre = ? AND publisher = ? AND publicationDate = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, newBook.getBookTitle());
            statement.setString(2, newBook.getBookAuthor());
            statement.setString(3, newBook.getGenre());
            statement.setString(4, newBook.getPublisher());
            statement.setDate(5,new Date(newBook.getPublicationDate().getTime()));
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
    // Get All Data of Book
    public static ObservableList<Book> GetDataBooks() throws SQLException {
        open();
        List<Book> bookList = new Vector<>();
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM book")) {
            while (rs.next()) {
                Book book = new Book();
                book.setBookTitle(rs.getString("title"));
                book.setBookID(rs.getInt("bookID"));
                book.setBookAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationDate(rs.getDate("publicationDate"));
                book.setTotalBook(rs.getInt("totalBook"));
                book.setAvailBook(rs.getInt("availLeft"));
                bookList.add(book);
            }
        } catch (Exception ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Book> bookDataList = FXCollections.observableList(bookList);
        return bookDataList;
    }

    public static void UpdateBook(Book updateBook, Book currentBook) {
        open();

        try {
            String sql = "UPDATE book SET title = ?, author = ?, genre = ?, publisher = ?, publicationDate = ?, totalBook = ?, availLeft = ? WHERE bookID = ?";
            statement = conn.prepareStatement(sql);

            statement.setString(1, updateBook.getBookTitle());
            statement.setString(2, updateBook.getBookAuthor());
            statement.setString(3, updateBook.getGenre());
            statement.setString(4, updateBook.getPublisher());
            statement.setDate(5, new Date(updateBook.getPublicationDate().getTime()));
            statement.setInt(6, updateBook.getTotalBook());
            Integer availLeft = updateBook.getTotalBook() - currentBook.getTotalBook() + currentBook.getAvailBook();
            statement.setInt(7, availLeft);
            statement.setInt(8, currentBook.getBookID());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static void DeleteBook(Integer deletedBookID) {
        open();

        String sql = "DELETE FROM book WHERE bookID = ?";

        try {
            statement = conn.prepareStatement(sql);

            statement.setInt(1, deletedBookID);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static ObservableList<Book> GetDataBookByTitle(String title) {
        open();
        List<Book> bookList = new ArrayList<>();
        ObservableList<Book> dataBook = null;
        try {
            String sql = "Select * From book WHERE title = ?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookTitle(rs.getString("title"));
                book.setBookID(rs.getInt("bookID"));
                book.setBookAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationDate(rs.getDate("publicationDate"));
                book.setTotalBook(rs.getInt("totalBook"));
                book.setAvailBook(rs.getInt("availLeft"));
                bookList.add(book);
            }
            dataBook = FXCollections.observableList(bookList);

        } catch (SQLException ex) {
            Logger.getLogger(CatalogEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        System.out.print("Hello");
        return dataBook;
    }

    public static ObservableList<Book> GetDataBookByGenre(String genre) {
        open();
        List<Book> bookList = new ArrayList<>();
        ObservableList<Book> dataBook = null;
        try {
            String sql = "Select * From book WHERE genre = ?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, genre);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookTitle(rs.getString("title"));
                book.setBookID(rs.getInt("bookID"));
                book.setBookAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationDate(rs.getDate("publicationDate"));
                book.setTotalBook(rs.getInt("totalBook"));
                book.setAvailBook(rs.getInt("availLeft"));
                bookList.add(book);
            }
            dataBook = FXCollections.observableList(bookList);

        } catch (SQLException ex) {
            Logger.getLogger(CatalogEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

        return dataBook;
    }

    public static ObservableList<Book> GetDataBookByAuthor(String author) {
        open();
        List<Book> bookList = new ArrayList<>();
        ObservableList<Book> dataBook = null;
        try {
            String sql = "Select * From book WHERE author = ?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, author);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookTitle(rs.getString("title"));
                book.setBookID(rs.getInt("bookID"));
                book.setBookAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationDate(rs.getDate("publicationDate"));
                book.setTotalBook(rs.getInt("totalBook"));
                book.setAvailBook(rs.getInt("availLeft"));
                bookList.add(book);
            }
            dataBook = FXCollections.observableList(bookList);

        } catch (SQLException ex) {
            Logger.getLogger(CatalogEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

        return dataBook;
    }

    public static ObservableList<Book> GetDataBookByTitleAndGenre(String title, String genre) {
        open();
        List<Book> bookList = new ArrayList<>();
        ObservableList<Book> dataBook = null;
        try {
            String sql = "Select * From book WHERE title = ? AND genre = ?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, genre);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookTitle(rs.getString("title"));
                book.setBookID(rs.getInt("bookID"));
                book.setBookAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationDate(rs.getDate("publicationDate"));
                book.setTotalBook(rs.getInt("totalBook"));
                book.setAvailBook(rs.getInt("availLeft"));
                bookList.add(book);
            }
            dataBook = FXCollections.observableList(bookList);

        } catch (SQLException ex) {
            Logger.getLogger(CatalogEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        // System.out.print("Hello");
        return dataBook;
    }

    public static ObservableList<Book> GetDataBookByTitleAndAuthor(String title, String author) {
        open();
        List<Book> bookList = new ArrayList<>();
        ObservableList<Book> dataBook = null;
        try {
            String sql = "Select * From book WHERE title = ? AND author = ?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, author);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookTitle(rs.getString("title"));
                book.setBookID(rs.getInt("bookID"));
                book.setBookAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationDate(rs.getDate("publicationDate"));
                book.setTotalBook(rs.getInt("totalBook"));
                book.setAvailBook(rs.getInt("availLeft"));
                bookList.add(book);
            }
            dataBook = FXCollections.observableList(bookList);

        } catch (SQLException ex) {
            Logger.getLogger(CatalogEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        // System.out.print("Hello");
        return dataBook;
    }

    public static ObservableList<Book> GetDataBookByTitleAndGenreAndAuthor(String title, String genre, String author) {
        open();
        List<Book> bookList = new ArrayList<>();
        ObservableList<Book> dataBook = null;
        try {
            String sql = "Select * From book WHERE title = ? AND genre = ? And author = ?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, genre);
            statement.setString(3, author);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookTitle(rs.getString("title"));
                book.setBookID(rs.getInt("bookID"));
                book.setBookAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationDate(rs.getDate("publicationDate"));
                book.setTotalBook(rs.getInt("totalBook"));
                book.setAvailBook(rs.getInt("availLeft"));
                bookList.add(book);
            }
            dataBook = FXCollections.observableList(bookList);

        } catch (SQLException ex) {
            Logger.getLogger(CatalogEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        // System.out.print("Hello");
        return dataBook;
    }

    public static ObservableList<Book> GetDataBookByGenreAndAuthor(String genre, String author) {
        open();
        List<Book> bookList = new ArrayList<>();
        ObservableList<Book> dataBook = null;
        try {
            String sql = "Select * From book WHERE genre = ? AND author = ?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, genre);
            statement.setString(2, author);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookTitle(rs.getString("title"));
                book.setBookID(rs.getInt("bookID"));
                book.setBookAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationDate(rs.getDate("publicationDate"));
                book.setTotalBook(rs.getInt("totalBook"));
                book.setAvailBook(rs.getInt("availLeft"));
                bookList.add(book);
            }
            dataBook = FXCollections.observableList(bookList);

        } catch (SQLException ex) {
            Logger.getLogger(CatalogEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        // System.out.print("Hello");
        return dataBook;
    }

    // Update number of available books after borrow
    public static void UpdateBorrowAvailBook(Book book) {
        open();

        try {
            String sql = "UPDATE Book SET availLeft = ? WHERE bookID = ?";
            statement = conn.prepareStatement(sql);

            book.setAvailBook(book.getAvailBook() - 1);
            statement.setInt(1, book.getAvailBook());
            statement.setInt(2, book.getBookID());

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    // Update number of available books after reuturn
    public static void UpdateReturnAvailBook(Integer bookID, Integer availLeft) {
        open();

        try {
            String sql = "UPDATE Book SET availLeft = ? WHERE bookID = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, availLeft + 1);
            statement.setInt(2, bookID);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }

    public static Integer getAvailLeftFromID(Integer bookID) {
        open();
        Integer availLeft = null;
        try {
            String sql = "SELECT availLeft FROM book WHERE bookID = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, bookID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                availLeft = rs.getInt("availLeft");
            } else {
                Logger.getLogger(BookEntity.class.getName()).log(Level.WARNING, "No book found with bookID: " + bookID);
            }
        } catch (Exception ex) {
            Logger.getLogger(BookEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return availLeft;
    }
}
