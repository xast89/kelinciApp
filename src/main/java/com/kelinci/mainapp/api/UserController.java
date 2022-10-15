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

    private List<OurUser> listOfUsers = new ArrayList<>();

    @GetMapping(value = "/registered/lastregistereduser")
    //czeka na wywolanie localhost:8080/users
    public OurUser getOurUsers() {
        return registeredUser;
    }

    @GetMapping(value = "/registered/lastconfirmeduser")
    //czeka na wywolanie localhost:8080/users
    public OurUser getConfirmedUsers() {
        return confirmedUser;
    }

    @GetMapping(value = "/registered/listofusers")
    //czeka na wywolanie localhost:8080/users
    public String getListOfRegisteredUsers() {
        return listOfUsers.toString();
    }

    @DeleteMapping(value = "/registered/delete")
    public void deleteOurUser() {
        registeredUser = null;
        confirmedUser = null;
        listOfUsers.clear();
    }

    @PostMapping(value = "/registration")
    public void register(@RequestBody RegisterRequest request) {

        Integer random = (int) (1000000 * Math.random());
        String mailCode = Integer.toString(random);
        System.out.println(mailCode);

        final OurUser ourUser = new OurUser(request.getMail(), mailCode, false);

        //wysylamy tutaj maila

        registeredUser = ourUser;
        listOfUsers.add(registeredUser);

    }

    @PostMapping(value = "/registered/confirm")
    public void confirm(@RequestBody ConfirmationRequest confirmation) {

        final OurUser userToBeConfirmed = new OurUser(confirmation.getMail(), confirmation.getMailCode(), false);

        for (OurUser userToBeChecked : listOfUsers) {
            if (areEmailsAndMailCodesTheSame(userToBeConfirmed, userToBeChecked)) {
                listOfUsers.remove(userToBeConfirmed);
                userToBeConfirmed.setConfirmed(true);
                listOfUsers.add(userToBeConfirmed);
                //tutaj jedyne co wykombinowalem to usunac usera z listy, ustawic mu setConfirmed na true, i potem dodac ponownie do listy
                //bo na to wygląda, że zmiana pól obiektu nie updatuje tych pól jeśli obiekt jest już w liście - dobrze wiedziec
            }
            confirmedUser = userToBeConfirmed;
        }

    }

    private static boolean areEmailsAndMailCodesTheSame(OurUser userToBeConfirmed, OurUser userToBeChecked) {
        return Objects.equals(userToBeChecked.getMail(), userToBeConfirmed.getMail()) && Objects.equals(userToBeChecked.getMailCode(), userToBeConfirmed.getMailCode());
    }


}


