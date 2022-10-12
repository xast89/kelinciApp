package com.kelinci.mainapp.api;

public class RegisterRequest {
    private String mail;
    private int magicNumber;


    public RegisterRequest(String mail, int magicNumber, String mailCode) {
        this.mail = mail;
        this.magicNumber = magicNumber;

    }

    public String getMail() {
        return mail;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

}
