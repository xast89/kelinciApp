package com.kelinci.mainapp.api;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegisteredUserMapper {
    public RegisteredUserMapper() {
    }

    public List<RegisteredUserResponse> mapToRegisteredUserResponses(List<OurUser> input){
        return input.stream().map(this::toRegisteredUserResponse).collect(Collectors.toList());
    }

    private RegisteredUserResponse toRegisteredUserResponse(OurUser ourUser) {
        return new RegisteredUserResponse(ourUser.getMail());
    }

}
