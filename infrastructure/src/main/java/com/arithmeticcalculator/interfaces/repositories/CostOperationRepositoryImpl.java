package com.arithmeticcalculator.interfaces.repositories;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.interfaces.repositories.entities.CostOperationEntity;
import com.arithmeticcalculator.interfaces.repositories.jpa.CostOperationEntityJpaRepository;
import com.arithmeticcalculator.ports.out.CostOperationRepositoryInterface;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CostOperationRepositoryImpl implements CostOperationRepositoryInterface {

  private final CostOperationEntityJpaRepository costOperationEntityJpaRepository;

  @Autowired
  public CostOperationRepositoryImpl(
      @NonNull CostOperationEntityJpaRepository costOperationEntityJpaRepository) {
    this.costOperationEntityJpaRepository = costOperationEntityJpaRepository;
  }

  @Override
  public Optional<CostOperation> findByName(@NonNull OperationTypes operationTypes) {
    return costOperationEntityJpaRepository
        .findByType(operationTypes)
        .map(CostOperationEntity::getOperation);
  }
}
