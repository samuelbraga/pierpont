package com.samuelbraga.pierpont.infrastructure.repositories.adapters.accounts;

import com.samuelbraga.pierpont.application.adapters.accounts.GetAccountByDocumentNumberAdapter;
import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.mapper.AccountMapper;
import com.samuelbraga.pierpont.infrastructure.repositories.AccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAccountByDocumentNumberAdapterImpl
  implements GetAccountByDocumentNumberAdapter {
  public final AccountRepository repository;
  public final AccountMapper mapper;

  @Override
  public Optional<AccountDTO> execute(String documentNumber) {
    var account = this.repository.findByDocumentNumber(documentNumber);
    return account.map(this.mapper::fromAccountToAccountDTO);
  }
}
