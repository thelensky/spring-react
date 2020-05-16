package com.thelensky.springreact.persistence.dao;

import com.thelensky.springreact.persistence.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
}