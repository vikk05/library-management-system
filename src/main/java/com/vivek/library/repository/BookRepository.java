package com.vivek.library.repository;

import com.vivek.library.dto.BookTitlePriceDto;
import com.vivek.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long>, JpaSpecificationExecutor<Book> {

    @Query(" SELECT new com.vivek.library.dto.BookTitlePriceDto( b.title,b.price )from Book b")
    List<BookTitlePriceDto> getBookTitleAndPrice();
    List<Book> findByPrice(BigDecimal price);
    List<Book> findByPriceBetween(BigDecimal minPrice,BigDecimal maxPrice);


    @Query("Select b from Book b where b.price>:price")
    List<Book> findBooksWithPriceGreaterThan(@Param("price") BigDecimal price);
    List<Book> findByQuantity(Integer quantity);

}
