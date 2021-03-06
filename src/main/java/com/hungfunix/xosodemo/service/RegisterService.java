package com.hungfunix.xosodemo.service;

import com.hungfunix.xosodemo.model.Role;
import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    public ResponseEntity<String> register( User user) {
        // Check duplicated User
        if (userRepository.findUserByMail(user.getMail())!=null) {
            return new ResponseEntity<>("User not added. Duplicated user.", HttpStatus.CONFLICT);
        }

        user.setPassword(encoder.encode(user.getPassword()));

        // Default Register from Register page will have default role "USER"
        Set<Role> roles = new HashSet<>();

        // Create Role instance: USER
        Role userRole = new Role();
        userRole.setRoleId(2);
        userRole.setRoleName("USER");

        roles.add(userRole);
        user.setRoles(roles);

        // Set Status to Active
        user.setStatus(1);

        System.out.println(user.toString());


        userRepository.save(user);
        System.out.println("User added.");
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
