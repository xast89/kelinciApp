package com.kelinci.mainapp.api;

public class UserRequest {

  private String name;
  private int age;
  private boolean isRegistered;

  public UserRequest(String name, int age, boolean isRegistered) {
    this.name = name;
    this.age = age;
    this.isRegistered = isRegistered;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
