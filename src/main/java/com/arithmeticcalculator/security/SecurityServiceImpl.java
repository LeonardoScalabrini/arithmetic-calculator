package com.arithmeticcalculator.security;

import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.repositories.UserEntityJpaRepository;
import com.arithmeticcalculator.security.interfaces.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

  private final UserEntityJpaRepository UserEntityjpaRepository;

  @Autowired
  public SecurityServiceImpl(UserEntityJpaRepository userEntityjpaRepository) {
    UserEntityjpaRepository = userEntityjpaRepository;
  }

  @Override
  public void createUser(String email, String password) {
    var user =
        UserEntity.builder().email(email).password(password).privileges(Privileges.USER).build();
    UserEntityjpaRepository.save(user);
  }
}
