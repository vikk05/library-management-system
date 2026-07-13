package com.vivek.library.entity;

import com.vivek.library.enums.BorrowStatus;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Borrow {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String borrowerName;



    private LocalDate dueDate;
    private LocalDate borrowDate;

    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private BorrowStatus status;


    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Borrow(){

    }

    public Borrow(Long id, String borrowerName, LocalDate borrowDate,LocalDate dueDate, LocalDate returnDate, BorrowStatus status, Book book) {
        this.id = id;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
        this.book=book;
        this.dueDate=dueDate;
    }


    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
