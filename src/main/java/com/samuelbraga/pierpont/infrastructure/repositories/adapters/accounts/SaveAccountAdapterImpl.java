package com.samuelbraga.pierpont.infrastructure.repositories.adapters.accounts;

import com.samuelbraga.pierpont.application.adapters.accounts.SaveAccountAdapter;
import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.mapper.AccountMapper;
import com.samuelbraga.pierpont.infrastructure.repositories.AccountRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveAccountAdapterImpl implements SaveAccountAdapter {
  private final AccountRepository repository;
  private final AccountMapper mapper;

  @Override
  public AccountDTO execute(@NotNull AccountDTO accountDTO) {
    var account = this.mapper.fromAccountDTOToAccount(accountDTO);
    var savedAccount = this.repository.save(account);
    return this.mapper.fromAccountToAccountDTO(savedAccount);
  }
}
