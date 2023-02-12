package com.arithmeticcalculator.controllers;

import com.arithmeticcalculator.domains.SquareRootOperation;
import com.arithmeticcalculator.domains.exceptions.CalculatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("/operations/square-root")
public class SquareRootController {
  private final SquareRootOperation squareRootOperation = SquareRootOperation.getInstance();

  @PostMapping("/{radicand}")
  public @ResponseBody ResponseEntity<Double> sqrt(
      @PathVariable(value = "radicand") double radicand) {
    try {
      return ResponseEntity.ok(squareRootOperation.sqrt(radicand));
    } catch (CalculatorException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }
}
