package com.arithmeticcalculator.usercases.interfaces.repositories;

import com.arithmeticcalculator.domains.Record;

public interface RecordRepository {
  <T> void save(Record<T> r);
}
