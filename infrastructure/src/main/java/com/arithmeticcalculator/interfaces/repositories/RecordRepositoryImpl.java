package com.arithmeticcalculator.interfaces.repositories;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.interfaces.repositories.entities.RecordEntity;
import com.arithmeticcalculator.interfaces.repositories.jpa.RecordEntityJpaRepository;
import com.arithmeticcalculator.interfaces.repositories.jpa.UserEntityJpaRepository;
import com.arithmeticcalculator.ports.out.RecordRepositoryInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecordRepositoryImpl implements RecordRepositoryInterface {
  private final UserEntityJpaRepository userEntityJpaRepository;
  private final RecordEntityJpaRepository recordEntityJpaRepository;

  @Autowired
  public RecordRepositoryImpl(
      @NonNull UserEntityJpaRepository userEntityJpaRepository,
      @NonNull RecordEntityJpaRepository recordEntityJpaRepository) {
    this.userEntityJpaRepository = userEntityJpaRepository;
    this.recordEntityJpaRepository = recordEntityJpaRepository;
  }

  @Override
  public <T> void save(@NonNull Record<T> r) {
    userEntityJpaRepository
        .findById(r.getUserId().getId())
        .ifPresent(userEntity -> recordEntityJpaRepository.save(RecordEntity.from(userEntity, r)));
  }
}
