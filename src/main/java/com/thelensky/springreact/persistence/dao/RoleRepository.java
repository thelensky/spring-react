package com.thelensky.springreact.persistence.dao;

import com.thelensky.springreact.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
  @Query( value = "select r.roleName from UserRoles ur " +
                                     "inner join " +
                                     " Role r on ur.role.id = r.id " +
                                     "where ur.users.id = :userId")
  List<String> findRolesByUserId(@Param
                              ("userId") Long userId);

  Role findByRoleName(String roleName);
}
