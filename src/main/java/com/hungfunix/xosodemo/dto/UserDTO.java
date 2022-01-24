package com.hungfunix.xosodemo.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class UserDTO {

    private String mail;
    private String password;

    private String phone;
    private String name;
    private int roleId;
    private int status;

    // return userService.getAll( ) UserDTOs
}
