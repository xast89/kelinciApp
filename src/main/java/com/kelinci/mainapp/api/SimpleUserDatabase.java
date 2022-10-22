package com.kelinci.mainapp.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SimpleUserDatabase {

    private static final  List<OurUser> userDatabase = new ArrayList<>();

    public static List<OurUser> getUserDatabase() {
        return userDatabase;
    }

    public static void addUserToDatabase(OurUser ourUser) {
        userDatabase.add(ourUser);
    }

    public void clearUserDatabase() {
        userDatabase.clear();
    }
    public Optional<OurUser> getUserWithMail(String mail){
        return getUserDatabase().stream()
                .filter(ourUser -> ourUser.getMail().equals(mail))
                .findFirst();
    }
    public static String generateMailCode() {
        int random = (int) (1000000 * Math.random());
        return Integer.toString(random);
    }
    public void addRegisteredUser(String mail) {
        OurUser ourUser = new OurUser(mail);
        ourUser.setMailCode(SimpleUserDatabase.generateMailCode());
        SimpleUserDatabase.addUserToDatabase(ourUser);
    }
    public static boolean areEmailsEndMailCodesTheSame(ConfirmationRequest confirmation, OurUser userToBeChecked) {
        return Objects.equals(confirmation.getMail(), userToBeChecked.getMail()) && Objects.equals(confirmation.getMailCode(), userToBeChecked.getMailCode());
    }

}
