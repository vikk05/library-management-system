package com.vivek.library.service;

import com.vivek.library.dto.BookRequestDto;
import com.vivek.library.dto.BookResponseDto;
import com.vivek.library.entity.Book;
import com.vivek.library.entity.Category;
import com.vivek.library.exception.BookNotFoundException;
import com.vivek.library.repository.BookRepository;
import com.vivek.library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository= categoryRepository;
    }
    public BookResponseDto saveBook(BookRequestDto dto){
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        book.setIsbn(dto.getIsbn());
        book.setQuantity(dto.getQuantity());
        book.setAvailableQuant(dto.getQuantity());
        if(book.getQuantity()<0){
            throw new IllegalArgumentException("Quantity can't be negative");
        }

        if(book.getQuantity()>1000){
            book.setQuantity(1000);
        }
        book.setAvailableQuant(book.getQuantity());

        Book savedBook = bookRepository.save(book);
        BookResponseDto responseDto= new BookResponseDto();

        responseDto.setId(savedBook.getId());
        responseDto.setTitle(savedBook.getTitle());
        responseDto.setAuthor(savedBook.getAuthor());
        responseDto.setPrice(savedBook.getPrice());
        responseDto.setIsbn(savedBook.getIsbn());
        responseDto.setQuantity(savedBook.getQuantity());
        responseDto.setAvailableQuant(savedBook.getQuantity());


        return responseDto;
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
