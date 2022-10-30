package com.kelinci.mainapp.api;

import java.util.Objects;

public class FirstUtilClass {

    public FirstUtilClass() {
    }

    public String generateMailCode() {
        int random = (int) (1000000 * Math.random());
        return Integer.toString(random);
    }

    public boolean areEmailsEndMailCodesTheSame(ConfirmationRequest confirmation, OurUser userToBeChecked) {
        return Objects.equals(confirmation.getMail(), userToBeChecked.getMail()) && Objects.equals(confirmation.getMailCode(), userToBeChecked.getMailCode());
    }
}
