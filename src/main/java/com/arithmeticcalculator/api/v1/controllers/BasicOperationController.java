package com.arithmeticcalculator.api.v1.controllers;

import com.arithmeticcalculator.api.v1.dtos.RecordResponseDTO;
import com.arithmeticcalculator.domains.BasicOperations;
import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.usercases.interfaces.PayOperationUserCase;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/operations")
public class BasicOperationController {

  private final PayOperationUserCase payOperationUserCase;

  @Autowired
  public BasicOperationController(PayOperationUserCase payOperationUserCase) {
    this.payOperationUserCase = payOperationUserCase;
  }

  @PostMapping("/addition")
  public @ResponseBody ResponseEntity<RecordResponseDTO> addition(
      @Valid @RequestParam("n1") double n1,
      @Valid @RequestParam("n2") double n2,
      Principal principal)
      throws OperationException {
    return payOperation(principal, BasicOperations.PLUS, n1, n2);
  }

  @PostMapping("/subtraction")
  public @ResponseBody ResponseEntity<RecordResponseDTO> subtraction(
      @Valid @RequestParam("n1") double n1,
      @Valid @RequestParam("n2") double n2,
      Principal principal)
      throws OperationException {
    return payOperation(principal, BasicOperations.MINUS, n1, n2);
  }

  @PostMapping("/multiplication")
  public @ResponseBody ResponseEntity<RecordResponseDTO> multiplication(
      @Valid @RequestParam("n1") double n1,
      @Valid @RequestParam("n2") double n2,
      Principal principal)
      throws OperationException {
    return payOperation(principal, BasicOperations.TIMES, n1, n2);
  }

  @PostMapping("/division")
  public @ResponseBody ResponseEntity<RecordResponseDTO> division(
      @Valid @RequestParam("n1") double n1,
      @Valid @RequestParam("n2") double n2,
      Principal principal)
      throws OperationException {
    return payOperation(principal, BasicOperations.DIVIDE, n1, n2);
  }

  private ResponseEntity<RecordResponseDTO> payOperation(
      Principal principal, BasicOperations basicOperation, double n1, double n2)
      throws OperationException {
    var command = basicOperation.getOperationCommand(n1, n2);
    return ResponseEntity.ok(
        RecordResponseDTO.from(payOperationUserCase.payOperation(principal.getName(), command)));
  }
}
