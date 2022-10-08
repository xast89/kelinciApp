package com.kelinci.mainapp.api;

public class Register {
    private String mail;

    public Register(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
