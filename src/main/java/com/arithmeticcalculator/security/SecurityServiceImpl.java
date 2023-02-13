package com.arithmeticcalculator.security;

import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.repositories.jpa.UserEntityJpaRepository;
import com.arithmeticcalculator.security.interfaces.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

  private final UserEntityJpaRepository userEntityJpaRepository;

  @Autowired
  public SecurityServiceImpl(UserEntityJpaRepository userEntityJpaRepository) {
    this.userEntityJpaRepository = userEntityJpaRepository;
  }

  @Override
  public void createUser(String email, String password) {
    var user =
        UserEntity.builder().email(email).password(password).privileges(Privileges.USER).build();
    userEntityJpaRepository.save(user);
  }
}
