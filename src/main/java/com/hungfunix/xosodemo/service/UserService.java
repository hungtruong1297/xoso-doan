package com.hungfunix.xosodemo.service;

import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    public List<User> findUserByStatus(int statusId) {
        return userRepository.findUserByStatus(statusId);
    }

    public List<User> findAllByMailContains(String mail) {
        return userRepository.findAllByMailContains(mail);
    }

    public List<User> findAllByPhoneContains(String phone) {
        return userRepository.findAllByPhoneContains(phone);
    }

    public User findUserByMail(String mail) {
        return userRepository.findUserByMail(mail);
    }

    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void disableUserByEmail(String userMail) {
        User user = userRepository.findUserByMail(userMail);
        if (user == null) {
            throw new UsernameNotFoundException(userMail);
        }
        user.setStatus(0);
        userRepository.save(user);
    }




}
