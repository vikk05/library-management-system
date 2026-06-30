package com.vivek.library.dto;

import java.math.BigDecimal;

public class BookSearchRequest {
    private String title;
    private String author;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    public BookSearchRequest(){

    }
    public BookSearchRequest(String title, String author, BigDecimal minPrice, BigDecimal maxPrice) {
        this.title = title;
        this.author = author;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
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

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
}
