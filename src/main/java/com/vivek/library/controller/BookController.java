package com.vivek.library.controller;

import com.vivek.library.dto.BookRequestDto;
import com.vivek.library.dto.BookResponseDto;
import com.vivek.library.service.BookService;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

     private final BookService bookService;

     public BookController(BookService bookService){
         this.bookService = bookService;
     }
     @GetMapping
     public Page<BookResponseDto> getAllBooks(Pageable pageable){
         return bookService.getAllBooks(pageable);
     }

     @GetMapping("/{id}")
     public BookResponseDto getBookById(@PathVariable Long id){

         return bookService.getBookById(id);
     }
     @PostMapping
     public BookResponseDto addBook(@Valid @RequestBody BookRequestDto dto){
         return bookService.saveBook(dto);
     }
     @PutMapping("/{id}")
     public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDto dto){
         BookResponseDto response =bookService.updateBook(id,dto);

        return ResponseEntity.ok(response);
     }
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);

         return ResponseEntity.noContent().build();

     }




     


}
