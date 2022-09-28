package com.kelinci.mainapp.api;

public class UserRequest {

  private String name;
  private String surname;
  private int age;
  private boolean isRegistered;

  public UserRequest(String name, String surname, int age, boolean isRegistered) {
    this.name = name;
    this.surname = surname;
    this.age = age;
    this.isRegistered = isRegistered;
  }

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

  public boolean isRegistered() {
    return isRegistered;
  }

  public void setRegistered(boolean registered) {
    isRegistered = registered;
  }
}
