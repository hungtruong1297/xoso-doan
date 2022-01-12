//package com.hungfunix.xosodemo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//import java.security.SecureRandom;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    //Why "Have bean, haven't Autowired, but this Bean is used to encode my Password"
////    @Bean
////    public BCryptPasswordEncoder encodePWD() {
////        return new BCryptPasswordEncoder();
////    }
//
////    //TODO: (?)
////    @Autowired
////    private UserDetailsService userDetailsService;
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
////    }
//
//    //    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests().anyRequest().permitAll();
////    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication().withUser("admin").password(encodePWD().encode("Password")).roles("ADMIN");
////        auth.inMemoryAuthentication().withUser("user").password(encodePWD().encode("123456")).roles("USER");
////    }
//
//    // Secured for all api
//    //TODO: BIG HIGHLIGHT,
//    // @Bean BCrypt should go with http.csrf().disable()
//    // (csrf: Cross-site request forgery, helps preventing cross-site attacks)
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
//    }
//
//
//    // Secured bases on URL
//////    @Override
//////    protected void configure(HttpSecurity http) throws Exception {
//////        http.csrf().disable();
//////        // URL starts from /rest
//////        http.authorizeRequests().antMatchers("/rest/**").fullyAuthenticated().and().httpBasic();
////    }
//
//    // Secured bases on ROLE
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable();
////        // URL starts from /rest
////        http.authorizeRequests().antMatchers("/rest/**").hasAnyRole("ADMIN").anyRequest().fullyAuthenticated().and().httpBasic();
////    }
//
//
//
//
//
////    @Bean
////    public static NoOpPasswordEncoder passwordEncoder() {
////        return (NoOpPasswordEncoder)NoOpPasswordEncoder.getInstance();
////    }
//
//
//}
//// TODO: Temp disabled