package com.vivek.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class BookResponseDto {

    @Schema(description = "Unique ID of the book", example = "101")
    private Long id;

    @Schema(description = "Title of the book", example = "Clean Code")
    private String title;

    @Schema(description = "Author of the book", example = "Robert C. Martin")
    private String author;

    @Schema(description = "International Standard Book Number", example = "9780132350884")
    private String isbn;

    @Schema(description = "Price of the book", example = "599.99")
    private BigDecimal price;

    @Schema(description = "Total number of copies in the library", example = "10")
    private Integer quantity;

    @Schema(description = "Currently available copies", example = "8")
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
