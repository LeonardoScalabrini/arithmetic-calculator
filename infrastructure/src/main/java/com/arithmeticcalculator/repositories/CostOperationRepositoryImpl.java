package com.arithmeticcalculator.repositories;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.entities.OperationEntity;
import com.arithmeticcalculator.ports.out.CostOperationRepositoryInterface;
import com.arithmeticcalculator.repositories.jpa.OperationEntityJpaRepository;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class CostOperationRepositoryImpl implements CostOperationRepositoryInterface {

  private final OperationEntityJpaRepository operationEntityJpaRepository;

  @Autowired
  public CostOperationRepositoryImpl(
      @NonNull OperationEntityJpaRepository operationEntityJpaRepository) {
    this.operationEntityJpaRepository = operationEntityJpaRepository;
  }

  @Override
  public Optional<CostOperation> findByName(@NonNull OperationTypes operationTypes) {
    return operationEntityJpaRepository
        .findByType(operationTypes)
        .map(OperationEntity::getOperation);
  }
}
