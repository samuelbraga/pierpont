package com.samuelbraga.pierpont.infrastructure.repositories.adapters.accounts;

import com.samuelbraga.pierpont.application.adapters.accounts.GetAccountByIdAdapter;
import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.mapper.AccountMapper;
import com.samuelbraga.pierpont.infrastructure.repositories.AccountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAccountByIdAdapterImpl implements GetAccountByIdAdapter {
  private final AccountRepository repository;
  private final AccountMapper mapper;

  public Optional<AccountDTO> execute(Long accountId) {
    var account = this.repository.findById(accountId);
    return account.map(this.mapper::fromAccountToAccountDTO);
  }
}
