package com.arithmeticcalculator.repositories.jpa;

import com.arithmeticcalculator.domains.ids.UserId;
import com.arithmeticcalculator.entities.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityJpaRepository extends JpaRepository<UserEntity, UserId> {
  Optional<UserEntity> findByEmail(@Param("email") String email);
}
