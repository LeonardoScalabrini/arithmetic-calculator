package com.arithmeticcalculator.queries;

import static org.junit.jupiter.api.Assertions.*;

import com.arithmeticcalculator.entities.RecordEntity;
import com.arithmeticcalculator.fixtures.Fixture;
import com.arithmeticcalculator.repositories.jpa.RecordEntityJpaRepository;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RecordPaginationQueryImplTest {

  @Mock private RecordEntityJpaRepository recordRepository;
  @InjectMocks private RecordPaginationQueryImpl recordPaginationQuery;

  private final RecordEntity record = Fixture.getRecordEntity();

  @BeforeEach
  void setUp() {
    Mockito.when(recordRepository.findByUserEmail(Mockito.eq("email"), Mockito.any(Pageable.class)))
        .thenReturn(new PageImpl<>(Collections.singletonList(record)));
  }

  @Test
  void findBy() {
    var result = recordPaginationQuery.findBy("email", 1, 2);
    assertEquals(Collections.singletonList(record), result);
    Mockito.verify(recordRepository, Mockito.times(1))
        .findByUserEmail(Mockito.eq("email"), Mockito.any(Pageable.class));
  }
}
