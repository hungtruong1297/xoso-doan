package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure/rest")
public class AdminController {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/admin/add")
    public String addUserByAdmin(@RequestBody User user) {

//        String pwd = user.getPassword();
//        String encryptPwd = passwordEncoder.encode(pwd);
//        user.setPassword(encryptPwd);

        userRepository.save(user);
        return "User added...";
    }

    @GetMapping("")
    public String adminPage(){
        return "Hi";
    }
}
