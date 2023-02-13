package com.arithmeticcalculator.fixtures;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.File;
import java.io.IOException;

public class Fixture {
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
