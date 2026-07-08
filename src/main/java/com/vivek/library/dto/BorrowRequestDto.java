package com.vivek.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class BorrowRequestDto {


    @Schema(
            description = "Name of the borrower",
            example = "Vivek"
    )
    private String borrowerName;
    public BorrowRequestDto(){

    }

    public BorrowRequestDto(Long bookId, String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }
}
