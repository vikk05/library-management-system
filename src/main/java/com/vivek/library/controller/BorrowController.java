package com.vivek.library.controller;

import com.vivek.library.dto.AdminBorrowResponseDto;
import com.vivek.library.dto.BorrowRequestDto;
import com.vivek.library.dto.BorrowResponseDto;
import com.vivek.library.entity.User;
import com.vivek.library.service.BorrowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
        name = "Borrow Management",
        description = "APIs for borrowing and returning books"
)

@RestController
@RequestMapping("/borrows")
@SecurityRequirement(name="bearerAuth")
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
    @PostMapping
    public BorrowResponseDto borrowBook( @Parameter(hidden = true)@AuthenticationPrincipal User user, @RequestBody @Valid BorrowRequestDto dto){
        return borrowService.borrowBook(user, dto);
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
    public BorrowResponseDto returnBook(
            @Parameter(hidden = true)
            @AuthenticationPrincipal User user,
            @PathVariable Long borrowId) {

        return borrowService.returnBook(user, borrowId);
    }
    @GetMapping("/me")
    public Page<BorrowResponseDto> getMyBorrowHistory(
            @Parameter(hidden = true)
            @AuthenticationPrincipal User user,@PageableDefault(size = 10, sort = "borrowDate", direction = Sort.Direction.DESC) Pageable pageable) {

        return borrowService.getMyBorrowHistory(user,pageable);
    }

    @GetMapping
    public Page<AdminBorrowResponseDto> getAllBorrowHistory(@PageableDefault(size = 10, sort = "borrowDate", direction = Sort.Direction.DESC) Pageable pageable){
        return borrowService.getAllBorrowHistory(pageable);
    }

}
