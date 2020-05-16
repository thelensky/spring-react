package com.thelensky.springreact.web.controller;

import com.thelensky.springreact.persistence.model.Users;
import com.thelensky.springreact.service.IUsersService;
import com.thelensky.springreact.web.dto.UserDto;
import com.thelensky.springreact.web.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Slf4j
public class RegistrationController {

  private IUsersService userService;

  public RegistrationController(IUsersService userService) {
    this.userService = userService;
  }

  @PostMapping("/user/registration")
  @ResponseBody
  public GenericResponse registerUserAccount(
      @RequestBody
      @Valid
      final UserDto accountDto, final HttpServletRequest request) {
    log.info(">>> Dto registry: " + accountDto.toString());
    final Users registered = userService.registerNewUserAccount(accountDto);
    return new GenericResponse("success");
  }
}
