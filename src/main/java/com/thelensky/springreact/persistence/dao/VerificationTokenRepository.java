package com.thelensky.springreact.persistence.dao;

import com.thelensky.springreact.persistence.model.Users;
import com.thelensky.springreact.persistence.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository <VerificationToken, Long> {
  VerificationToken findByToken(String token);
}
