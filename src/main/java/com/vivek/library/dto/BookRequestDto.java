package com.vivek.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class BookRequestDto {

    private Long categoryId;


    @Schema(
            description = "Title of the book",
            example = "Clean Code"
    )
    @NotBlank
    private String title;


    @Schema(
            description = "Author of the book",
            example = "Robert C. Martin"
    )
    @NotBlank
    private String author;


    @Schema(
            description = "ISBN number",
            example = "9780132350884"
    )
    @NotBlank
    private String isbn;


    @Schema(
            description = "Book price",
            example = "599.99"
    )
    @PositiveOrZero
    private BigDecimal price;


    @Schema(
            description = "Total quantity of books",
            example = "10"
    )
    @Positive
    private Integer quantity;
    public BookRequestDto(){

    }

    public BookRequestDto(String title, String author, String isbn, BigDecimal price, Integer quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
