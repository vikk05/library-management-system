package com.vivek.library.controller;

import com.vivek.library.entity.Book;
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
     public List<Book> getAllBooks(){
         System.out.println("Book get");
         return bookService.getAllBooks();
     }

     @GetMapping("/{id}")
     public Book getBookById(@PathVariable Long id){
         System.out.println("Book get by id");
         return bookService.getBookById(id);
     }
     @PostMapping
     public Book addBook(@Valid @RequestBody Book book){
         return bookService.saveBook(book);
     }
     @PutMapping("/{id}")
     public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook){
         Book book =bookService.updateBook(id,updatedBook);

         if(book==null){
             return ResponseEntity.notFound().build();
         }
        return ResponseEntity.ok(book);
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
