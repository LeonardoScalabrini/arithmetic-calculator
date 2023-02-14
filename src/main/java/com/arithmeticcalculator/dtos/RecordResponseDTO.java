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
@Builder
public class RecordResponseDTO {
  Operations operation;
  double amount;
  double balance;
  String response;
  Date date;

  public static List<RecordResponseDTO> parseOf(List<RecordEntity> records) {
    return records
        .parallelStream()
        .map(
            r ->
                RecordResponseDTO.builder()
                    .operation(r.getOperation().getType())
                    .amount(r.getAmount())
                    .balance(r.getUserBalance())
                    .response(r.getOperationResponse())
                    .date(r.getDate())
                    .build())
        .collect(Collectors.toList());
  }
}
