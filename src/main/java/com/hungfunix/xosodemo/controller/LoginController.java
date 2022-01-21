package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User request) throws Exception {
        return loginService.createAuthenticationToken(request);
    }
}
