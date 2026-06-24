package com.vivek.library.service;

import com.vivek.library.entity.Book;
import com.vivek.library.exception.BookNotFoundException;
import com.vivek.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow(() ->new BookNotFoundException("Book not found with id :" +id));
    }

    public Book updateBook(Long id,Book updatedBook) {
        Book existingbook = bookRepository.findById(id).orElse(null);
        if (existingbook == null) {
            return null;
        }

        existingbook.setTitle(updatedBook.getTitle());
        existingbook.setAuthor(updatedBook.getAuthor());
        existingbook.setPrice(updatedBook.getPrice());
        existingbook.setIsbn(updatedBook.getIsbn());
        existingbook.setQuantity(updatedBook.getQuantity());
        existingbook.setAvailableQuant(updatedBook.getQuantity());

        return bookRepository.save(existingbook);
    }

    public boolean deleteBook(Long id){
        Book book = bookRepository.findById(id).orElse(null);

        if(book==null){
            return false;
        }
        bookRepository.delete(book);

        return true;
    }

}
