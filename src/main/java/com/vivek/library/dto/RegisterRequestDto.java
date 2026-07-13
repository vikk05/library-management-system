package com.vivek.library.dto;

import jakarta.validation.constraints.*;

public class RegisterRequestDto {
    @NotBlank(message="Name is required")
    @Size(min=3,max=50)
    private String name;

    @NotBlank(message="Email is required")
    @Email(message="Invalid Email format")
    private String email;

    @NotBlank(message="Password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
            message = "Password must contain at least 8 characters, one uppercase, one lowercase, one digit and one special character."
    )
    private String password;

    @NotBlank(message = "Contact number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Contact number must contain exactly 10 digits"
    )
    private String contactNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255)
    private String address;
    public RegisterRequestDto(){

    }

    public RegisterRequestDto(String name, String email, String password, String contactNumber, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
