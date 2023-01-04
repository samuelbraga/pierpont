package com.samuelbraga.pierpont.repositories;

import com.samuelbraga.pierpont.models.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository
  extends JpaRepository<OperationType, Long> {}
