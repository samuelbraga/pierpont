package com.samuelbraga.pierpont.infrastructure.repositories;

import com.samuelbraga.pierpont.infrastructure.repositories.entities.Account;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.Transaction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository
  extends JpaRepository<Transaction, Long> {
  List<Transaction> findAllByAccount(Account account);
}
