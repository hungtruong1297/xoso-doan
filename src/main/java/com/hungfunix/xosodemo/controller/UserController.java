package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.models.MessageResponse;
import com.hungfunix.xosodemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getActiveUsers() {
        return userService.findUserByStatus(1); // 1 equals to Status Active
    }

    @GetMapping("/searchByMail/{userMail}")
    public List<User> getUsersByMail(@PathVariable String userMail) {
        return userService.findAllByMailContains(userMail);
    }

    @GetMapping("/searchByPhone/{phone}")
    public List<User> getUsersByPhone(@PathVariable String phone) {
        return userService.findAllByPhoneContains(phone);
    }


    @DeleteMapping("/{userMail}")
    public ResponseEntity<?> disableUserByEmail(@PathVariable(value="userMail") String userMail) {
        userService.disableUserByEmail(userMail);
        return ResponseEntity.ok(new MessageResponse("Disabled."));
    }
}
