package com.arithmeticcalculator.interfaces.repositories;

import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.interfaces.repositories.entities.UserEntity;
import com.arithmeticcalculator.interfaces.repositories.jpa.UserEntityJpaRepository;
import com.arithmeticcalculator.ports.out.UserRepositoryInterface;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class UserRepositoryImpl implements UserRepositoryInterface {

  private final UserEntityJpaRepository userEntityJpaRepository;

  @Autowired
  public UserRepositoryImpl(@NonNull UserEntityJpaRepository userEntityJpaRepository) {
    this.userEntityJpaRepository = userEntityJpaRepository;
  }

  @Override
  public void save(@NonNull User user) {
    userEntityJpaRepository.save(UserEntity.from(user));
  }

  @Override
  public Optional<User> findByEmail(@NonNull String email) {
    return userEntityJpaRepository.findByEmail(email).map(UserEntity::getUser);
  }
}
