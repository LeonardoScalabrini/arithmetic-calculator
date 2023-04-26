package com.arithmeticcalculator.fixtures;

import static com.arithmeticcalculator.domains.OperationTypes.SQUARE_ROOT;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.Password;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.ids.CostOperationId;
import com.arithmeticcalculator.domains.ids.UserId;

public class Fixture {

  public static final String CRYPTED_STRING =
      "$2a$10$B7ABI0IuyTty.j5JZthusu3RCUMwt6iqbBscm5gzqB3UNPDKclLAS";

  public static User getUser(UserId userId, Password password) {
    return User.newInstance(userId, "email", 20, password);
  }

  public static User getUser() {
    return getUser(UserId.newInstance(), getPassword());
  }

  public static CostOperation getCostOperation(CostOperationId costOperationId) {
    return CostOperation.newInstance(costOperationId, SQUARE_ROOT, 5);
  }

  public static CostOperation getCostOperation() {
    return getCostOperation(CostOperationId.newInstance());
  }

  public static Record<Double> getRecord(User user, CostOperation costOperation) {
    return Record.from(user, costOperation, 2.0);
  }

  public static Record<Double> getRecord() {
    return getRecord(getUser(), getCostOperation());
  }

  public static Password getPassword(String password) {
    return Password.newInstance(password);
  }

  public static Password getPassword() {
    return getPassword("password");
  }
}
