package com.thelensky.springreact.web.controller;

import com.thelensky.springreact.persistence.model.Users;
import com.thelensky.springreact.persistence.model.VerificationToken;
import com.thelensky.springreact.service.IUsersService;
import com.thelensky.springreact.web.dto.UserDto;
import com.thelensky.springreact.web.listener.OnRegistrationCompleteEvent;
import com.thelensky.springreact.web.util.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@Slf4j
public class RegistrationController {

  private IUsersService userService;
  private ApplicationEventPublisher applicationEventPublisher;

  public RegistrationController(IUsersService userService,
                                ApplicationEventPublisher applicationEventPublisher) {
    this.userService = userService;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @PostMapping("/user/registration")
  @ResponseBody
  public GenericResponse registerUserAccount(
      @RequestBody
      @Valid
      final UserDto accountDto, final HttpServletRequest request) {
    final Users registered = userService.registerNewUserAccount(accountDto);
    applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,
                                                                           getAppUrl(request)));

    return new GenericResponse("success");
  }

  @GetMapping("/regitrationConfirm")
  public GenericResponse confirmRegistration(
      @RequestParam("token")
          String token) {

    log.info(">>>>>>>>>>> token is: " + token);
    VerificationToken verificationToken = userService.getVerificationToken(token);

    log.info(">>>>>>>>> expired: " + (LocalDateTime.now()
                                                   .isBefore(verificationToken.getExpiryDate())));

    if (verificationToken == null) {

      return new GenericResponse("invalid Token",
                                 "true");
    }

    Users user = verificationToken.getUser();

    if (LocalDateTime.now()
                     .isBefore(verificationToken.getExpiryDate())) {

      return new GenericResponse("Token expired",
                                 "true");
    }

    // confirm
    user.setEnabled(true);
    userService.saveRegisteredUser(user);
    return new GenericResponse("success");
  }

  private String getAppUrl(HttpServletRequest request) {
    return "http://" + request.getServerName() + ":" + request.getServerPort() +
           request.getContextPath();
  }
}
