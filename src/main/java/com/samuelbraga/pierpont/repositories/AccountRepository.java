package com.samuelbraga.pierpont.repositories;

import com.samuelbraga.pierpont.models.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByDocumentNumber(String documentNumber);
}
