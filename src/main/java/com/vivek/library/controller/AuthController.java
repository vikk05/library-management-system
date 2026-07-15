package com.vivek.library.controller;

import com.vivek.library.dto.RegisterRequestDto;
import com.vivek.library.dto.RegisterResponseDto;
import com.vivek.library.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody RegisterRequestDto dto){
        RegisterResponseDto response= authService.register(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
