package com.vivek.library.repository;

import com.vivek.library.entity.Borrow;
import com.vivek.library.enums.BorrowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow,Long> {

    Optional<Borrow> findByBook_IdAndBorrowerNameAndStatus(Long bookId, String borrowerName, BorrowStatus status);
}
