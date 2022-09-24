package com.kelinci.mainapp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/users")
    //czeka na wywolanie localhost:8080/users
    public User users(){
        return new User("Pawel", "Krzak");
    }
}
