package com.hungfunix.xosodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableWebSecurity
@CrossOrigin(origins="*")
public class XosoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(XosoDemoApplication.class, args);
    }

}
