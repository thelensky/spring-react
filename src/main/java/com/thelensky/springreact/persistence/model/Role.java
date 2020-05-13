package com.thelensky.springreact.persistence.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"role_name"})})
public class Role {
  @Id
  @GeneratedValue
  @Column(name = "role_id")
  private Long id;

  @Column(name = "role_name")
  private String roleName;

  public Role() {
  }
}
