package com.arithmeticcalculator.queries.interfaces;

import com.arithmeticcalculator.entities.RecordEntity;
import java.util.List;

public interface RecordPaginationQuery {
  List<RecordEntity> findBy(String email, int page, int size);
}
