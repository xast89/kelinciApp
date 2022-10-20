package com.kelinci.mainapp.api;

public class RegisteredUserResponse {
    private String mail;

    public RegisteredUserResponse(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}
