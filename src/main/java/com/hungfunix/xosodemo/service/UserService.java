package com.hungfunix.xosodemo.service;

import com.hungfunix.xosodemo.model.Role;
import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.models.MessageResponse;
import com.hungfunix.xosodemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    public ResponseEntity<?> makeAdmin(int userId) {
        User user = userRepository.findUserById(userId);
        if (user != null) {
            Role admin = new Role();
            admin.setRoleId(1);
            admin.setRoleName("ADMIN");
            Set<Role> roles = new HashSet<>();
            roles.add(admin);
            user.setRoles(roles);
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("Admin granted."));
        }
        return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
    }
}
