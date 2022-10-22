package com.kelinci.mainapp.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.kelinci.mainapp.api.SimpleUserDatabase.getUserDatabase;

public class Mapper {
    public Mapper() {
    }

    public List<RegisteredUserResponse> getRegisteredUsersEmails() {
        return getUserDatabase().stream()
                .map(this::toRegisteredUserResponse)
                .collect(Collectors.toList());
    }

    public RegisteredUserResponse toRegisteredUserResponse(OurUser ourUser) {
        return new RegisteredUserResponse(ourUser.getMail());
    }

}
