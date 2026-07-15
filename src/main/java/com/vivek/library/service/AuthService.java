package com.vivek.library.service;

import com.vivek.library.dto.RegisterRequestDto;
import com.vivek.library.dto.RegisterResponseDto;
import com.vivek.library.entity.User;
import com.vivek.library.enums.Role;
import com.vivek.library.exception.EmailAlreadyExistsException;
import com.vivek.library.mapper.UserMapper;
import com.vivek.library.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public RegisterResponseDto register(RegisterRequestDto dto){

        dto.setEmail(dto.getEmail().trim().toLowerCase());
        dto.setName(dto.getName().trim());
        dto.setAddress(dto.getAddress().trim());
        dto.setContactNumber(dto.getContactNumber().trim());

        Optional<User> existingUser= userRepository.findByEmail(dto.getEmail());

        if(existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user= userMapper.mapToEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);

        User savedUser= userRepository.save(user);

        RegisterResponseDto response= userMapper.mapToDto(savedUser);
        response.setMessage("User Registered Successfully");

        return response;
    }
}
