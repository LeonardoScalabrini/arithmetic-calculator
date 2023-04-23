package com.arithmeticcalculator.interfaces.repositories.jpa;

import com.arithmeticcalculator.interfaces.repositories.entities.RecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordEntityJpaRepository extends JpaRepository<RecordEntity, String> {
  Page<RecordEntity> findByUserEmail(String email, Pageable pageable);
}
