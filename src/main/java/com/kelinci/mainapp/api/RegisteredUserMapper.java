package com.kelinci.mainapp.api;

import java.util.List;
import java.util.stream.Collectors;


public class RegisteredUserMapper {
    public RegisteredUserMapper() {
    }

    public List<RegisteredUserResponse> getListOfRegisteredUsersEmails() {
        SimpleUserDatabase userDatabaseController = new SimpleUserDatabase();
        return userDatabaseController.getUserDatabase().stream()
                .map(this::toRegisteredUserResponse)
                .collect(Collectors.toList());
    }

    public RegisteredUserResponse toRegisteredUserResponse(OurUser ourUser) {
        return new RegisteredUserResponse(ourUser.getMail());
    }

}
