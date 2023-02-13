package com.arithmeticcalculator.repositories.jpa;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.entities.OperationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationEntityJpaRepository extends JpaRepository<OperationEntity, String> {

  Optional<OperationEntity> findByType(@Param("type") Operations operations);
}
