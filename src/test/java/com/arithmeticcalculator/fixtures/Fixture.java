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

public class Fixture {

  public static User getUser() {
    return User.builder().email("email").balance(20).build();
  }

  public static Operation getOperation() {
    return Operation.builder().cost(5).operations(Operations.SQUARE_ROOT).build();
  }

  public static Record<Double> getRecord() {
    return Record.from(getUser(), getOperation(), 2.0);
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
    return OperationEntity.from(getOperation());
  }

  public static RecordEntity getRecordEntity() {
    return RecordEntity.from(getUserEntity(), getOperationEntity(), getRecord());
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
