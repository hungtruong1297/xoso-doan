package com.hungfunix.xosodemo.models;

public class AuthenticationResponse {
    public final String token;
    public final String username;
    public final String role;

    public AuthenticationResponse(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }
}
