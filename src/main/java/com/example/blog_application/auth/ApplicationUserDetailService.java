package com.example.blog_application.auth;

import com.example.blog_application.entities.User;
import com.example.blog_application.exceptions.ResourceNotFoundAuth;
import com.example.blog_application.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loading user from database from username
        User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundAuth("User", "email", username));
        return user;
    }
}
