package com.vivek.library.controller;

import com.vivek.library.dto.BorrowRequestDto;
import com.vivek.library.dto.BorrowResponseDto;
import com.vivek.library.service.BorrowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Borrow Management",
        description = "APIs for borrowing and returning books"
)

@RestController
@RequestMapping("/borrows")

public class BorrowController {
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService){
        this.borrowService=borrowService;
    }


    @Operation(
            summary = "Borrow a book",
            description = "Allows a user to borrow a book if it is available and the user has not already borrowed it."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Book borrowed successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Book is already borrowed or out of stock"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found"
            )
    })
    @PostMapping("/{bookId}")
    public BorrowResponseDto borrowBook(@PathVariable Long bookId, @RequestBody BorrowRequestDto dto){
        return borrowService.borrowBook(bookId, dto);
    }

    @Operation(
            summary = "Return a book",
            description = "Returns a borrowed book, updates its status to RETURNED, and increases the available quantity."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Book returned successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Book has already been returned"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Borrow record not found"
            )
    })
    @PutMapping("/{borrowId}/return")
    public BorrowResponseDto returnBook(@Parameter(
            description = "Borrow transaction ID",
            example = "5"
    )@PathVariable Long borrowId){
        return borrowService.returnBook(borrowId);
    }

}
