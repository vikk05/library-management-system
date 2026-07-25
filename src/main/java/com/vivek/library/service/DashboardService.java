package com.vivek.library.service;

import com.vivek.library.dto.DashboardResponseDto;
import com.vivek.library.enums.BorrowStatus;
import com.vivek.library.repository.BookRepository;
import com.vivek.library.repository.BorrowRepository;
import com.vivek.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {
    private final BookRepository bookRepository;
    private final BorrowRepository borrowRepository;
    private final UserRepository userRepository;

    public DashboardService(BookRepository bookRepository, BorrowRepository borrowRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
        this.userRepository = userRepository;
    }
    public DashboardResponseDto getDashboardStatistics() {
        long totalBooks = bookRepository.count();

        long totalUsers = userRepository.count();

        long activeBorrows = borrowRepository.countByStatus(BorrowStatus.BORROWED);

        long overdueBooks = borrowRepository.countByStatusAndDueDateBefore(BorrowStatus.BORROWED, LocalDate.now());

        long availableBooks = bookRepository.getTotalAvailableBooks();

        DashboardResponseDto response = new DashboardResponseDto();

        response.setTotalBooks(totalBooks);
        response.setTotalUsers(totalUsers);
        response.setActiveBorrows(activeBorrows);
        response.setOverdueBooks(overdueBooks);
        response.setAvailableBooks(availableBooks);

        return response;
    }
}
