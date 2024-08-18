package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.UserDto;
import com.example.Restaurant.management.entities.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User createUser(UserDto userDto);
    List<UserDto> getAllUsers(Pageable pageable);
    User updateUser(Long id, UserDto userDto);
    boolean deleteUser(Long id);
}
