package com.samuelbraga.pierpont.repositories;

import com.samuelbraga.pierpont.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository
  extends JpaRepository<Transaction, Long> {}
