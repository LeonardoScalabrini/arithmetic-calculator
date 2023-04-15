package com.arithmeticcalculator.api.v1.controllers;

import com.arithmeticcalculator.api.v1.dtos.RecordResponseDTO;
import com.arithmeticcalculator.usercases.interfaces.PayOperationUserCase;
import com.arithmeticcalculator.usercases.interfaces.RandomStringUserCase;
import java.security.Principal;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/operations")
public final class RandomStringController {
  private final RandomStringUserCase randomStringUserCase;
  private final PayOperationUserCase payOperationUserCase;

  @Autowired
  public RandomStringController(
      @NonNull RandomStringUserCase randomStringUserCase,
      @NonNull PayOperationUserCase payOperationUserCase) {
    this.randomStringUserCase = randomStringUserCase;
    this.payOperationUserCase = payOperationUserCase;
  }

  @GetMapping("/random-string")
  public @ResponseBody ResponseEntity<RecordResponseDTO> randomString(
      @NonNull Principal principal) {
    return ResponseEntity.ok(
        RecordResponseDTO.from(
            payOperationUserCase.payOperation(principal.getName(), randomStringUserCase)));
  }
}
