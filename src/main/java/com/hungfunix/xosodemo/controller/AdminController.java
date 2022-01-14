package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.repository.RoleRepository;
import com.hungfunix.xosodemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @PostMapping("/add")
    public ResponseEntity<String> addUserByAdmin(@RequestBody User user) {

        if (this.userRepository.findUserByMail(user.getMail()) == null) {

            String pwd = user.getPassword();
            String encryptPwd = passwordEncoder.encode(pwd);
            user.setPassword(encryptPwd);

            userRepository.save(user);
            System.out.println("User added.");
            return new ResponseEntity<>("User added.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not added. Duplicated user.", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("")
    public String adminPage() {
        return "From /admin";
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody User userInput) {
        User user = userRepository.findUserById(userInput.getId());

        String newPassword = generateRandomPassword();
        String encryptedPWD = passwordEncoder.encode(newPassword);

        user.setPassword(encryptedPWD);
        userRepository.save(user);

        System.out.println(newPassword);
        return new ResponseEntity<>(newPassword, HttpStatus.OK);
        // TODO: Still in Dev
    }


    public String generateRandomPassword() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }




}
