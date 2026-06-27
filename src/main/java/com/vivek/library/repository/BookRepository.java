package com.vivek.library.repository;

import com.vivek.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
  List<Book> findByTitleContainingAndAuthorContaining(String title, String author);
  //List<Book> findByAuthorContaining(String author);
  List<Book> findByPrice(BigDecimal price);
  List<Book> findByPriceBetween(BigDecimal minPrice,BigDecimal maxPrice);
  List<Book> findByPriceLessThan(BigDecimal price);
  List<Book> findByPriceGreaterThan(BigDecimal price);
  List<Book> findByQuantity(Integer quantity);
  List<Book> findByQuantityGreaterThan(Integer quantity);
  List<Book> findByQuantityLessThan(Integer quantity);

}
