package com.arithmeticcalculator.ports.out;

import com.arithmeticcalculator.domains.Record;

public interface RecordRepositoryInterface {
  <T> void save(Record<T> r);
}
