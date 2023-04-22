package com.arithmeticcalculator.ports.in;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.User;

public interface CreateRecordService {
  <T> Record<T> create(User user, CostOperation costOperation, T result);
}
