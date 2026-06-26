package com.vivek.library.controller;

import com.vivek.library.dto.BookRequestDto;
import com.vivek.library.dto.BookResponseDto;
import com.vivek.library.service.BookService;

import jakarta.validation.Valid;
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
     public List<BookResponseDto> getAllBooks(){
         return bookService.getAllBooks();
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
         boolean deleted= bookService.deleteBook(id);

         if(!deleted){
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.noContent().build();

     }




     


}
