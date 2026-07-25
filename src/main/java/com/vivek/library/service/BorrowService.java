package com.vivek.library.service;

import com.vivek.library.dto.AdminBorrowResponseDto;
import com.vivek.library.dto.BorrowRequestDto;
import com.vivek.library.dto.BorrowResponseDto;
import com.vivek.library.entity.Book;
import com.vivek.library.entity.BorrowRecord;
import com.vivek.library.entity.User;
import com.vivek.library.enums.BorrowStatus;
import com.vivek.library.enums.Role;
import com.vivek.library.exception.*;
import com.vivek.library.repository.BookRepository;
import com.vivek.library.repository.BorrowRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;


    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BorrowResponseDto borrowBook(User user, BorrowRequestDto dto) {
        Book book = bookRepository.findById(dto.getBookId()).orElseThrow(() -> new BookNotFoundException("Book Not Found by id " + dto.getBookId()));
        if (borrowRepository.existsByUserAndBookAndStatus(
                user,
                book,
                BorrowStatus.BORROWED))
        {
            throw new DuplicateBorrowException("You have already borrowed this book. Please return it before borrowing again");
    }

        if(book.getAvailableQuant()<=0){
            throw new BookOutOfStockException("Book is Currently Out of Stock");
        }
        book.setAvailableQuant(book.getAvailableQuant()-1);
        BorrowRecord borrowRecord =new BorrowRecord();

        LocalDate today= LocalDate.now();
        borrowRecord.setUser(user);
        borrowRecord.setBorrowDate(today);
        borrowRecord.setDueDate(today.plusDays(14));
        borrowRecord.setBook(book);
        borrowRecord.setStatus(BorrowStatus.BORROWED);

        bookRepository.save(book);
        BorrowRecord savedBorrowRecord = borrowRepository.save(borrowRecord);
        //int x=10/0;(use to check the transactional working or not)

        return mapToDto(savedBorrowRecord);
    }
    @Transactional
    public BorrowResponseDto returnBook(User user,Long borrowId){
        BorrowRecord borrowRecord = borrowRepository.findById(borrowId).orElseThrow(()-> new BorrowNotFoundException("Book Record not found with id "+ borrowId));

        if(user.getRole() != Role.ADMIN && !borrowRecord.getUser().getId().equals(user.getId())){
            throw new UnauthorizedException("Not Authorized to return this book");

        }
        if(borrowRecord.getStatus()==BorrowStatus.RETURNED){
            throw new AlreadyReturnException("Book Already returned");
        }
        Book book = borrowRecord.getBook();
        book.setAvailableQuant(book.getAvailableQuant()+1);
        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecord.setStatus(BorrowStatus.RETURNED);
        bookRepository.save(book);
        BorrowRecord savedBorrowRecord = borrowRepository.save(borrowRecord);
        return mapToDto(savedBorrowRecord);

    }
    public Page<BorrowResponseDto> getMyBorrowHistory(User user, Pageable pageable) {
        Page <BorrowRecord> borrowRecords = borrowRepository.findByUser(user,pageable);

        return borrowRecords.map(this::mapToDto);

    }

    public Page<AdminBorrowResponseDto> getAllBorrowHistory(Pageable pageable){
        Page <BorrowRecord> borrowRecords = borrowRepository.findAll(pageable);

        return borrowRecords.map(this::mapToAdminDto);
    }
        private BorrowResponseDto mapToDto(BorrowRecord borrowRecord){
            BorrowResponseDto dto= new BorrowResponseDto();

            dto.setBookId(borrowRecord.getBook().getId());
            dto.setBorrowDate(borrowRecord.getBorrowDate());
            dto.setReturnDate(borrowRecord.getReturnDate());
            dto.setDueDate(borrowRecord.getDueDate());
            dto.setBorrowId(borrowRecord.getId());
            dto.setStatus(borrowRecord.getStatus());
            dto.setBookTitle(borrowRecord.getBook().getTitle());

            return dto;
        }
    private AdminBorrowResponseDto mapToAdminDto(BorrowRecord borrowRecord){
        AdminBorrowResponseDto response= new AdminBorrowResponseDto();

        response.setUserId(borrowRecord.getUser().getId());
        response.setBookId(borrowRecord.getBook().getId());
        response.setBorrowDate(borrowRecord.getBorrowDate());
        response.setReturnDate(borrowRecord.getReturnDate());
        response.setDueDate(borrowRecord.getDueDate());
        response.setBorrowId(borrowRecord.getId());
        response.setStatus(borrowRecord.getStatus());
        response.setBookTitle(borrowRecord.getBook().getTitle());
        response.setUsername(borrowRecord.getUser().getUsername());
        response.setEmail(borrowRecord.getUser().getEmail());

        return response;

    }
}
