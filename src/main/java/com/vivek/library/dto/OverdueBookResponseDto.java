package com.vivek.library.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OverdueBookResponseDto {
    private Long borrowId;
    private Long userId;
    private String username;
    private String email;

    private Long bookId;
    private String bookTitle;

    private LocalDate borrowDate;
    private LocalDate dueDate;

    private long overdueDays;

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    private BigDecimal fine;
    public OverdueBookResponseDto(){

    }
    public OverdueBookResponseDto(BigDecimal fine,Long borrowId, Long userId, String username, String email, Long bookId, String bookTitle, LocalDate borrowDate, LocalDate dueDate, long overdueDays) {
        this.borrowId = borrowId;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.overdueDays = overdueDays;
        this.fine=fine;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public long getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(long overdueDays) {
        this.overdueDays = overdueDays;
    }
}
