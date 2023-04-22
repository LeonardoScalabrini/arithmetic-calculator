package com.arithmeticcalculator.ports.out;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.OperationTypes;
import java.util.Optional;

public interface CostOperationRepositoryInterface {
  Optional<CostOperation> findByName(OperationTypes operationTypes);
}
