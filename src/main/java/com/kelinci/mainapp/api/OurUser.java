package com.kelinci.mainapp.api;

public class OurUser {
    private String mail;
    private String mailCode;
    private boolean isConfirmed;

    public OurUser(String mail, String mailCode, boolean isConfirmed) {
        this.mail = mail;
        this.mailCode = mailCode;
        this.isConfirmed = isConfirmed;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMailCode() {
        return mailCode;
    }

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}
