package com.arithmeticcalculator.repositories;

import com.arithmeticcalculator.domains.Operation;
import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.interfaces.OperationRepository;
import com.arithmeticcalculator.entities.OperationEntity;
import com.arithmeticcalculator.repositories.jpa.OperationEntityJpaRepository;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationRepositoryImpl implements OperationRepository {

  private final OperationEntityJpaRepository operationEntityJpaRepository;

  @Autowired
  public OperationRepositoryImpl(OperationEntityJpaRepository operationEntityJpaRepository) {
    this.operationEntityJpaRepository = operationEntityJpaRepository;
  }

  @Override
  public Optional<Operation> findByName(@NonNull Operations operations) {
    return operationEntityJpaRepository.findByType(operations).map(OperationEntity::getOperation);
  }
}
