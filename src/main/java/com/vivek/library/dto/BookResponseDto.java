package com.vivek.library.dto;

import java.math.BigDecimal;

public class BookResponseDto {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private Integer quantity;
    private Integer availableQuant;

    public BookResponseDto(){

    }

    public BookResponseDto(Long id,String title, String author, String isbn, BigDecimal price, Integer quantity, Integer availableQuant){
        this.id=id;
        this.title=title;
        this.author=author;
        this.isbn=isbn;
        this.price=price;
        this.quantity=quantity;
        this.availableQuant=availableQuant;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return this.id;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setAuthor(String author){
        this.author=author;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setPrice(BigDecimal price){
        this.price=price;
    }
    public BigDecimal getPrice(){
        return this.price;
    }
    public void setQuantity(Integer quantity){
        this.quantity=quantity;
    }
    public Integer getQuantity(){
        return this.quantity;
    }
    public void setIsbn(String isbn){
        this.isbn=isbn;
    }
    public String getIsbn(){
        return this.isbn;
    }
    public void setAvailableQuant(Integer availableQuant){
        this.availableQuant=availableQuant;
    }
    public Integer getAvailableQuant(){
        return this.availableQuant;
    }
}
