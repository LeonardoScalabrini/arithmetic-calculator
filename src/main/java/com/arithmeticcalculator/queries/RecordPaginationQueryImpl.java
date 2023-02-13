package com.arithmeticcalculator.queries;

import com.arithmeticcalculator.entities.RecordEntity;
import com.arithmeticcalculator.queries.interfaces.RecordPaginationQuery;
import com.arithmeticcalculator.repositories.jpa.RecordEntityJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class RecordPaginationQueryImpl implements RecordPaginationQuery {
  private final RecordEntityJpaRepository recordEntityJpaRepository;

  @Autowired
  public RecordPaginationQueryImpl(RecordEntityJpaRepository recordEntityJpaRepository) {
    this.recordEntityJpaRepository = recordEntityJpaRepository;
  }

  public List<RecordEntity> findBy(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
    return recordEntityJpaRepository.findAll(pageable).toList();
  }
}
