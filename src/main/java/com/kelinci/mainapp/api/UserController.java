package com.kelinci.mainapp.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private OurUser ourUser;

    private List<OurUser> listOfUsers = new ArrayList<>();

    @GetMapping(value = "/registered/lastuser")
    //czeka na wywolanie localhost:8080/users
    public OurUser getOurUsers() {
        return ourUser;
    }

    @GetMapping(value = "/registered/listofusers")
    //czeka na wywolanie localhost:8080/users
    public String getListOfRegisteredUsers() {
        return listOfUsers.toString();
    }

    @DeleteMapping(value = "/registered/delete")
    public void deleteOurUser() {
        ourUser = null;
        listOfUsers.clear();
    }

    @PostMapping(value = "/registration")
    public void register(@RequestBody RegisterRequest request) {

        final OurUser userFromRequest = new OurUser(request.getMail());
        //in case the list is empty - add the userFromRequest with its randomly generated mailCode
        if (listOfUsers.isEmpty()) {
            String mailCode = generateMailCode();
            userFromRequest.setMailCode(mailCode);
            ourUser = userFromRequest;
            listOfUsers.add(userFromRequest);
            //in case the list is not empty, stream all email addresses from listOfUsers into a new List: listOfAllEmails
            //the list maps and collects only email addresses from listOfUsers
            //eventually if listOfAllEmails does not contain the email from the incoming user, generate mailCode and add user

        } else {
            List<String> listOfAllEmails = listOfUsers.stream().map(OurUser::getMail).collect(Collectors.toList());

            if (!(listOfAllEmails.contains(userFromRequest.getMail()))) {

                String mailCode = generateMailCode();
                userFromRequest.setMailCode(mailCode);
                ourUser = userFromRequest;
                listOfUsers.add(userFromRequest);
            }
        }
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
            ourUser = userToBeConfirmed;
        }
    }

    private static String generateMailCode() {
        Integer random = (int) (1000000 * Math.random());
        String mailCode = Integer.toString(random);
        return mailCode;
    }

    private static boolean areEmailsAndMailCodesTheSame(OurUser userToBeConfirmed, OurUser userToBeChecked) {
        return Objects.equals(userToBeChecked.getMail(), userToBeConfirmed.getMail()) && Objects.equals(userToBeChecked.getMailCode(), userToBeConfirmed.getMailCode());
    }


}



