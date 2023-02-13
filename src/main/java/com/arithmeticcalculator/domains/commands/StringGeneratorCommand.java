package com.arithmeticcalculator.domains.commands;

import com.arithmeticcalculator.domains.exceptions.OperationException;
import com.arithmeticcalculator.domains.interfaces.OperationCommand;
import com.arithmeticcalculator.domains.interfaces.StringGeneratorService;
import lombok.NonNull;

public class StringGeneratorCommand implements OperationCommand<String> {

  private final StringGeneratorService stringGeneratorService;

  public StringGeneratorCommand(@NonNull StringGeneratorService stringGeneratorService) {
    this.stringGeneratorService = stringGeneratorService;
  }

  @Override
  public String execute() throws OperationException {
    return stringGeneratorService
        .stringGenerator()
        .orElseThrow(() -> OperationException.withMessage("String generator not able!"));
  }
}
