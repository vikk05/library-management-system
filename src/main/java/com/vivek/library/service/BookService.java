package com.vivek.library.service;

import com.vivek.library.dto.BookRequestDto;
import com.vivek.library.dto.BookResponseDto;
import com.vivek.library.entity.Book;
import com.vivek.library.entity.Category;
import com.vivek.library.exception.BookNotFoundException;
import com.vivek.library.exception.CategoryNotFoundException;
import com.vivek.library.repository.BookRepository;
import com.vivek.library.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public Page<BookResponseDto> getAllBooks(Pageable pageable){
        Page<Book> books= bookRepository.findAll(pageable);


        return books.map(this::mapToDto);
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

    public void deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book not found with id : "+id));
        
        bookRepository.delete(book);
    }

    public List<BookResponseDto> searchBook(String title,String author){
        title=title.trim();
        author=author.trim();

        if(title.isBlank()||author.isBlank()){
            throw new IllegalArgumentException("Search Cannot be Empty");
        }
        else if(title.length()<3 || author.length()<3){
            throw new IllegalArgumentException("Search must be at least 3 characters");
        }

        List<Book> books= bookRepository.findByTitleContainingAndAuthorContaining(title,author);

        List<BookResponseDto> response= new ArrayList<>();

        for(Book book:books){
            response.add(mapToDto(book));
        }

        return response;
    }

    public List<BookResponseDto> searchByPriceRange(BigDecimal minPrice, BigDecimal maxPrice){
        if(minPrice.compareTo(BigDecimal.ZERO)<0 || maxPrice.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if(minPrice.compareTo(maxPrice)>0){
            throw new IllegalArgumentException("Min price can't be grater than max price");
        }
        List<Book> books= bookRepository.findByPriceBetween(minPrice,maxPrice);
        List<BookResponseDto> response= new ArrayList<>();

        for(Book book:books){
            response.add(mapToDto(book));
        }
        return response;
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
