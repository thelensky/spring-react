package com.thelensky.springreact.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Users {
  public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  @Id
  @GeneratedValue
  @Column(name = "users_id")
  private Long id;

  @Column(name = "enabled")
  private boolean enabled;

  @Column(name = "name")
  private String name;

  @Column(name = "password", length = 60)
  @JsonIgnore
  private String password;

  @Column(unique = true, nullable = false)
  private String email;

  public Users() {
  }

  public void setPassword(String password) {
    this.password = PASSWORD_ENCODER.encode(password);
  }
}
