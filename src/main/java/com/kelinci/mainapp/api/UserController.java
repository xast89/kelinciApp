package com.kelinci.mainapp.api;

import com.sun.xml.bind.v2.TODO;
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
    private final List<OurUser> listOfUsers = new ArrayList<>();

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
        //To do: zastąpić wszystko jednym streamem z komentarza Pawła
        if (listOfUsers.isEmpty()) {
            generateAndSetMailCode(userFromRequest);
        } else {
            List<String> listOfAllEmails = listOfUsers.stream().map(OurUser::getMail).toList();
            if (!(listOfAllEmails.contains(userFromRequest.getMail()))) {
                generateAndSetMailCode(userFromRequest);
            }
        }
    }

    @PostMapping(value = "/registered/confirm")
    public void confirm(@RequestBody ConfirmationRequest confirmation) {

        for (OurUser userToBeChecked : listOfUsers) {
            if (areEmailsEndMailCodesTheSame(confirmation, userToBeChecked)) {
                userToBeChecked.setConfirmed(true);
            }
        }
    }

    private static boolean areEmailsEndMailCodesTheSame(ConfirmationRequest confirmation, OurUser userToBeChecked) {
        return Objects.equals(confirmation.getMail(), userToBeChecked.getMail()) && Objects.equals(confirmation.getMailCode(), userToBeChecked.getMailCode());
    }

    private void generateAndSetMailCode(OurUser userFromRequest) {
        String mailCode = generateMailCode();
        userFromRequest.setMailCode(mailCode);
        ourUser = userFromRequest;
        listOfUsers.add(userFromRequest);
    }

    private static String generateMailCode() {
        int random = (int) (1000000 * Math.random());
        return Integer.toString(random);
    }

}



