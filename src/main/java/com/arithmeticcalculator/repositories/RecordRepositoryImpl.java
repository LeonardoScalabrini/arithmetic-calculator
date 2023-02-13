package com.arithmeticcalculator.repositories;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.interfaces.RecordRepository;
import com.arithmeticcalculator.entities.RecordEntity;
import com.arithmeticcalculator.repositories.jpa.OperationEntityJpaRepository;
import com.arithmeticcalculator.repositories.jpa.RecordEntityJpaRepository;
import com.arithmeticcalculator.repositories.jpa.UserEntityJpaRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecordRepositoryImpl implements RecordRepository {
  private final UserEntityJpaRepository userEntityJpaRepository;
  private final OperationEntityJpaRepository operationEntityJpaRepository;
  private final RecordEntityJpaRepository recordEntityJpaRepository;

  @Autowired
  public RecordRepositoryImpl(
      UserEntityJpaRepository userEntityJpaRepository,
      OperationEntityJpaRepository operationEntityJpaRepository,
      RecordEntityJpaRepository recordEntityJpaRepository) {
    this.userEntityJpaRepository = userEntityJpaRepository;
    this.operationEntityJpaRepository = operationEntityJpaRepository;
    this.recordEntityJpaRepository = recordEntityJpaRepository;
  }

  @Override
  public <T> void save(@NonNull Record<T> record) {
    userEntityJpaRepository
        .findByEmail(record.getUser().getEmail())
        .ifPresent(
            userEntity ->
                operationEntityJpaRepository
                    .findByType(record.getOperation().getOperations())
                    .ifPresent(
                        operationEntity -> {
                          var recordEntity =
                              RecordEntity.builder()
                                  .date(record.getDate())
                                  .user(userEntity)
                                  .userBalance(record.getBalance())
                                  .amount(record.getAmount())
                                  .operation(operationEntity)
                                  .operationResponse(record.getOperationResult().toString())
                                  .build();
                          recordEntityJpaRepository.save(recordEntity);
                        }));
  }
}
