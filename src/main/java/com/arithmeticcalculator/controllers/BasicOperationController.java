package com.arithmeticcalculator.controllers;

import com.arithmeticcalculator.domains.BasicOperation;
import com.arithmeticcalculator.domains.exceptions.CalculatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("/operations")
public class BasicOperationController {
  @PostMapping("/{operation}")
  public @ResponseBody ResponseEntity<Double> calc(
      @PathVariable(value = "operation") String operation,
      @RequestParam("n1") double n1,
      @RequestParam("n2") double n2) {
    try {
      BasicOperation basicOperation = BasicOperation.valueOf(operation.toUpperCase());
      return ResponseEntity.ok(basicOperation.calc(n1, n2));
    } catch (CalculatorException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operation should be valid!");
    }
  }
}
