package com.arithmeticcalculator.usercases.interfaces.repositories;

import com.arithmeticcalculator.domains.Operation;
import com.arithmeticcalculator.domains.OperationTypes;
import java.util.Optional;

public interface OperationRepository {
  Optional<Operation> findByName(OperationTypes operationTypes);
}
