package com.arithmeticcalculator.repositories.jpa;

import com.arithmeticcalculator.entities.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordEntityJpaRepository extends JpaRepository<RecordEntity, String> {}
