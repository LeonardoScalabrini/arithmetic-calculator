package com.arithmeticcalculator.dtos;

import com.arithmeticcalculator.domains.Operations;
import com.arithmeticcalculator.entities.RecordEntity;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Value
public class RecordResponseDTO {
  Operations operation;
  double amount;
  double balance;
  String response;
  Date date;

  private RecordResponseDTO(
      @NonNull Operations operation,
      double amount,
      double balance,
      @NonNull String response,
      @NonNull Date date) {
    this.operation = operation;
    this.amount = amount;
    this.balance = balance;
    this.response = response;
    this.date = date;
  }

  private static RecordResponseDTO from(@NonNull RecordEntity r) {
    return new RecordResponseDTO(
        r.getOperation().getType(),
        r.getAmount(),
        r.getUserBalance(),
        r.getOperationResponse(),
        r.getDate());
  }

  public static List<RecordResponseDTO> from(List<RecordEntity> records) {
    return records.parallelStream().map(RecordResponseDTO::from).collect(Collectors.toList());
  }
}
