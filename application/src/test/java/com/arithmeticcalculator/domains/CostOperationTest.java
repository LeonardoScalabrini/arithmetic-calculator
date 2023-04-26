package com.arithmeticcalculator.domains;

import static com.arithmeticcalculator.fixtures.Fixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.arithmeticcalculator.abstracts.UtilsTest;
import com.arithmeticcalculator.domains.ids.CostOperationId;
import com.arithmeticcalculator.fixtures.Fixture;
import org.junit.jupiter.api.Test;

class CostOperationTest extends UtilsTest {

  @Test
  void build() {
    assertClass(CostOperation.class, getCostOperation());
    var id = CostOperationId.newInstance();
    var costOperation = Fixture.getCostOperation(id);
    assertEquals(OperationTypes.SQUARE_ROOT, costOperation.getOperationTypes());
    assertEquals(5, costOperation.getCost());
    assertEquals(id, costOperation.getCostOperationId());
  }
}
