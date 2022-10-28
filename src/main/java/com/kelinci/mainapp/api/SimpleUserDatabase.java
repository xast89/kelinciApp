package com.kelinci.mainapp.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleUserDatabase {

    private static final List<OurUser> userDatabase = new ArrayList<>();

    public List<OurUser> getUserDatabase() {
        return userDatabase;
    }

    public void addUserToDatabase(OurUser ourUser) {
        userDatabase.add(ourUser);
    }

    public void clearUserDatabase() {
        userDatabase.clear();
    }

    public Optional<OurUser> getUserWithMail(String mail) {
        return getUserDatabase().stream()
                .filter(ourUser -> ourUser.getMail().equals(mail))
                .findFirst();
    }

    public void addRegisteredUser(String mail) {
        OurUser ourUser = new OurUser(mail);
        FirstUtilClass mailGenerator = new FirstUtilClass();
        SimpleUserDatabase userDatabaseController = new SimpleUserDatabase();
        ourUser.setMailCode(mailGenerator.generateMailCode());
        userDatabaseController.addUserToDatabase(ourUser);
    }

}
