package com.spring.learn.service;

import com.spring.learn.domain.User;
import com.spring.learn.model.request.UserRequestDto;
import com.spring.learn.model.response.UserResponseDto;
import com.spring.learn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.username());
        user.setFullName(userRequestDto.fullName());
        userRepository.save(user);
        return mapToUserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        users.forEach(user -> userResponseDtos.add(mapToUserResponseDto(user)));

        return userResponseDtos;
    }

    @Override
    public String deleteUserById(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }else throw new RuntimeException("User not found");
        return "User was disabled";
    }

    private UserResponseDto mapToUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getFullName(),user.getDeleted());
    }
}
