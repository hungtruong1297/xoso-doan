package com.hungfunix.xosodemo.security;

import com.hungfunix.xosodemo.filters.JwtRequestFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Random;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Config DaoAuthenticationProvider
    // that uses Beans
    // passwordEncoder() and userDetailsService()
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http
//                .httpBasic().and()
//                .authorizeRequests()
//                .antMatchers("/").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/api/**").hasAnyAuthority("USER")
//                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .logout().permitAll()
//        ;
        // Source: https://www.codejava.net/frameworks/spring-boot/spring-boot-security-role-based-authorization-tutorial
        // Plus line: httpBasic().and()
        // Plus line: http.csrf().disable();
        // Remove Cascade ALL in User

        http
                .authorizeRequests()
                    .antMatchers("/api/results/**").permitAll()
                    .antMatchers("/api/searchHistory/**").permitAll()
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .antMatchers("/authenticate").permitAll()
                    .antMatchers("/api/register").permitAll()
                    .antMatchers("/api/forgot").permitAll()
                    .antMatchers("/api/login").permitAll()
                    .antMatchers("/api/admin").hasAuthority("ADMIN")
                    .antMatchers("/api/users").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Tell spring security don't create session (Step 1)
        // Step 2:
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Make sure jwtRequestFilter is called before UsernamePassword... called

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
