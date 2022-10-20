package com.kelinci.mainapp.api;

public class RegisteredUserResponse {
    private final String mail;

    public RegisteredUserResponse(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}
