package com.thelensky.springreact.web.listener;

import com.thelensky.springreact.persistence.model.Users;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
  private final String appUrl;
  private final Users user;

  public OnRegistrationCompleteEvent(Users user,  String appUrl) {
    super(user);
    this.appUrl = appUrl;
    this.user = user;
  }

  public String getAppUrl() {
    return appUrl;
  }


  public Users getUser() {
    return user;
  }
}
