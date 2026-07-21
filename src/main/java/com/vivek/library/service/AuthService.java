package com.vivek.library.service;

import com.vivek.library.dto.LoginRequestDto;
import com.vivek.library.dto.LoginResponseDto;
import com.vivek.library.dto.RegisterRequestDto;
import com.vivek.library.dto.RegisterResponseDto;
import com.vivek.library.entity.User;
import com.vivek.library.enums.Role;
import com.vivek.library.exception.EmailAlreadyExistsException;
import com.vivek.library.mapper.UserMapper;
import com.vivek.library.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.vivek.library.service.JwtService;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,JwtService jwtService ,PasswordEncoder passwordEncoder, UserMapper userMapper,AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
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

    public LoginResponseDto login(LoginRequestDto dto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword()));

        User user=(User) authentication.getPrincipal();
        String token=jwtService.generateToken(user);

        return new LoginResponseDto(user.getEmail(),token,user.getRole());
    }
}
