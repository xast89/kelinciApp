package com.kelinci.mainapp.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseService {

    private final SimpleUserDatabase userDatabaseController = new SimpleUserDatabase();

    public DatabaseService() {
    }

    public Optional<OurUser> getUserWithMail(String mail) {
        return userDatabaseController.getUserDatabase().stream()
                .filter(ourUser -> ourUser.getMail().equals(mail))
                .findFirst();
    }

}
