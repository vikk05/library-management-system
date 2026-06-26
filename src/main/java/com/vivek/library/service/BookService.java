package com.vivek.library.service;

import com.vivek.library.dto.BookRequestDto;
import com.vivek.library.dto.BookResponseDto;
import com.vivek.library.entity.Book;
import com.vivek.library.entity.Category;
import com.vivek.library.exception.BookNotFoundException;
import com.vivek.library.exception.CategoryNotFoundException;
import com.vivek.library.repository.BookRepository;
import com.vivek.library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository= categoryRepository;
    }
    public BookResponseDto saveBook(BookRequestDto dto){
        Book book = mapToEntity(dto);
        Book savedBook = bookRepository.save(book);
        return mapToDto(savedBook);

    }

    public List<BookResponseDto> getAllBooks(){
        List<Book> book= bookRepository.findAll();

        List<BookResponseDto> response= new ArrayList<>();

        for(Book books :book){
            response.add(mapToDto(books));
        }
        return response;
    }

    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book not found with id : "+ id));

        return mapToDto(book);
    }

    public BookResponseDto updateBook(Long id,BookRequestDto dto) {
        Book existingbook = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book not found with id : "+ id));

        existingbook.setTitle(dto.getTitle());
        existingbook.setAuthor(dto.getAuthor());
        existingbook.setIsbn(dto.getIsbn());
        existingbook.setPrice(dto.getPrice());
        existingbook.setQuantity(dto.getQuantity());

        if (existingbook.getQuantity() > 1000) {
            existingbook.setQuantity(1000);
        }

        existingbook.setAvailableQuant(existingbook.getQuantity());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + dto.getCategoryId()));

        existingbook.setCategory(category);

        Book updatedBook= bookRepository.save(existingbook);

        return mapToDto(updatedBook);
    }

    public boolean deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book not found with id : "+id));
        
        bookRepository.delete(book);

        return true;
    }


    private Book mapToEntity(BookRequestDto dto){
        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        book.setIsbn(dto.getIsbn());
        book.setQuantity(dto.getQuantity());
        book.setAvailableQuant(dto.getQuantity());

        if(book.getQuantity()>1000){
            book.setQuantity(1000);
        }
        book.setAvailableQuant(book.getQuantity());



        Category category= categoryRepository.findById(dto.getCategoryId()).orElseThrow(()->
                new CategoryNotFoundException("Category not found with id : " + dto.getCategoryId()));
        book.setCategory(category);

        return book;
    }

    private BookResponseDto mapToDto(Book book){
        BookResponseDto dto= new BookResponseDto();

        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setPrice(book.getPrice());
        dto.setQuantity(book.getQuantity());
        dto.setAvailableQuant(book.getAvailableQuant());

        return dto;
    }

}
