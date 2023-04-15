package com.arithmeticcalculator.queries;

import com.arithmeticcalculator.entities.RecordEntity;
import com.arithmeticcalculator.queries.interfaces.RecordPaginationQuery;
import com.arithmeticcalculator.repositories.jpa.RecordEntityJpaRepository;
import java.util.List;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public final class RecordPaginationQueryImpl implements RecordPaginationQuery {
  private final RecordEntityJpaRepository recordEntityJpaRepository;

  @Autowired
  public RecordPaginationQueryImpl(@NonNull RecordEntityJpaRepository recordEntityJpaRepository) {
    this.recordEntityJpaRepository = recordEntityJpaRepository;
  }

  public List<RecordEntity> findBy(@NonNull String email, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());
    Page<RecordEntity> recordsPage = recordEntityJpaRepository.findByUserEmail(email, pageable);
    return recordsPage.getContent();
  }
}
