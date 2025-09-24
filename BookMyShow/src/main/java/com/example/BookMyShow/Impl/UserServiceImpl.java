package com.example.BookMyShow.Impl;

import com.example.BookMyShow.Dto.UserRequestDto;
import com.example.BookMyShow.Dto.UserResponseDto;
import com.example.BookMyShow.Entity.User;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.Service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserResponseDto toDto(User u) {
        return UserResponseDto.builder()
                .id(u.getId())
                .fullName(u.getFullName())
                .email(u.getEmail())
                .phone(u.getPhone())
                .build();
    }

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        // Basic duplicate email check (optional)
        userRepository.findByEmail(dto.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Email already exists");
        });

        User user = User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();

        User saved = userRepository.save(user);
        return toDto(saved);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        if (dto.getFullName() != null) user.setFullName(dto.getFullName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getPhone() != null) user.setPhone(dto.getPhone());
        User saved = userRepository.save(user);
        return toDto(saved);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return toDto(u);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
}

