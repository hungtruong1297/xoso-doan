package com.hungfunix.xosodemo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
public class AuthenticationRequest {

    private String mail;
    private String password;

}
