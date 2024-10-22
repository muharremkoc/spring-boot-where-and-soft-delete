package com.spring.learn.service;

import com.spring.learn.model.request.UserRequestDto;
import com.spring.learn.model.response.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    String deleteUserById(String id);

}
