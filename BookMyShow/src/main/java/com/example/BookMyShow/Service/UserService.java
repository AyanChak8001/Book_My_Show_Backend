package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.UserRequestDto;
import com.example.BookMyShow.Dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);
    UserResponseDto updateUser(Long id, UserRequestDto dto);
    void deleteUser(Long id);
    UserResponseDto getUserById(Long id);
    List<UserResponseDto> getAllUsers();
}
