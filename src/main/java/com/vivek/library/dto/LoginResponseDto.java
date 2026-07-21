package com.vivek.library.dto;

import com.vivek.library.enums.Role;

public class LoginResponseDto {
    private String email;
    private String token;
    private Role role;

    public LoginResponseDto(){

    }
    public LoginResponseDto(String email, String token, Role role) {
        this.email = email;
        this.token = token;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
