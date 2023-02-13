package com.arithmeticcalculator.domains.interfaces;

import com.arithmeticcalculator.domains.Operation;
import com.arithmeticcalculator.domains.User;

public interface CreateRecordUserCase {
  <T> void create(User user, Operation operation, T result);
}
