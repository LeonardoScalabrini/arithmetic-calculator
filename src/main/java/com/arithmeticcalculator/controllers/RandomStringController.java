package com.arithmeticcalculator.controllers;

import com.arithmeticcalculator.domains.interfaces.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("/operations")
public class RandomStringController {

  private final RandomService randomService;

  @Autowired
  public RandomStringController(RandomService randomService) {
    this.randomService = randomService;
  }

  @GetMapping("/random-string")
  public @ResponseBody ResponseEntity<String> randomString() {
    try {
      var optional = randomService.stringGenerator();
      return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    } catch (RuntimeException e) {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, "Oooppss! Something is not working very well! :(");
    }
  }
}
