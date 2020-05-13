package com.thelensky.springreact.persistence.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_roles", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"users_id", "role_id"})})
public class UserRoles {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "users_id", nullable = false)
  private Users users;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  public UserRoles() {
  }
}
