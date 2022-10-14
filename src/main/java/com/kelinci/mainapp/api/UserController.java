package com.kelinci.mainapp.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {
    private OurUser registeredUser;
    private OurUser confirmedUser;

    private List<OurUser> listOfRegisteredUsers = new ArrayList<>();

    private List<OurUser> listOfConfirmedUsers = new ArrayList<>();

    @GetMapping(value = "/registered/users")
    //czeka na wywolanie localhost:8080/users
    public OurUser getOurUsers() {
        return registeredUser;
    }

    @GetMapping(value = "/registered/listofusers")
    //czeka na wywolanie localhost:8080/users
    public String getListOfRegisteredUsers() {
        return listOfRegisteredUsers.toString();
    }

    @GetMapping(value = "/registered/confirmedusers")
    //czeka na wywolanie localhost:8080/users
    public OurUser getConfirmedUsers() {
        return confirmedUser;
    }

    @GetMapping(value = "/registered/listofconfirmedusers")
    //czeka na wywolanie localhost:8080/users
    public String getListOfConfirmedUsers() {
        return listOfConfirmedUsers.toString();
    }

    @DeleteMapping(value = "/registered/delete")
    public void deleteOurUser() {
        registeredUser = null;
        confirmedUser = null;
        listOfRegisteredUsers.clear();
        listOfConfirmedUsers.clear();
    }

    @PostMapping(value = "/registration")
    public void register(@RequestBody RegisterRequest request) {

        Integer random = (int) (1000000 * Math.random());
        String mailCode = random.toString();
        System.out.println(mailCode);

        final OurUser ourUser = new OurUser(request.getMail(), mailCode, false);

        //wysylamy tutaj maila

        registeredUser = ourUser;
        listOfRegisteredUsers.add(registeredUser);

    }

    @PostMapping(value = "/registered/confirm")
    public void confirm(@RequestBody ConfirmationRequest confirmation) {

        final OurUser userToBeConfirmed = new OurUser(confirmation.getMail(), confirmation.getMailCode(), false);

        for (OurUser userToBeChecked : listOfRegisteredUsers) {
            if (Objects.equals(userToBeChecked.getMail(), userToBeConfirmed.getMail()) && Objects.equals(userToBeChecked.getMailCode(), userToBeConfirmed.getMailCode())) {
                userToBeConfirmed.setConfirmed(true);

                listOfConfirmedUsers.add(userToBeConfirmed);
                //if logic could probably be also done using overridden equals method from OurUser class
            }
            System.out.println(listOfConfirmedUsers.toString());
            confirmedUser = userToBeConfirmed;
        }

    }


}


