package com.arithmeticcalculator.controllers;

import com.arithmeticcalculator.entities.RecordEntity;
import com.arithmeticcalculator.queries.interfaces.RecordPaginationQuery;
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
@RequestMapping("/")
public class QueryController {

  private final RecordPaginationQuery recordPaginationQuery;

  @Autowired
  public QueryController(RecordPaginationQuery recordPaginationQuery) {
    this.recordPaginationQuery = recordPaginationQuery;
  }

  @GetMapping("/records/search")
  public ResponseEntity<List<RecordEntity>> findBy(
      @Valid @RequestParam("page") int page, @Valid @RequestParam("size") int size) {
    var result = recordPaginationQuery.findBy(page, size);
    if (result.isEmpty()) return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    return ResponseEntity.ok(result);
  }
}
