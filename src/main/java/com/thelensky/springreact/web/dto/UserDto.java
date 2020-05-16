package com.thelensky.springreact.web.dto;

import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserDto {

  @NonNull
  private String name;

  @NonNull
  @NotEmpty
  private String password;

  @NonNull
  @NotEmpty
  @Email
  private String email;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "UserDto{" + "name='" + name + '\'' + ", password='" + password +
           '\'' + ", email='" + email + '\'' + '}';
  }
}
