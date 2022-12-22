package com.kelinci.mainapp.api;

import java.util.List;
import java.util.Optional;

public interface UserDatabaseInterface {
    List<OurUser> getUserDatabase();

    void addUserToDatabase(OurUser ourUser);

    void clearUserDatabase();

    Optional<OurUser> getUserWithMail(String mail);

    void addRegisteredUser(String mail);


}
