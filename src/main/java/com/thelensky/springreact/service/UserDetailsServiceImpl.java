package com.thelensky.springreact.service;

import com.thelensky.springreact.persistence.dao.RoleRepository;
import com.thelensky.springreact.persistence.dao.UsersRepository;
import com.thelensky.springreact.persistence.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private UsersRepository usersRepository;
  private RoleRepository roleRepository;

  public UserDetailsServiceImpl(UsersRepository usersRepository,
                                RoleRepository roleRepository) {
    this.usersRepository = usersRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public UserDetails loadUserByUsername(
      String username) throws UsernameNotFoundException {
    Users user = usersRepository.findByName(username);
    if (user == null) {
      System.out.println("User not found! " + username);
      throw new UsernameNotFoundException(
          "User " + username + " was not found in" + " the " +
          "database");
    }

    System.out.println("User" + user + " was found!");
    List<String> rolesName = roleRepository.findRolesByUserId(user.getId());

    List<GrantedAuthority> authorityList = new ArrayList<>();
    if (rolesName != null) {
      for (String role : rolesName) {
        authorityList.add(new SimpleGrantedAuthority(role));
      }
    }

    return new User(user.getName(),
                    user.getPassword(),
                    authorityList);
  }
}

