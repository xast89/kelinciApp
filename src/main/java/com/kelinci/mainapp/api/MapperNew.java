package com.kelinci.mainapp.api;

public class MapperNew {
    public static RegisteredUserResponse toRegisteredUserResponse(OurUser ourUser) {
        return new RegisteredUserResponse(ourUser.getMail());
    }

}
