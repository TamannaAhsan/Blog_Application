package com.example.blog_application.services.impl;

import com.example.blog_application.config.AppConstant;
import com.example.blog_application.entities.Role;
import com.example.blog_application.entities.User;
import com.example.blog_application.exceptions.ResourceNotFoundException;
import com.example.blog_application.payloads.UserDto;
import com.example.blog_application.repositories.RoleRepo;
import com.example.blog_application.repositories.UserRepo;
import com.example.blog_application.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);

        // encoded the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // roles
        Role role = this.roleRepo.findById(AppConstant.NORMAL_USER).get();
        user.getRoles().add(role);
        User newUser = this.userRepo.save(user);
        return this.modelMapper.map(newUser, UserDto.class);

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Role role = this.roleRepo.findById(AppConstant.NORMAL_USER).get();
        user.getRoles().add(role);
        User savedUser = this.userRepo.save(user);
        return this.userTodto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updateUser = this.userRepo.save(user);
        UserDto userDto1 = this.userTodto(updateUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id",userId));
        return userTodto(user);
    }

    @Override
    public Set<UserDto> getALLUsers() {

        List<User> users = this.userRepo.findAll();
        Set<UserDto> userDtos= users.stream().map(user -> this.userTodto(user)).collect(Collectors.toSet());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }
    private User dtoToUser(UserDto userDto){

        User user = this.modelMapper.map(userDto,User.class);

        return user;
    }

    private UserDto userTodto (User user){

        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;
    }
}
