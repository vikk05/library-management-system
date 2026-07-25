package com.vivek.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class BorrowRequestDto {


    @Schema(
            description = "ID of the Book to Borrow",
            example = "1"
    )
    @NotNull(message="book id is required")
    private Long bookId;
    public BorrowRequestDto(){

    }

    public BorrowRequestDto(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
