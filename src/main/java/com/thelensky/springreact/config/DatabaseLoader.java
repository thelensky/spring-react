package com.thelensky.springreact.config;

import com.thelensky.springreact.persistence.dao.RecordsRepository;
import com.thelensky.springreact.persistence.dao.RoleRepository;
import com.thelensky.springreact.persistence.dao.UserRolesRepository;
import com.thelensky.springreact.persistence.dao.UsersRepository;
import com.thelensky.springreact.persistence.model.Role;
import com.thelensky.springreact.persistence.model.UserRoles;
import com.thelensky.springreact.persistence.model.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

  RoleRepository roleRepository;
  RecordsRepository recordsRepository;
  UsersRepository usersRepository;
  UserRolesRepository userRolesRepository;

  public DatabaseLoader(RoleRepository roleRepository,
                        RecordsRepository recordsRepository,
                        UsersRepository usersRepository,
                        UserRolesRepository userRolesRepository) {
    this.roleRepository = roleRepository;
    this.recordsRepository = recordsRepository;
    this.usersRepository = usersRepository;
    this.userRolesRepository = userRolesRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    //roles
    Role adminRole = new Role();
    adminRole.setRoleName("ROLE_ADMIN");
//    this.roleRepository.save(adminRole);
    Role userRole = new Role();
    userRole.setRoleName("ROLE_USER");
//    this.roleRepository.save(userRole);

    // users
    Users user1 = new Users();
    user1.setName("user1");
    user1.setPassword("123");
    user1.setEmail("admin@admin.ru");
//    this.usersRepository.save(user1);
    Users user2 = new Users();
    user2.setName("user2");
    user2.setPassword("234");
    user2.setEmail("user@user.ru");
//    this.usersRepository.save(user2);

    // userRoles
    UserRoles userRoles1 = new UserRoles();
    userRoles1.setUsers(user1);
    userRoles1.setRole(adminRole);
//    this.userRolesRepository.save(userRoles1);
    UserRoles userRoles3 = new UserRoles();
    userRoles3.setUsers(user1);
    userRoles3.setRole(userRole);
//    this.userRolesRepository.save(userRoles3);
    UserRoles userRoles2 = new UserRoles();
    userRoles2.setUsers(user2);
    userRoles2.setRole(userRole);

    //logging
//    this.userRolesRepository.save(userRoles2);
//    System.out.println("user1 :" + user1 + " has role " +
//                       roleRepository.findRolesByUserId(user1.getId()));
//    System.out.println("user2 :" + user2 + " has role " +
//                       roleRepository.findRolesByUserId(user2.getId()));
  }
}
