package com.vivek.library.dto;

import com.vivek.library.enums.Role;

public class RegisterResponseDto {
    private Long id;
    private String name;
    private String email;
    private String message;
    private Role role;
    public RegisterResponseDto(){

    }

    public RegisterResponseDto(Long id, String name, String email, String message, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.message = message;
        this.role=role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRole(Role role){
        this.role=role;
    }
    public Role getRole(){
        return role;
    }
}
