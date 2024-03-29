package com.arithmeticcalculator.interfaces.repositories.jpa;

import com.arithmeticcalculator.interfaces.repositories.entities.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityJpaRepository extends JpaRepository<UserEntity, String> {
  Optional<UserEntity> findByEmail(@Param("email") String email);
}
