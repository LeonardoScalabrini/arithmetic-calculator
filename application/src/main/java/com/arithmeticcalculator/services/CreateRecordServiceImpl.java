package com.arithmeticcalculator.services;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.ports.in.CreateRecordService;
import com.arithmeticcalculator.ports.out.RecordRepositoryInterface;
import lombok.NonNull;
import lombok.Value;

@Value
public class CreateRecordServiceImpl implements CreateRecordService {
  RecordRepositoryInterface recordRepository;

  private CreateRecordServiceImpl(@NonNull RecordRepositoryInterface recordRepository) {
    this.recordRepository = recordRepository;
  }

  @Override
  public <T> Record<T> create(
      @NonNull User user, @NonNull CostOperation costOperation, @NonNull T result) {
    var r = Record.from(user, costOperation, result);
    recordRepository.save(r);
    return r;
  }

  public static CreateRecordService newInstance(
      @NonNull RecordRepositoryInterface recordRepository) {
    return new CreateRecordServiceImpl(recordRepository);
  }
}
