package com.arithmeticcalculator.api.v1.controllers;

import com.arithmeticcalculator.api.v1.dtos.RecordResponseDTO;
import com.arithmeticcalculator.domains.BasicOperations;
import com.arithmeticcalculator.usercases.interfaces.PayOperationUserCase;
import java.security.Principal;
import javax.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/operations")
public final class BasicOperationController {

  private final PayOperationUserCase payOperationUserCase;

  @Autowired
  public BasicOperationController(@NonNull PayOperationUserCase payOperationUserCase) {
    this.payOperationUserCase = payOperationUserCase;
  }

  @PostMapping("/addition")
  public @ResponseBody ResponseEntity<RecordResponseDTO> addition(
      @Valid @RequestParam("n1") double n1,
      @Valid @RequestParam("n2") double n2,
      @NonNull Principal principal) {

    return payOperation(principal, BasicOperations.PLUS, n1, n2);
  }

  @PostMapping("/subtraction")
  public @ResponseBody ResponseEntity<RecordResponseDTO> subtraction(
      @Valid @RequestParam("n1") double n1,
      @Valid @RequestParam("n2") double n2,
      @NonNull Principal principal) {

    return payOperation(principal, BasicOperations.MINUS, n1, n2);
  }

  @PostMapping("/multiplication")
  public @ResponseBody ResponseEntity<RecordResponseDTO> multiplication(
      @Valid @RequestParam("n1") double n1,
      @Valid @RequestParam("n2") double n2,
      @NonNull Principal principal) {

    return payOperation(principal, BasicOperations.TIMES, n1, n2);
  }

  @PostMapping("/division")
  public @ResponseBody ResponseEntity<RecordResponseDTO> division(
      @Valid @RequestParam("n1") double n1,
      @Valid @RequestParam("n2") double n2,
      @NonNull Principal principal) {

    return payOperation(principal, BasicOperations.DIVIDE, n1, n2);
  }

  private ResponseEntity<RecordResponseDTO> payOperation(
      @NonNull Principal principal, @NonNull BasicOperations basicOperation, double n1, double n2) {
    return ResponseEntity.ok(
        RecordResponseDTO.from(
            payOperationUserCase.payOperation(
                principal.getName(), basicOperation.getOperationCommand(n1, n2))));
  }
}
