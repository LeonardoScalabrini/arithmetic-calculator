package com.arithmeticcalculator.repositories;

import com.arithmeticcalculator.domains.Operation;
import com.arithmeticcalculator.domains.OperationTypes;
import com.arithmeticcalculator.entities.OperationEntity;
import com.arithmeticcalculator.repositories.jpa.OperationEntityJpaRepository;
import com.arithmeticcalculator.usercases.interfaces.repositories.OperationRepository;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class OperationRepositoryImpl implements OperationRepository {

  private final OperationEntityJpaRepository operationEntityJpaRepository;

  @Autowired
  public OperationRepositoryImpl(
      @NonNull OperationEntityJpaRepository operationEntityJpaRepository) {
    this.operationEntityJpaRepository = operationEntityJpaRepository;
  }

  @Override
  public Optional<Operation> findByName(@NonNull OperationTypes operationTypes) {
    return operationEntityJpaRepository
        .findByType(operationTypes)
        .map(OperationEntity::getOperation);
  }
}
