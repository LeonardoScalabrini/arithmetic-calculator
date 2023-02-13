package com.arithmeticcalculator.domains.interfaces;

import com.arithmeticcalculator.domains.Operation;
import com.arithmeticcalculator.domains.Operations;
import java.util.Optional;

public interface OperationRepository {
  Optional<Operation> findByName(Operations operations);
}
