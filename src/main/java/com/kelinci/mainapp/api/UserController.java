package com.kelinci.mainapp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private OurUser zapisanyUser;

    @GetMapping(value = "/registered/users")
    //czeka na wywolanie localhost:8080/users
    public OurUser getOurUsers() {
        return zapisanyUser;
    }

    @PostMapping(value = "/registration")
    public void register(@RequestBody Register request) {
        String mailCode = "123456";
        //String - tutaj zrobic random od 0 do 99999
        //final String emailZRequestu = request.getMail();

        final OurUser ourUser = new OurUser(request.getMail(), mailCode, false);

        //wysylamy tutaj maila

        zapisanyUser = ourUser;

    }

}
