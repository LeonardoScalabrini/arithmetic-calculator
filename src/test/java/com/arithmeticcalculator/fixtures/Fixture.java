package com.arithmeticcalculator.fixtures;

import com.arithmeticcalculator.domains.Operation;
import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.entities.OperationEntity;
import com.arithmeticcalculator.entities.RecordEntity;
import com.arithmeticcalculator.entities.UserEntity;
import com.arithmeticcalculator.security.Privileges;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Fixture {

  public static final Date DATE = new Date();

  public static User getUser() {
    return User.builder().email("email").balance(20).build();
  }

  public static Operation getOperation() {
    return Operation.builder().cost(5).operations(Operations.SQUARE_ROOT).build();
  }

  public static Record<Double> getRecord() {
    return Record.<Double>builder()
        .amount(5)
        .balance(20)
        .operation(getOperation())
        .user(getUser())
        .operationResult(2.0)
        .build();
  }

  public static UserEntity getUserEntity() {
    return UserEntity.builder()
        .email("email")
        .balance(20)
        .password("password")
        .privileges(Privileges.USER)
        .build();
  }

  public static OperationEntity getOperationEntity() {
    return OperationEntity.builder().type(Operations.DIVISION).cost(5).build();
  }

  public static RecordEntity getRecordEntity() {
    return RecordEntity.builder()
        .amount(5)
        .userBalance(20)
        .operation(getOperationEntity())
        .user(getUserEntity())
        .operationResponse("2.0")
        .date(DATE)
        .build();
  }

  public static final String CREATE_USER_REQUEST_PATH =
      "src/test/resources/jsons/userCreateRequest.json";

  public static <T> T readValue(String path, Class<T> type) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(new File(path), type);
  }

  public static <T> String fromJson(T anObject) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(anObject);
  }
}
