package com.arithmeticcalculator.queries;

import com.arithmeticcalculator.interfaces.repositories.entities.RecordEntity;
import java.util.List;

public interface RecordPaginationQuery {
  List<RecordEntity> findBy(String email, int page, int size);
}
