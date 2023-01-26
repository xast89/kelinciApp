package com.kelinci.mainapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    @Autowired
    private UserDatabaseInterface userDatabaseController;
    @Autowired
    private RegisteredUserMapper mapper;


    //@Autowired tutaj sygnalizuje ze Spring przejmuje ten komponent

    @GetMapping(value = "/registered/lastuser")
    public OurUser getOurUsers() {
        return userDatabaseController.getUserDatabase().get(userDatabaseController.getUserDatabase().size()-1);
    }
    //dodać response entity też w tym getterze


    //getOurUsers powinna miec utworzony obiekt z klasy UserController ale tego nie potrzeba bo to robi Spring w tle
    //
    //dać size minus 1, pobawić sie w Evaluate expression
    @GetMapping(value = "/registered/listofusers")
    public ResponseEntity<List<RegisteredUserResponse>> getRegisteredUserResponse() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Baeldung-Example-Header",
                "Value-ResponseEntityBuilderWithHttpHeaders");


        return ResponseEntity
                .ok()  //ustawienie statusu
                .headers(responseHeaders) //ustawienie headera
                .body(mapper.mapToRegisteredUserResponses(userDatabaseController.getUserDatabase())); //ust. body
        //teraz definiujemy dokladnie co będzie we wszystkich 3 elementach (status, head, body)
    }

    @DeleteMapping(value = "/registered/delete")
    public void deleteOurUser() {
        userDatabaseController.clearUserDatabase();
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String mailFromRequest = request.getMail();

        if (userDatabaseController.getUserWithMail(mailFromRequest).isEmpty()) {
            userDatabaseController.addRegisteredUser(mailFromRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("String zwracany w body");
    }

    @PostMapping(value = "/registered/confirm")
    public ResponseEntity<Void> confirm(@RequestBody ConfirmationRequest confirmation) {
        FirstUtilClass emailAndEmailCodeComparator = new FirstUtilClass();
        for (OurUser userToBeChecked : userDatabaseController.getUserDatabase()) {

            if (emailAndEmailCodeComparator.areEmailsEndMailCodesTheSame(confirmation, userToBeChecked)) {
                userToBeChecked.setConfirmed(true);
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}



