package com.screencap.dictionary.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationBody {
    @Size(min = 5, message = "username password must be of length at least 5")
    private String username;

    @Size(min = 6, message = "password must be of length at least 6")
    @NotNull
    private String password;

    @Email(message = "please provide a valid email address")
    private String email;


    public RegistrationBody() {

    }

    public RegistrationBody(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public RegistrationBody(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
