package com.vivek.library.specification;

import com.vivek.library.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class BookSpecification {

    public static Specification<Book> titleContains(String title){
        return(root,query,criteriaBuilder) ->
            criteriaBuilder.like(root.get("title"),"%"+title+"%");

    }

    public static Specification<Book> authorContains(String author){
        return (root,query,criteriaBuilder)->
                criteriaBuilder.like(root.get("author"),"%"+author+"%");
    }

    public static Specification<Book> quantityGreaterThan(Integer quantity){
        return (root,query,criteriaBuilder)->
                criteriaBuilder.greaterThan(root.get("quantity"),quantity);
    }

    public static Specification<Book> priceGreaterThan(BigDecimal minPrice){
        return (root,query,criteriaBuilder)->
                criteriaBuilder.greaterThan(root.get("price"),minPrice);
    }
    public static Specification<Book> priceLessThan(BigDecimal maxPrice){
        return (root,query,criteriaBuilder)->
                criteriaBuilder.lessThan(root.get("price"),maxPrice);
    }
}
