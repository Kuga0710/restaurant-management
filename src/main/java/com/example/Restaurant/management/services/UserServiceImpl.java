package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.UserDto;
import com.example.Restaurant.management.entities.User;
import com.example.Restaurant.management.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);

//        if (userPage.isEmpty()) {
//            throw new ResourceNotFoundException(ValidationMessages.NO_RECORDS_FOUND);
//        }

        return userPage.getContent().stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    BeanUtils.copyProperties(user, userDto);
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        // Find the existing user by ID
        User user = userRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Invalid User ID"));

        BeanUtils.copyProperties(userDto, user);
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
