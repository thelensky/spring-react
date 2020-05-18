package com.thelensky.springreact.service;

import com.thelensky.springreact.persistence.model.Users;
import com.thelensky.springreact.persistence.model.VerificationToken;
import com.thelensky.springreact.web.dto.UserDto;
import com.thelensky.springreact.web.error.UserAlreadyExistException;

public interface IUsersService {

  Users registerNewUserAccount(
      UserDto userDto) throws UserAlreadyExistException;

  void saveRegisteredUser(Users user);

  void createVerificationToken(Users user, String token);

  VerificationToken getVerificationToken(String VerificationToken);
}
