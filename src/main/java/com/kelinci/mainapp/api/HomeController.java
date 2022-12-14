package com.kelinci.mainapp.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
//wymiany JSON po HTTP, to @RestController zamienia klase HomeController and nasłuchiwanie endpointów http
//Spring nadbudowuje swoje features
public class HomeController {

    private User zapisanyUser;

    @GetMapping(value = "/users")
    //czeka na wywolanie localhost:8080/users, nasłuchuje ne users i wywolu
    public User getUsers() {
        return zapisanyUser;
    }

    @DeleteMapping(value = "/user/delete")
    public void deleteUser() {
        zapisanyUser = null;
    }

    @PostMapping(value = "/user/add")
    public void addUser(@RequestBody UserRequest userSendByOurAppClient) {
        //dzięki obiektowi, na który wskazuje zmienna referencyjna userSendByOurAppClient mam dostęp do wszystkich wartości, jakie wysłał mi Klient w
        // swoim requestem na endpoint localhost:8080/user/add (metoda POST). Jak widzisz, Spring zrobił tutaj magię -> zmapował wysłanego do nas JSON'a
        // na obiekt klasy UserRequest.Jak widzisz, klasa UserRequest jest tylko po to, by odebrać wartośći z requestu i byśmy mogli coś potem zrobić
        // z tymi wysłanymi przez klienta wartościami
        // json jest modelem komunikacyjnym po to żeby miec komunikacje miedzy np jezykami

        final String wartoscNameZRequestu = userSendByOurAppClient.getName();
        final String wartoscSurnameZRequestu = userSendByOurAppClient.getSurname();

        final int wartoscAgeZRequestu = userSendByOurAppClient.getAge();
        final boolean wartoscRegisteredZRequestu = userSendByOurAppClient.isRegistered();

        final User user = new User(wartoscNameZRequestu, wartoscSurnameZRequestu, wartoscAgeZRequestu);
        //dodac tutaj age z requestu i potem commit
        zapisanyUser = user;


    }
}
