package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.models.MessageResponse;
import com.hungfunix.xosodemo.repository.RoleRepository;
import com.hungfunix.xosodemo.repository.UserRepository;
import com.hungfunix.xosodemo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private String generateRandomPassword;

    @Autowired
    private AdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<?> addUserByAdmin(@RequestBody User user) {
        return adminService.addUserByAdmin(user);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody User userInput) {
        return adminService.resetPassword(userInput);
    }
}
