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
        }

        else {
            return new ResponseEntity<>("User not added. Duplicated user.", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("")
    public String adminPage(){
        return "From /admin";
    }
}
