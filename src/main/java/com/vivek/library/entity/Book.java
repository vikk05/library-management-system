package com.vivek.library.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 50, nullable = false)
    private String author;
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Integer availableQuant;
    @Column(nullable = false, precision=10,scale =2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Book() {

    }

    public Book(String title, String author, String isbn, Integer quantity, Integer availableQuant, BigDecimal price, Category category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.quantity = quantity;
        this.availableQuant = availableQuant;
        this.price = price;
        this.category = category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getIsbn() {
        return this.isbn;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getQuantity() {
        return this.quantity;
    }
    public void setAvailableQuant(Integer availableQuant) {
        this.availableQuant = availableQuant;
    }
    public Integer getAvailableQuant() {
        return this.availableQuant;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public  BigDecimal getPrice() {
        return this.price;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Category getCategory() {
        return this.category;
    }


}

