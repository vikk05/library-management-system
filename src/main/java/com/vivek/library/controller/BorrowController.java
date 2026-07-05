package com.vivek.library.controller;

import com.vivek.library.dto.BorrowRequestDto;
import com.vivek.library.dto.BorrowResponseDto;
import com.vivek.library.service.BorrowService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService){
        this.borrowService=borrowService;
    }

    @PostMapping("/{bookId}")
    public BorrowResponseDto borrowBook(@PathVariable Long bookId, @RequestBody BorrowRequestDto dto){
        return borrowService.borrowBook(bookId, dto);
    }

    @PutMapping("/{borrowId}/return")
    public BorrowResponseDto returnBook(@PathVariable Long borrowId){
        return borrowService.returnBook(borrowId);
    }

}
