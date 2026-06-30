package com.vivek.library.dto;

import java.math.BigDecimal;

public class BookTitlePriceDto {

    private String title;
    private BigDecimal price;
     public BookTitlePriceDto(){

     }
    public BookTitlePriceDto(String title, BigDecimal price){
        this.title=title;
        this.price=price;
    }

    public void setTitle(String title){
         this.title=title;
    }
    public String getTitle(){
        return title;
    }
    public void setPrice(BigDecimal price){
        this.price=price;
    }
    public BigDecimal getPrice(){
       return price;
    }
}
