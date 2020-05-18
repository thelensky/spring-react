package com.thelensky.springreact.web.listener;

import com.thelensky.springreact.persistence.model.Users;
import com.thelensky.springreact.service.IUsersService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements

    ApplicationListener<OnRegistrationCompleteEvent> {

  private IUsersService service;
  private JavaMailSender mailSender;

  public RegistrationListener(IUsersService service,
                              JavaMailSender mailSender) {
    this.service = service;
    this.mailSender = mailSender;
  }

  @Override
  public void onApplicationEvent(OnRegistrationCompleteEvent event) {
    this.confirmRegistration(event);
  }

  private void confirmRegistration(OnRegistrationCompleteEvent event) {
    Users user = event.getUser();
    String token = UUID.randomUUID()
                       .toString();
    service.createVerificationToken(user,
                                    token);

    String recipientAddress = user.getEmail();
    String subject = "Registration Confirmation";
    String confirmationUrl =
        event.getAppUrl() + "/regitrationConfirm?token=" + token;
    String message = "You registered successfully. We will send you a " +
                     "confirmation message to your email account.";

    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(recipientAddress);
    email.setSubject(subject);
    email.setText(message + "\r\n" + confirmationUrl);
    mailSender.send(email);
  }
}
