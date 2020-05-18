package com.thelensky.springreact.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class VerificationToken {
  private static final int EXPIRATION = 60 * 24;

  @Id
  @GeneratedValue
  private Long id;
  private String token;
  @OneToOne(fetch = FetchType.EAGER, targetEntity = Users.class)
  @JoinColumn(nullable = false, name = "users_id")
  private Users user;
  private LocalDateTime expiryDate = LocalDateTime.now();

  public VerificationToken() {
  }
}
