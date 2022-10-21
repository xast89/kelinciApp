package com.kelinci.mainapp.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final List<OurUser> listOfUsers = new ArrayList<>();

    @GetMapping(value = "/registered/lastuser")
    //czeka na wywolanie localhost:8080/users
    public OurUser getOurUsers() {
        return listOfUsers.get(0);
    }

    @GetMapping(value = "/registered/listofusers")
    //czeka na wywolanie localhost:8080/users
    public List<RegisteredUserResponse> getRegisteredUserResponse() {
        //mapujemy tutaj ourUser na ResitsterUR - chodzi o to żeby ujawnić tylko to co ma być zdefiniowane w RUR
        return listOfUsers.stream()
                .map(this::toRegisteredUserResponse)
                .collect(Collectors.toList());
    }






    //ta metode wrzucic do mappera - ktory bedzie mial tylko ta metode publiczna
    private RegisteredUserResponse toRegisteredUserResponse(OurUser ourUser) {
        return new RegisteredUserResponse(ourUser.getMail());
    }

    @DeleteMapping(value = "/registered/delete")
    public void deleteOurUser() {
        listOfUsers.clear();
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        String mailFromRequest = request.getMail();
        Optional<OurUser> existingUser = listOfUsers.stream()
                .filter(ourUser -> ourUser.getMail().equals(mailFromRequest))
                .findFirst();
        //Optional: może się znaleść element OurUser ale nie musi, pomaga w unikaniu null pointer exception NPE

        if (existingUser.isEmpty()) {
            addRegisteredUser(mailFromRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
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

    private void addRegisteredUser(String mail) {
        OurUser ourUser = new OurUser(mail);
        ourUser.setMailCode(generateMailCode());
        listOfUsers.add(ourUser);
    }

    private String generateMailCode() {
        int random = (int) (1000000 * Math.random());
        return Integer.toString(random);
    }

}



