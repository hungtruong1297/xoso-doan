package com.hungfunix.xosodemo.service;

import com.hungfunix.xosodemo.dto.UserDTO;
import com.hungfunix.xosodemo.model.Role;
import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.models.MessageResponse;
import com.hungfunix.xosodemo.repository.RoleRepository;
import com.hungfunix.xosodemo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private String generateRandomPassword;

    public ResponseEntity<?> addUserByAdmin(User user) {

        // If user doesn't exist, then add user
        if (this.userRepository.findUserByMail(user.getMail()) == null) {

            String pwd = user.getPassword();
            String encryptPwd = passwordEncoder.encode(pwd);
            user.setPassword(encryptPwd);
            // Set status to active
            user.setStatus(1);

            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("User added."));
        } else {
            return new ResponseEntity<>("User not added. Duplicated user.", HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> resetPassword(User userInput) {
        User user = userRepository.findUserById(userInput.getId());

        String newPassword = generateRandomPassword;
        String encryptedPWD = passwordEncoder.encode(newPassword);

        user.setPassword(encryptedPWD);
        userRepository.save(user);

        System.out.println(newPassword);
        return ResponseEntity.ok(new MessageResponse(newPassword));
    }
}
