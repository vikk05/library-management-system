package com.vivek.library.dto;

import com.vivek.library.enums.BorrowStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BorrowResponseDto {
    @Schema(description = "Unique borrow transaction ID", example = "15")
    private Long borrowId;

    @Schema(description = "Book ID", example = "3")
    private Long bookId;

    @Schema(description = "Book title", example = "Clean Code")
    private String bookTitle;

    @Schema(description = "Borrower's name", example = "Vivek")
    private String borrowerName;

    @Schema(description = "Date when the book was borrowed", example = "2026-07-09")
    private LocalDate borrowDate;

    @Schema(description = "Due date for returning the book", example = "2026-07-23")
    private LocalDate dueDate;

    @Schema(description = "Actual return date", example = "2026-07-18")
    private LocalDate returnDate;

    @Schema(description = "Current borrow status", example = "BORROWED")
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
