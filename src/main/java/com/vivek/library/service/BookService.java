package com.vivek.library.service;

import com.vivek.library.entity.Book;
import com.vivek.library.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Book saveBook(Book book){
        if(book.getQuantity()<0){
            throw new IllegalArgumentException("Quantity can't be negative");
        }

        if(book.getQuantity()>1000){
            book.setQuantity(1000);
        }
        book.setAvailableQuant(book.getQuantity());
        return bookRepository.save(book);
    }

}
