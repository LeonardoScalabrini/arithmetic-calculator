package com.arithmeticcalculator.api.v1.controllers;

import com.arithmeticcalculator.api.v1.dtos.RecordResponseDTO;
import com.arithmeticcalculator.ports.in.PayCostOperationService;
import com.arithmeticcalculator.ports.in.RandomStringService;
import java.security.Principal;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/operations")
public final class RandomStringController {
  private final RandomStringService randomStringService;
  private final PayCostOperationService payCostOperationService;

  @Autowired
  public RandomStringController(
      @NonNull RandomStringService randomStringService,
      @NonNull PayCostOperationService payCostOperationService) {
    this.randomStringService = randomStringService;
    this.payCostOperationService = payCostOperationService;
  }

  @GetMapping("/random-string")
  public @ResponseBody ResponseEntity<RecordResponseDTO> randomString(
      @NonNull Principal principal) {
    return ResponseEntity.ok(
        RecordResponseDTO.from(
            payCostOperationService.payOperation(principal.getName(), randomStringService)));
  }
}
