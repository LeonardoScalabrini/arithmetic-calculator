package com.arithmeticcalculator.ports.in;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.commands.OperationCommand;

public interface PayOperationService {

  <T> Record<T> payOperation(String email, OperationCommand<T> command);
}
