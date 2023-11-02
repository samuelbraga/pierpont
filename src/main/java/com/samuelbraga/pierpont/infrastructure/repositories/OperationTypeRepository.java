package com.samuelbraga.pierpont.infrastructure.repositories;

import com.samuelbraga.pierpont.infrastructure.repositories.entities.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository
  extends JpaRepository<OperationType, Long> {}
