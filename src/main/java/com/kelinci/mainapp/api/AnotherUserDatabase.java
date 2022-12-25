package com.kelinci.mainapp.api;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component

public class AnotherUserDatabase implements UserDatabaseInterface{
    @Override
    public List<OurUser> getUserDatabase() {
        return null;
    }

    @Override
    public void addUserToDatabase(OurUser ourUser) {

    }

    @Override
    public void clearUserDatabase() {

    }

    @Override
    public Optional<OurUser> getUserWithMail(String mail) {
        return Optional.empty();
    }

    @Override
    public void addRegisteredUser(String mail) {

    }
}
