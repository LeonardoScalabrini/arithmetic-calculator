package com.arithmeticcalculator.interfaces.repositories.jpa;

import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.interfaces.repositories.entities.CostOperationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CostOperationEntityJpaRepository
    extends JpaRepository<CostOperationEntity, String> {
  Optional<CostOperationEntity> findByType(@Param("type") OperationTypes operationTypes);
}
