package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.models.MessageResponse;
import com.hungfunix.xosodemo.repository.UserRepository;
import com.hungfunix.xosodemo.service.MailService;
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
public class ForgotPasswordController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    @Autowired
    PasswordUtil passUtil;

    @Autowired
    BCryptPasswordEncoder encoder;

    @PostMapping("")
    public ResponseEntity<?> forgotPassword(@RequestBody User userInput) {

        User user = userRepository.findUserByMail(userInput.getMail());

        if (user==null) {
            return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.I_AM_A_TEAPOT);
        }

        String newPassword = passUtil.generateRandomPassword();
        user.setPassword(newPassword);
        mailService.sendActivationEmail(user);

        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("New password has been sent to your email."));
    }

}
