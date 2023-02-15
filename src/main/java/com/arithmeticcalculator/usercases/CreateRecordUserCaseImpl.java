package com.arithmeticcalculator.usercases;

import com.arithmeticcalculator.domains.Operation;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.interfaces.CreateRecordUserCase;
import com.arithmeticcalculator.domains.interfaces.RecordRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateRecordUserCaseImpl implements CreateRecordUserCase {

  private final RecordRepository recordRepository;

  @Autowired
  public CreateRecordUserCaseImpl(RecordRepository recordRepository) {
    this.recordRepository = recordRepository;
  }

  @Override
  public <T> void create(@NonNull User user, @NonNull Operation operation, @NonNull T result) {
    recordRepository.save(Record.from(user, operation, result));
  }
}
