package com.arithmeticcalculator.fixtures;

import static com.arithmeticcalculator.domains.OperationTypes.SQUARE_ROOT;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.Password;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.ids.CostOperationId;
import com.arithmeticcalculator.domains.ids.UserId;

public class Fixture {
  public static User getUser() {
    return User.builder()
        .userId(UserId.newInstance())
        .email("email")
        .balance(20)
        .password(Password.newInstance("password"))
        .build();
  }

  public static CostOperation getCostOperation() {
    return CostOperation.builder()
        .costOperationId(CostOperationId.newInstance())
        .operationTypes(SQUARE_ROOT)
        .cost(5)
        .build();
  }

  public static Record<Double> getRecord() {
    return Record.from(getUser(), getCostOperation(), 2.0);
  }
}
