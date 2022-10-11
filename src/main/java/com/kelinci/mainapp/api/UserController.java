package com.kelinci.mainapp.api;

import org.springframework.web.bind.annotation.DeleteMapping;
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

    @DeleteMapping(value = "/registered/delete")
    public void deleteOurUser() {
        zapisanyUser = null;
    }

    @PostMapping(value = "/registration")
    public void register(@RequestBody Register request) {

        Integer random = (int) (1000000 * Math.random());
        String mailCode = random.toString();
        System.out.println(mailCode);

        final OurUser ourUser = new OurUser(request.getMail(), mailCode, false);

        //wysylamy tutaj maila

        zapisanyUser = ourUser;

    }

}
