package com.kelinci.mainapp.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    public UserController() {
    }

    private final SimpleUserDatabase userDatabaseController = new SimpleUserDatabase();
    private final RegisteredUserMapper mapper = new RegisteredUserMapper();
    private final DatabaseService databaseService = new DatabaseService();

    @GetMapping(value = "/registered/lastuser")
    public OurUser getOurUsers() {
        return userDatabaseController.getUserDatabase().get(0);

    }

    @GetMapping(value = "/registered/listofusers")
    public List<RegisteredUserResponse> getRegisteredUserResponse() {
        return mapper.mapToRegisteredUserResponses(userDatabaseController.getUserDatabase());
    }

    @DeleteMapping(value = "/registered/delete")
    public void deleteOurUser() {
        userDatabaseController.clearUserDatabase();
        //Resp Voida jako void, dodac return na kazdym etapie, 200 Created, albo, CONFLICT , ifami statusy rozne powkladac,
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        String mailFromRequest = request.getMail();

        if (databaseService.getUserWithMail(mailFromRequest).isEmpty()) {
            userDatabaseController.addRegisteredUser(mailFromRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PostMapping(value = "/registered/confirm")
    public void confirm(@RequestBody ConfirmationRequest confirmation) {
        FirstUtilClass emailAndEmailCodeComparator = new FirstUtilClass();
        for (OurUser userToBeChecked : userDatabaseController.getUserDatabase()) {

            if (emailAndEmailCodeComparator.areEmailsEndMailCodesTheSame(confirmation, userToBeChecked)) {
                userToBeChecked.setConfirmed(true);
            }
        }
    }
}



