package com.spring.learn.controller;

import com.spring.learn.model.request.UserRequestDto;
import com.spring.learn.model.response.UserResponseDto;
import com.spring.learn.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto saveUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.saveUser(userRequestDto);
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        return userService.deleteUserById(id);
    }
}
