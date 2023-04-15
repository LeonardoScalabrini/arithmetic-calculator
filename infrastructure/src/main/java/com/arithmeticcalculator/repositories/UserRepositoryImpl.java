package com.arithmeticcalculator.repositories;

import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.repositories.jpa.UserEntityJpaRepository;
import com.arithmeticcalculator.usercases.interfaces.repositories.UserRepository;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class UserRepositoryImpl implements UserRepository {

  private final UserEntityJpaRepository userEntityJpaRepository;

  @Autowired
  public UserRepositoryImpl(@NonNull UserEntityJpaRepository userEntityJpaRepository) {
    this.userEntityJpaRepository = userEntityJpaRepository;
  }

  @Override
  public void save(@NonNull User user) {
    userEntityJpaRepository
        .findByEmail(user.getEmail())
        .ifPresent(
            userEntity -> {
              userEntity.setBalance(user.getBalance());
              userEntityJpaRepository.save(userEntity);
            });
  }

  @Override
  public Optional<User> findByEmail(@NonNull String email) {
    return userEntityJpaRepository.findByEmail(email).map(UserEntity::getUser);
  }
}
