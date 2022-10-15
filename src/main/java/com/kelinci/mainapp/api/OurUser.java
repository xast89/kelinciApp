package com.kelinci.mainapp.api;

import java.util.Objects;

public class OurUser {
    private String mail;
    private String mailCode;
    private boolean isConfirmed;

    public OurUser(String mail, String mailCode, boolean isConfirmed) {
        this.mail = mail;
        this.mailCode = mailCode;
        this.isConfirmed = isConfirmed;
    }

    public OurUser(String mail) {
        this.mail = mail;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OurUser ourUser = (OurUser) o;
        return isConfirmed == ourUser.isConfirmed && Objects.equals(mail, ourUser.mail) && Objects.equals(mailCode, ourUser.mailCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, mailCode, isConfirmed);
    }

    @Override
    public String toString() {
        return "OurUser{" +
                "mail='" + mail + '\'' +
                ", mailCode='" + mailCode + '\'' +
                ", isConfirmed=" + isConfirmed +
                '}';
    }

}
