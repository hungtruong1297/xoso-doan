package com.hungfunix.xosodemo.controller;

import com.hungfunix.xosodemo.model.Result;
import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public List<User> getActiveUsers() {
        // Status id: 1 -> Active
        // Status id: 0 -> Inactive
        return userRepository.findUserByStatus(1);
    }

    @GetMapping("/searchByMail/{userMail}")
    public List<User> getUsersByMail(@PathVariable String userMail) {
        return userRepository.findAllByMailContains(userMail);
    }

    @GetMapping("/searchByPhone/{phone}")
    public List<User> getUsersByPhone(@PathVariable String phone) {
        return userRepository.findAllByPhoneContains(phone);
    }


    @DeleteMapping("/{userMail}")
    public ResponseEntity<User> disableUserByEmail(@PathVariable(value="userMail") String userMail) {
        User user = userRepository.findUserByMail(userMail);
        if (user == null) {
            throw new UsernameNotFoundException(userMail);
        }

//        try {
//            userRepository.deleteById(userMail);
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        user.setStatus(0);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }



}
