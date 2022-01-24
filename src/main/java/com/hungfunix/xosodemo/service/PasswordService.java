package com.hungfunix.xosodemo.service;

import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.models.MessageResponse;
import com.hungfunix.xosodemo.repository.UserRepository;
import com.hungfunix.xosodemo.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    @Autowired
    PasswordUtil passwordUtil;

    @Autowired
    BCryptPasswordEncoder encoder;

    public ResponseEntity<?> forgotPassword(User userInput) {
        User user = userRepository.findUserByMail(userInput.getMail());
        if (user == null) {
            return new ResponseEntity<>(new MessageResponse("User not found"), HttpStatus.I_AM_A_TEAPOT);
        }
        String newPassword = passwordUtil.generateRandomPassword();

        // Passing "new random password" to reset-password.html
        user.setPassword(newPassword);
        mailService.sendResetPasswordEmail(user);

        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("New password has been sent to your email."));

    }


}
