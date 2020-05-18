package com.thelensky.springreact.service;

import com.thelensky.springreact.persistence.dao.RoleRepository;
import com.thelensky.springreact.persistence.dao.UserRolesRepository;
import com.thelensky.springreact.persistence.dao.UsersRepository;
import com.thelensky.springreact.persistence.dao.VerificationTokenRepository;
import com.thelensky.springreact.persistence.model.Role;
import com.thelensky.springreact.persistence.model.UserRoles;
import com.thelensky.springreact.persistence.model.Users;
import com.thelensky.springreact.persistence.model.VerificationToken;
import com.thelensky.springreact.web.dto.UserDto;
import com.thelensky.springreact.web.error.UserAlreadyExistException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UsersService implements IUsersService {

  private UsersRepository usersRepository;
  private UserRolesRepository userRolesRepository;
  private RoleRepository roleRepository;
  private VerificationTokenRepository tokenRepository;

  public UsersService(UsersRepository usersRepository,
                      UserRolesRepository userRolesRepository,
                      RoleRepository roleRepository,
                      VerificationTokenRepository tokenRepository) {
    this.usersRepository = usersRepository;
    this.userRolesRepository = userRolesRepository;
    this.roleRepository = roleRepository;
    this.tokenRepository = tokenRepository;
  }

  @Override
  public Users registerNewUserAccount(
      UserDto accountDto) throws UserAlreadyExistException {

    final String ROLE_USER = "ROLE_USER";

    if (emailExists(accountDto.getEmail())) {
      throw new UserAlreadyExistException(
          "There is an account with that email adress: " +
          accountDto.getEmail());
    }

    Role role = roleRepository.findByRoleName(ROLE_USER);
    if (role == null) {
      role = new Role();
      role.setRoleName("ROLE_USER");
      roleRepository.save(role);
    }

    //creat new user and assign role
    Users user = new Users();
    UserRoles userRoles = new UserRoles();
    user.setName(accountDto.getName());
    user.setPassword(accountDto.getPassword());
    user.setEmail(accountDto.getEmail());
    usersRepository.save(user);
    userRoles.setUsers(user);
    userRoles.setRole(role);
    userRolesRepository.save(userRoles);
    return user;
  }

  private boolean emailExists(String email) {

    return usersRepository.findByEmail(email) != null;
  }


  @Override
  public VerificationToken getVerificationToken(String VerificationToken) {
    return tokenRepository.findByToken(VerificationToken);
  }

  @Override
  public void saveRegisteredUser(Users user) {
    usersRepository.save(user);
  }

  @Override
  public void createVerificationToken(Users user, String token) {
    VerificationToken myToken = new VerificationToken();
    myToken.setToken(token);
    myToken.setUser(user);
    tokenRepository.save(myToken);
  }
}
