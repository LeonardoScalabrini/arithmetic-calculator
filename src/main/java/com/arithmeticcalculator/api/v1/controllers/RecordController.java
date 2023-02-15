package com.arithmeticcalculator.api.v1.controllers;

import com.arithmeticcalculator.api.v1.dtos.RecordResponseDTO;
import com.arithmeticcalculator.queries.interfaces.RecordPaginationQuery;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/records")
public class RecordController {

  private final RecordPaginationQuery recordPaginationQuery;

  @Autowired
  public RecordController(RecordPaginationQuery recordPaginationQuery) {
    this.recordPaginationQuery = recordPaginationQuery;
  }

  @GetMapping("/search")
  public ResponseEntity<List<RecordResponseDTO>> findBy(
      @Valid @RequestParam("page") int page,
      @Valid @RequestParam("size") int size,
      Principal principal) {
    var result =
        RecordResponseDTO.from(recordPaginationQuery.findBy(principal.getName(), page, size));
    if (result.isEmpty()) return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    return ResponseEntity.ok(result);
  }
}
