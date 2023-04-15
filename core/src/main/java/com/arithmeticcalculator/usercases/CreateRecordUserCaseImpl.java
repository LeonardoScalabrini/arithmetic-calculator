package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.Operation;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.usercases.interfaces.CreateRecordUserCase;
import com.arithmeticcalculator.usercases.interfaces.repositories.RecordRepository;
import lombok.NonNull;

public final class CreateRecordUserCaseImpl implements CreateRecordUserCase {

  private final RecordRepository recordRepository;

  public CreateRecordUserCaseImpl(@NonNull RecordRepository recordRepository) {
    this.recordRepository = recordRepository;
  }

  @Override
  public <T> Record<T> create(@NonNull User user, @NonNull Operation operation, @NonNull T result) {
    var r = Record.from(user, operation, result);
    recordRepository.save(Record.from(user, operation, result));
    return r;
  }
}
