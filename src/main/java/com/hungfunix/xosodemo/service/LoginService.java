package com.hungfunix.xosodemo.service;

import com.hungfunix.xosodemo.model.Role;
import com.hungfunix.xosodemo.model.User;
import com.hungfunix.xosodemo.models.AuthenticationResponse;
import com.hungfunix.xosodemo.repository.UserRepository;
import com.hungfunix.xosodemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;


    // Source: https://www.youtube.com/watch?v=X80nJ5T7YpE&ab_channel=JavaBrains
    // JWT + SpringBoot from Scratch

    // AuthenticationRequest is simply the Payload of the Body
    public ResponseEntity<?> createAuthenticationToken(User request) throws Exception {

        // The request.getPassword() here doesn't need BCrypt, because authenticationManager automatically use BCrypt itself.
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getMail(),request.getPassword())
            );
        } catch(BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getMail());

        String token = jwtTokenUtil.generateToken(userDetails);
        String username = jwtTokenUtil.extractUsername(token);

        User user = userRepository.findUserByMail(request.getMail());
        Set<Role> roles = user.getRoles();
        String roleName = "";

        for (Role role : roles) {
            roleName = role.getRoleName();
        }
        return ResponseEntity.ok(new AuthenticationResponse(token, username, roleName));

    }



}
