package com.vivek.library.repository;

import com.vivek.library.entity.Book;
import com.vivek.library.entity.BorrowRecord;
import com.vivek.library.entity.User;
import com.vivek.library.enums.BorrowStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowRecord,Long> {
    boolean existsByUserAndBookAndStatus(
            User user,
            Book book,
            BorrowStatus status
    );

    Page<BorrowRecord> findByUser(User user, Pageable pageable);
    long countByStatus(BorrowStatus status);
    long countByStatusAndDueDateBefore(
            BorrowStatus status,
            LocalDate dueDate
    );

    Page<BorrowRecord> findByStatusAndDueDateBefore(BorrowStatus status,LocalDate dueDate,Pageable pageable);

}
