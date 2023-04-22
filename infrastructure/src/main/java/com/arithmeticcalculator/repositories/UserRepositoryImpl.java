package com.arithmeticcalculator.repositories;

import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.ports.out.UserRepositoryInterface;
import com.arithmeticcalculator.repositories.jpa.UserEntityJpaRepository;
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
    userEntityJpaRepository
        .findById(user.getUserId())
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
