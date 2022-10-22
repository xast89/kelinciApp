package com.kelinci.mainapp.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final SimpleUserDatabase userDatabaseController = new SimpleUserDatabase();

    @GetMapping(value = "/registered/lastuser")
    public OurUser getOurUsers() {
        return SimpleUserDatabase.getUserDatabase().get(0);
    }

    @GetMapping(value = "/registered/listofusers")
    public List<RegisteredUserResponse> getRegisteredUserResponse() {
        Mapper mapper = new Mapper();
        return mapper.getRegisteredUsersEmails();
    }

    @DeleteMapping(value = "/registered/delete")
    public void deleteOurUser() {
        userDatabaseController.clearUserDatabase();
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        String mailFromRequest = request.getMail();

        if (userDatabaseController.getUserWithMail(mailFromRequest).isEmpty()) {
            userDatabaseController.addRegisteredUser(mailFromRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PostMapping(value = "/registered/confirm")
    public void confirm(@RequestBody ConfirmationRequest confirmation) {

        for (OurUser userToBeChecked : SimpleUserDatabase.getUserDatabase()) {
            if (SimpleUserDatabase.areEmailsEndMailCodesTheSame(confirmation, userToBeChecked)) {
                userToBeChecked.setConfirmed(true);
            }
        }
    }
}



