package com.arithmeticcalculator.fixtures;

import static com.arithmeticcalculator.domains.OperationTypes.SQUARE_ROOT;

import com.arithmeticcalculator.domains.*;
import com.arithmeticcalculator.domains.Privileges;
import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.domains.ids.CostOperationId;
import com.arithmeticcalculator.domains.ids.UserId;
import com.arithmeticcalculator.interfaces.repositories.entities.CostOperationEntity;
import com.arithmeticcalculator.interfaces.repositories.entities.RecordEntity;
import com.arithmeticcalculator.interfaces.repositories.entities.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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

  public static UserEntity getUserEntity() {
    return getUserEntity("email", "password");
  }

  public static UserEntity getUserEntity(String email, String password) {
    return UserEntity.builder()
        .email(email)
        .balance(20)
        .password(password)
        .privileges(Privileges.USER)
        .id(UUID.randomUUID().toString())
        .build();
  }

  public static CostOperationEntity getCostOperationEntity() {
    return CostOperationEntity.from(getCostOperation());
  }

  public static RecordEntity getRecordEntity(UserEntity userEntity) {
    return RecordEntity.from(userEntity, getRecord());
  }

  public static RecordEntity getRecordEntity() {
    return getRecordEntity(getUserEntity());
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
