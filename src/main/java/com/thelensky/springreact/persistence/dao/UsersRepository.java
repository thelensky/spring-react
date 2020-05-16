package com.thelensky.springreact.persistence.dao;

import com.thelensky.springreact.persistence.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

  Users findByName(String name);

  String findByEmail(String email);
}
