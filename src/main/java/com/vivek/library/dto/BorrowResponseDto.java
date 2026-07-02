package com.vivek.library.dto;

import com.vivek.library.enums.BorrowStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BorrowResponseDto {
    private Long bookId;
    private String borrowerName;
    private String bookTitle;
    private Long borrowId;
    private LocalDate dueDate;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private BorrowStatus status;
    public BorrowResponseDto(){

    }
    public BorrowResponseDto(Long bookId, String borrowerName, String bookTitle, LocalDate borrowDate,LocalDate returnDate,LocalDate dueDate,BorrowStatus status ,Long borrowId) {
        this.bookId = bookId;
        this.borrowerName = borrowerName;
        this.bookTitle = bookTitle;
        this.borrowDate=borrowDate;
        this.returnDate=returnDate;
        this.dueDate=dueDate;
        this.borrowId=borrowId;
        this.status=status;

    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BorrowStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowStatus status) {
        this.status = status;
    }



    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
