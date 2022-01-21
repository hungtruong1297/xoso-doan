package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.repository.UserRepository;
import com.hungfunix.xosodemo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// RestController -> JSON object [  ]
// return // .html
@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) {
        return registerService.register(user);
    }


}
