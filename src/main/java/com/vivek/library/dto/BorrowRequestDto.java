package com.vivek.library.dto;

public class BorrowRequestDto {

    private Long bookId;

    private String borrowerName;
    public BorrowRequestDto(){

    }

    public BorrowRequestDto(Long bookId, String borrowerName) {
        this.bookId = bookId;
        this.borrowerName = borrowerName;
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
}
