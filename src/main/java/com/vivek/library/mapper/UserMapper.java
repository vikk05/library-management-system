package com.vivek.library.mapper;

import com.vivek.library.dto.RegisterRequestDto;
import com.vivek.library.dto.RegisterResponseDto;
import com.vivek.library.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToEntity(RegisterRequestDto dto){

        User user= new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setContactNo(dto.getContactNumber());
        user.setPassword(dto.getPassword());
        user.setAddress(dto.getAddress());

        return user;

    }
    public RegisterResponseDto mapToDto(User user){
       RegisterResponseDto response= new RegisterResponseDto();

       response.setEmail(user.getEmail());
       response.setName(user.getName());
       response.setRole(user.getRole());
       response.setId(user.getId());
       return response;
    }

}
