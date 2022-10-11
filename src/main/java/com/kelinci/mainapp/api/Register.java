package com.kelinci.mainapp.api;

public class Register {
    private String mail;
    private int magicNumber;

    public Register(String mail, int magicNumber) {
        this.mail = mail;
        this.magicNumber = magicNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }
}
