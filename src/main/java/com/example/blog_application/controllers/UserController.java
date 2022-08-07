package com.example.blog_application.controllers;

import com.example.blog_application.payloads.ApiResponse;
import com.example.blog_application.payloads.UserDto;
import com.example.blog_application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Add User
    @PostMapping ("/add")
    public ResponseEntity<UserDto> createUser (@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    //Update User
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
        UserDto updateUser = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updateUser);

    }
    //Admin
    //Delete User
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/delete/{userId}")
        public ResponseEntity<ApiResponse> deleteUser( @PathVariable("userId") Integer userId){
           this.userService.deleteUser(userId);
           return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);

    }
    //Get All Users
    @GetMapping("/all")
    public ResponseEntity<Set<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getALLUsers());
    }

    //Get Single User
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
