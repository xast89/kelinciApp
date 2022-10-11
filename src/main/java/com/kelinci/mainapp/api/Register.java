package com.kelinci.mainapp.api;

public class Register {
    private String mail;
    private int magicNumber;
    private String mailCode;

    public Register(String mail, int magicNumber, String mailCode) {
        this.mail = mail;
        this.magicNumber = magicNumber;
        this.mailCode = mailCode;
    }

    public String getMail() {
        return mail;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public String getMailCode() {
        return mailCode;
    }
}
