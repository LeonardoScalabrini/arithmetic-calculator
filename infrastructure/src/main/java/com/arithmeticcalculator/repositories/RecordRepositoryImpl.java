package com.arithmeticcalculator.repositories;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.entities.RecordEntity;
import com.arithmeticcalculator.ports.out.RecordRepositoryInterface;
import com.arithmeticcalculator.repositories.jpa.OperationEntityJpaRepository;
import com.arithmeticcalculator.repositories.jpa.RecordEntityJpaRepository;
import com.arithmeticcalculator.repositories.jpa.UserEntityJpaRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class RecordRepositoryImpl implements RecordRepositoryInterface {
  private final UserEntityJpaRepository userEntityJpaRepository;
  private final OperationEntityJpaRepository operationEntityJpaRepository;
  private final RecordEntityJpaRepository recordEntityJpaRepository;

  @Autowired
  public RecordRepositoryImpl(
      @NonNull UserEntityJpaRepository userEntityJpaRepository,
      @NonNull OperationEntityJpaRepository operationEntityJpaRepository,
      @NonNull RecordEntityJpaRepository recordEntityJpaRepository) {
    this.userEntityJpaRepository = userEntityJpaRepository;
    this.operationEntityJpaRepository = operationEntityJpaRepository;
    this.recordEntityJpaRepository = recordEntityJpaRepository;
  }

  @Override
  public <T> void save(@NonNull Record<T> r) {
    userEntityJpaRepository
        .findById(r.getUserId())
        .ifPresent(userEntity -> recordEntityJpaRepository.save(RecordEntity.from(userEntity, r)));
  }
}
