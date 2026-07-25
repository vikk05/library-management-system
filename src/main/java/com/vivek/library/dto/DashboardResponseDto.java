package com.vivek.library.dto;

public class DashboardResponseDto {
    private long totalBooks;

    private long availableBooks;

    private long borrowedBooks;

    private long totalUsers;

    private long activeBorrows;

    private long overdueBooks;

    public DashboardResponseDto(){

    }
    public DashboardResponseDto(long totalBooks, long availableBooks, long borrowedBooks, long totalUsers, long activeBorrows, long overdueBooks) {
        this.totalBooks = totalBooks;
        this.availableBooks = availableBooks;
        this.borrowedBooks = borrowedBooks;
        this.totalUsers = totalUsers;
        this.activeBorrows = activeBorrows;
        this.overdueBooks = overdueBooks;
    }

    public long getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(long totalBooks) {
        this.totalBooks = totalBooks;
    }

    public long getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(long availableBooks) {
        this.availableBooks = availableBooks;
    }

    public long getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(long borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getActiveBorrows() {
        return activeBorrows;
    }

    public void setActiveBorrows(long activeBorrows) {
        this.activeBorrows = activeBorrows;
    }

    public long getOverdueBooks() {
        return overdueBooks;
    }

    public void setOverdueBooks(long overdueBooks) {
        this.overdueBooks = overdueBooks;
    }
}
