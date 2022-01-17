package com.hungfunix.xosodemo.filters;


import com.hungfunix.xosodemo.security.MyUserDetails;
import com.hungfunix.xosodemo.security.UserDetailsServiceImpl;
import com.hungfunix.xosodemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Intercept every request,
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // FilterChain examine the request for JWT in header
        // If valid -> save in Security Context
        final String authorizationHeader = request.getHeader("Authorization"); // "Authorization" in Header in Postman when Authorization is selected.

        String username = null;
        String jwt = null;

        if (authorizationHeader!= null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // this will remove "Bearer " from authorizationHeader
            username = jwtUtil.extractUsername(jwt); // This will get 'username' from JWT
        }

        if (username!= null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request,response);
        // Then go to SecurityConfigurer and tell Security to use this FilterChain

        // Source: https://www.youtube.com/watch?v=X80nJ5T7YpE&ab_channel=JavaBrains at 35:09

    }
}
