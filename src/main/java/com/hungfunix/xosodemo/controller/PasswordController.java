package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.models.MessageResponse;
import com.hungfunix.xosodemo.repository.UserRepository;
import com.hungfunix.xosodemo.service.MailService;
import com.hungfunix.xosodemo.service.PasswordService;
import com.hungfunix.xosodemo.service.UserService;
import com.hungfunix.xosodemo.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/forgot")
public class PasswordController {

    @Autowired
    PasswordService passwordService;

    @PostMapping("")
    public ResponseEntity<?> forgotPassword(@RequestBody User userInput) {
        return passwordService.forgotPassword(userInput);
    }

}
