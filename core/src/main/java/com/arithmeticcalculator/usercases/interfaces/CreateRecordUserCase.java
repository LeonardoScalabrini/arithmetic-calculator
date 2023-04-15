package com.arithmeticcalculator.usercases.interfaces;

import com.arithmeticcalculator.domains.Operation;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.User;

public interface CreateRecordUserCase {
  <T> Record<T> create(User user, Operation operation, T result);
}
