package com.hungfunix.xosodemo.models;

public class AuthenticationResponse {
    public final String token;
    public final String username;

    public AuthenticationResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }
}
