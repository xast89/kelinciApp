package com.kelinci.mainapp.api;

import java.util.Objects;

public class User {
    //ta klasa jest dla naszego wewnetrznego uzytku
    private String name;
    private String surname;
    private int age;

    public User(String name, String surname, int age) {

        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //sprawdza czy nie jest nullem albo czy te porownywane obiekty naleza do innych klas, kon i owca nie moga byc equals
        User user = (User) o;
        //rzutuje na usera
        return age == user.age && Objects.equals(name, user.name) && Objects.equals(surname, user.surname);
        //tutaj domyślnie wszystkie trzy pola się mają zgadzać, ale to nie jest reguła - możemy sami z definiować
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }
    //jak dwa obiekty mają ten sam hashcode to nie gwarantuje equals, ale hashcody są szybsza w algorytmach, jeśli hashcody są inne to
    // wiemy że to nie jest ten sam obiekt, równośc hashcodów nie gwaranatuje ze to ten sam obiekt - musimy wywolać equals zeby wiedziec na 1--%
    // wykorzystywane w Set i Map

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
