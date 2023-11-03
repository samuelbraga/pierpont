package com.samuelbraga.pierpont.infrastructure.repositories;

import com.samuelbraga.pierpont.infrastructure.repositories.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository
  extends JpaRepository<Transaction, Long> {}
