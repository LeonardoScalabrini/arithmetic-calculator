package com.arithmeticcalculator.api.commons;

import java.io.Serializable;
import java.util.Date;
import lombok.*;

@Getter
@Builder
@Value
public class ApiError implements Serializable {
  Date timestamp = new Date();
  int status;
  String error;
  String message;
}
