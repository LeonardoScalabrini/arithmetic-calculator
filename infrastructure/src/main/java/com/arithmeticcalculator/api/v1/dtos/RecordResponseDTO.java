package com.arithmeticcalculator.api.v1.dtos;

import com.arithmeticcalculator.domains.Record;
import com.arithmeticcalculator.interfaces.repositories.entities.RecordEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.*;

@Value
public class RecordResponseDTO {
  String operation;
  double amount;
  double balance;
  String response;
  String date;

  private RecordResponseDTO(
      @NonNull String operation,
      double amount,
      double balance,
      @NonNull String response,
      @NonNull String date) {
    this.operation = operation;
    this.amount = amount;
    this.balance = balance;
    this.response = response;
    this.date = date;
  }

  public static <T> RecordResponseDTO from(@NonNull Record<T> r) {
    return new RecordResponseDTO(
        r.getOperationTypes().name(),
        r.getAmount(),
        r.getBalance(),
        r.getOperationResult().toString(),
        r.getDate().toString());
  }

  public static RecordResponseDTO from(@NonNull RecordEntity r) {
    return new RecordResponseDTO(
        r.getOperation().name(),
        r.getAmount(),
        r.getUserBalance(),
        r.getOperationResponse(),
        r.getDate().toString());
  }

  public static List<RecordResponseDTO> from(@NonNull List<RecordEntity> records) {
    return records.stream().map(RecordResponseDTO::from).collect(Collectors.toList());
  }
}
