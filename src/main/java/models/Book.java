/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author Legion
 */
public class Book {
    private String bookTitle;
    private String genre;
    private String bookAuthor;
    private Integer totalBook;
    private Integer availBook;
    private String publisher;
    private Date publicationDate;
    private Integer bookID;

    public Book(String bookTitle, String genre, String bookAuthor, Integer totalBook, Integer availBook,
            String publisher, Date publicationDate) {
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.bookAuthor = bookAuthor;
        this.totalBook = totalBook;
        this.availBook = availBook;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
    }

    public Book(String bookTitle, String genre, String bookAuthor, Integer totalBook, String publisher,
            Date publicationDate) {
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.bookAuthor = bookAuthor;
        this.totalBook = totalBook;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
    }

    public Book() {

    }
    public Integer getBookID() {
        return bookID;
    }
    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public Integer getTotalBook() {
        return totalBook;
    }
    public void setTotalBook(int totalBook) {
        this.totalBook = totalBook;
    }
    public Integer getAvailBook() {
        return this.availBook;
    }
    public void setAvailBook(int availBook) {
        this.availBook = availBook;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
    public static Boolean IsValidStock(String stock) {
        if (stock == null || stock.isEmpty()) {
            return false;
        }
        for (char c : stock.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
