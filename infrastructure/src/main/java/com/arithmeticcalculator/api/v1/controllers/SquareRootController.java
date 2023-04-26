package com.arithmeticcalculator.api.v1.controllers;

import com.arithmeticcalculator.api.v1.dtos.RecordResponseDTO;
import com.arithmeticcalculator.domains.commands.SquareRootCommand;
import com.arithmeticcalculator.ports.in.PayCostOperationService;
import java.security.Principal;
import javax.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/operations")
public final class SquareRootController {
  private final PayCostOperationService payCostOperationService;

  @Autowired
  public SquareRootController(@NonNull PayCostOperationService payCostOperationService) {
    this.payCostOperationService = payCostOperationService;
  }

  @PostMapping("/square-root/{radicand}")
  public @ResponseBody ResponseEntity<RecordResponseDTO> sqrt(
      @Valid @PathVariable(value = "radicand") double radicand, @NonNull Principal principal) {
    return ResponseEntity.ok(
        RecordResponseDTO.from(
            payCostOperationService.payOperation(
                principal.getName(), SquareRootCommand.newInstance(radicand))));
  }
}
