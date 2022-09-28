package com.kelinci.mainapp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    private User zapisanyUser;

    @GetMapping(value = "/users")
    //czeka na wywolanie localhost:8080/users
    public User users() {
        return new User("Pawel", "Krzak");
    }

    @PostMapping(value = "/user/add")
    public void addUser(@RequestBody UserRequest userSendByOurAppClient) {
        //dzięki obiektowi, na który wskazuje zmienna referencyjna userSendByOurAppClient mam dostęp do wszystkich wartości, jakie wysłał mi Klient w
        // swoim requestem na endpoint localhost:8080/user/add (metoda POST). Jak widzisz, Spring zrobił tutaj magię -> zmapował wysłanego do nas JSON'a
        // na obiekt klasy UserRequest.Jak widzisz, klasa UserRequest jest tylko po to, by odebrać wartośći z requestu i byśmy mogli coś potem zrobić
        // z tymi wysłanymi przez klienta wartościami

        final String wartoscNameZRequestu = userSendByOurAppClient.getName();
        final int wartoscAgeZRequestu = userSendByOurAppClient.getAge();
        final boolean wartoscRegisteredZRequestu = userSendByOurAppClient.isRegistered();

        final User user = new User(wartoscNameZRequestu, "tutajPOwinienNBycSurnameZRequesty");

        zapisanyUser = user;


    }
}
