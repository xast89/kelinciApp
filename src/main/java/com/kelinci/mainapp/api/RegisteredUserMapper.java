package com.kelinci.mainapp.api;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RegisteredUserMapper {
    public RegisteredUserMapper() {
    }

    public List<RegisteredUserResponse> mapToRegisteredUserResponses(List<OurUser> input) {
        //input.stream().map(ourUser -> toRegisteredUserResponse(ourUser)).collect(Collectors.toList());
        //lambda działa na tym samym parametrze co metoda (na tylko jednym), bo metoda bierze tą referencję z lambdy jako jedyny parametr)
        //this bo mapper zawiera metodę

        //return input.stream().map(MapperNew::toRegisteredUserResponse).collect(Collectors.toList());
        //tutaj zamiast this mamy referencje do metiody (nazwanej tak samo) ale w innej klasie (więc nie this)
        return input.stream().map(this::toRegisteredUserResponse).collect(Collectors.toList());
    }

    private RegisteredUserResponse toRegisteredUserResponse(OurUser ourUser) {
        return new RegisteredUserResponse(ourUser.getMail());
    }
    public List<RegisteredUserResponse> mapToRegisteredUserResponsesNew(List<OurUser> input) {
        return Optional.ofNullable(input).orElse(List.of()).stream().map(this::toRegisteredUserResponse).collect(Collectors.toList());
    }
//tu się bronimy przed null pointer exception - to nam daje wrapper Optional


}
