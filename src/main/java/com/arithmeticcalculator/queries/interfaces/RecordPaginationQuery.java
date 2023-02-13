package com.arithmeticcalculator.queries.interfaces;

import com.arithmeticcalculator.entities.RecordEntity;
import java.util.List;

public interface RecordPaginationQuery {
  List<RecordEntity> findBy(int page, int size);
}
