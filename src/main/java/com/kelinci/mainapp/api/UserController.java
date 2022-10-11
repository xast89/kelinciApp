package com.kelinci.mainapp.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private OurUser registeredUser;
    private OurUser confirmedUser;

    @GetMapping(value = "/registered/users")
    //czeka na wywolanie localhost:8080/users
    public OurUser getOurUsers() {
        return registeredUser;
    }

    @GetMapping(value = "/registered/confirmedusers")
    //czeka na wywolanie localhost:8080/users
    public OurUser getConfirmedUsers() {
        return confirmedUser;
    }

    @DeleteMapping(value = "/registered/delete")
    public void deleteOurUser() {
        registeredUser = null;
        confirmedUser = null;
    }

    @PostMapping(value = "/registration")
    public void register(@RequestBody Register request) {

        Integer random = (int) (1000000 * Math.random());
        String mailCode = random.toString();
        System.out.println(mailCode);

        final OurUser ourUser = new OurUser(request.getMail(), mailCode, false);

        //wysylamy tutaj maila

        registeredUser = ourUser;

    }

    @PostMapping(value = "/registered/confirm")
    public void confirm(@RequestBody Register confirmation) {

        final OurUser userToBeConfirmed = new OurUser(confirmation.getMail(), confirmation.getMailCode(), false);
        final OurUser existingUser = new OurUser(registeredUser.getMail(), registeredUser.getMailCode(), registeredUser.isConfirmed());
        //registeredUser could be hard-coded as false here as double protection, but maybe not necessary

        if (userToBeConfirmed.equals(existingUser)) {
            //equals method is overriding default equals method from OurUser class
            existingUser.setConfirmed(true);
            confirmedUser = existingUser;
        }


    }

}
