package com.thelensky.springreact.persistence.doa;

import com.thelensky.springreact.persistence.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
  Users findByName(String name);
}
