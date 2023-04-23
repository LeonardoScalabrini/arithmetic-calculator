package com.arithmeticcalculator.interfaces.repositories.jpa;

import static com.arithmeticcalculator.fixtures.Fixture.getRecordEntity;
import static com.arithmeticcalculator.fixtures.Fixture.getUserEntity;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class RecordEntityJpaRepositoryTest {

  @Autowired private RecordEntityJpaRepository recordEntityJpaRepository;
  @Autowired private UserEntityJpaRepository userEntityJpaRepository;

  @Test
  void save() {
    var record = getRecordEntity();
    userEntityJpaRepository.save(record.getUser());
    var result = recordEntityJpaRepository.save(record);
    assertEquals(record, result);
  }

  @Test
  void findByUserEmail() {
    var user = userEntityJpaRepository.save(getUserEntity());
    var save1 = recordEntityJpaRepository.save(getRecordEntity(user));
    var save2 = recordEntityJpaRepository.save(getRecordEntity(user));
    var result =
        recordEntityJpaRepository
            .findByUserEmail(user.getEmail(), PageRequest.ofSize(10))
            .getContent();
    assertEquals(2, result.size());
    assertTrue(result.stream().allMatch(r -> r.getUser().equals(user)));
    assertTrue(result.stream().anyMatch(r -> r.getId().equals(save1.getId())));
    assertTrue(result.stream().anyMatch(r -> r.getId().equals(save2.getId())));
    assertTrue(
        recordEntityJpaRepository
            .findByUserEmail("empty", PageRequest.ofSize(10))
            .getContent()
            .isEmpty());
  }
}
