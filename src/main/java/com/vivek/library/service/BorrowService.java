package com.vivek.library.service;

import com.vivek.library.dto.BorrowRequestDto;
import com.vivek.library.dto.BorrowResponseDto;
import com.vivek.library.entity.Book;
import com.vivek.library.entity.Borrow;
import com.vivek.library.enums.BorrowStatus;
import com.vivek.library.exception.*;
import com.vivek.library.repository.BookRepository;
import com.vivek.library.repository.BorrowRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;


    public BorrowService(BorrowRepository borrowRepository,BookRepository bookRepository){
        this.borrowRepository=borrowRepository;
        this.bookRepository=bookRepository;
    }

    @Transactional
    public BorrowResponseDto borrowBook(Long bookId,BorrowRequestDto dto){
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException("Book Not Found by id" +bookId));
        Optional<Borrow> existingBorrow= borrowRepository.findByBook_IdAndBorrowerNameAndStatus(bookId,dto.getBorrowerName(),BorrowStatus.BORROWED);
        if(existingBorrow.isPresent()){
            throw new DuplicateBorrowException("You have already borrowed this book. Please return it before borrowing again");
        }

        if(book.getAvailableQuant()<=0){
            throw new BookOutOfStockException("Book is Currently Out of Stock");
        }
        book.setAvailableQuant(book.getAvailableQuant()-1);
        Borrow borrow=new Borrow();

        LocalDate today= LocalDate.now();
        borrow.setBorrowDate(today);
        borrow.setDueDate(today.plusDays(14));

        borrow.setBorrowerName(dto.getBorrowerName());
        borrow.setBook(book);
        borrow.setStatus(BorrowStatus.BORROWED);

        bookRepository.save(book);
        Borrow savedBorrow = borrowRepository.save(borrow);
        //int x=10/0;(use to check the transactional working or not)

        return mapToDto(savedBorrow);
    }
    @Transactional
    public BorrowResponseDto returnBook(Long borrowId){
        Borrow borrow= borrowRepository.findById(borrowId).orElseThrow(()-> new BorrowNotFoundException("Book Record not found with id "+ borrowId));

        if(borrow.getStatus()==BorrowStatus.RETURNED){
            throw new AlreadyReturnException("Book Already returned");
        }
        Book book = borrow.getBook();
        book.setAvailableQuant(book.getAvailableQuant()+1);
        borrow.setReturnDate(LocalDate.now());
        borrow.setStatus(BorrowStatus.RETURNED);
        bookRepository.save(book);
        Borrow savedBorrow = borrowRepository.save(borrow);
        return mapToDto(savedBorrow);

    }
        private BorrowResponseDto mapToDto(Borrow borrow){
            BorrowResponseDto dto= new BorrowResponseDto();

            dto.setBookId(borrow.getBook().getId());
            dto.setBorrowerName(borrow.getBorrowerName());
            dto.setBorrowDate(borrow.getBorrowDate());
            dto.setReturnDate(borrow.getReturnDate());
            dto.setDueDate(borrow.getDueDate());
            dto.setBorrowId(borrow.getId());
            dto.setStatus(borrow.getStatus());
            dto.setBookTitle(borrow.getBook().getTitle());

            return dto;
        }
}
