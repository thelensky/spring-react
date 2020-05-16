package com.thelensky.springreact.service;

import com.thelensky.springreact.persistence.model.Users;
import com.thelensky.springreact.web.dto.UserDto;
import com.thelensky.springreact.web.error.UserAlreadyExistException;

public interface IUsersService {
  Users registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;
}
