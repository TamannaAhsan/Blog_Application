package com.example.blog_application.services;

import com.example.blog_application.payloads.UserDto;

import java.util.Set;


public interface UserService {

    UserDto registerNewUser (UserDto userDto);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById (Integer userId);
    Set<UserDto> getALLUsers();
    void deleteUser(Integer userId);
}
