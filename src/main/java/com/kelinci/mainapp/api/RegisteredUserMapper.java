package com.kelinci.mainapp.api;

import java.util.List;
import java.util.stream.Collectors;

import static com.kelinci.mainapp.api.SimpleUserDatabase.getUserDatabase;

public class RegisteredUserMapper {
    public RegisteredUserMapper() {
    }

    public List<RegisteredUserResponse> getListOfRegisteredUsersEmails() {
        return getUserDatabase().stream()
                .map(this::toRegisteredUserResponse)
                .collect(Collectors.toList());
    }

    public RegisteredUserResponse toRegisteredUserResponse(OurUser ourUser) {
        return new RegisteredUserResponse(ourUser.getMail());
    }

}
