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

    // Constructor for creat book
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

    // SetBookId
    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    /**
     * @return the bookTitle
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     * @param bookTitle the bookTitle to set
     */
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    // Get Genre
    public String getGenre() {
        return genre;
    }

    // Set Genre
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the bookAuthor
     */
    public String getBookAuthor() {
        return bookAuthor;
    }

    /**
     * @param bookAuthor the bookAuthor to set
     */
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    /**
     * @return the totalBook
     */
    public Integer getTotalBook() {
        return totalBook;
    }

    /**
     * @param totalBook the totalBook to set
     */
    public void setTotalBook(int totalBook) {
        this.totalBook = totalBook;
    }

    /**
     * @return the availBook
     */
    public Integer getAvailBook() {
        return this.availBook;
    }

    /**
     * @param availBook the availBook to set
     */
    public void setAvailBook(int availBook) {
        this.availBook = availBook;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the publicationDate
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * @param publicationDate the publicationDate to set
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    // Check stock have to be integer
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
