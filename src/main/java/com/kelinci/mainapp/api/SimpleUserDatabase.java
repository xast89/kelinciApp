package com.kelinci.mainapp.api;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component

public class SimpleUserDatabase implements UserDatabaseInterface {

    private static final List<OurUser> userDatabase = new ArrayList<>();

    @Override
    public List<OurUser> getUserDatabase() {
        return userDatabase;
    }

    @Override
    public void addUserToDatabase(OurUser ourUser) {
        userDatabase.add(ourUser);
    }

    @Override
    public void clearUserDatabase() {
        userDatabase.clear();
    }

    @Override
    public Optional<OurUser> getUserWithMail(String mail) {
        return getUserDatabase().stream().filter(ourUser -> ourUser.getMail().equals(mail)).findFirst();
    }

    @Override
    public void addRegisteredUser(String mail) {
        OurUser ourUser = new OurUser(mail);
        FirstUtilClass mailGenerator = new FirstUtilClass();
        SimpleUserDatabase userDatabaseController = new SimpleUserDatabase();
        ourUser.setMailCode(mailGenerator.generateMailCode());
        userDatabaseController.addUserToDatabase(ourUser);
    }

}
