package com.arithmeticcalculator.api.commons;

import java.time.Instant;
import lombok.*;

@Getter
@Builder
@Value
@RequiredArgsConstructor
public class ApiError {
  Instant timestamp = Instant.now();
  int status;
  String error;
  String message;
}
