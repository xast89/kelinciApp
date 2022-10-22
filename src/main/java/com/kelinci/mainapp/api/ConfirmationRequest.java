package com.kelinci.mainapp.api;

public class ConfirmationRequest {

    private final String mail;
    private final int magicNumber;
    private final String mailCode;

    public ConfirmationRequest(String mail, int magicNumber, String mailCode) {
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
