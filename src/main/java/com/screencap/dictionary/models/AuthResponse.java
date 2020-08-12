package com.screencap.dictionary.models;

public class AuthResponse {
    private final String jwtToken;


    public AuthResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return this.jwtToken;
    }
}
