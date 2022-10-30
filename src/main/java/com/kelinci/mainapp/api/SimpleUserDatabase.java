package com.kelinci.mainapp.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleUserDatabase {

    private static final List<OurUser> userDatabase = new ArrayList<>();
    private final FirstUtilClass mailGenerator = new FirstUtilClass();
    private final SimpleUserDatabase userDatabaseController = new SimpleUserDatabase();

    public List<OurUser> getUserDatabase() {
        return userDatabase;
    }

    public void addUserToDatabase(OurUser ourUser) {
        userDatabase.add(ourUser);
    }

    public void clearUserDatabase() {
        userDatabase.clear();
    }

    public void addRegisteredUser(String mail) {
        OurUser ourUser = new OurUser(mail);
        ourUser.setMailCode(mailGenerator.generateMailCode());
        userDatabaseController.addUserToDatabase(ourUser);
    }

}
