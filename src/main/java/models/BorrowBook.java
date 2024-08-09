/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Legion
 */
public class BorrowBook {
    private Integer borrowID;
    private Integer bookID;
    private Integer accountID;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private String title;

    // Constructor
    public BorrowBook(Integer bookID, Integer accountID, String title) {
        this.bookID = bookID;
        this.accountID = accountID;
        this.title = title;
        borrowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowDate);
        borrowDate = calendar.getTime();
        // + 30 days from now
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        dueDate = calendar.getTime();
        returnDate = null;
    }

    public BorrowBook() {
    }

    public Integer getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(Integer borrowID) {
        this.borrowID = borrowID;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void returnBook() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        this.returnDate = calendar.getTime();
    }
}
