package com.samuelbraga.pierpont.infrastructure.repositories.adapters.accounts;

import static com.samuelbraga.pierpont.Constants.ERROR_INVALID_ACCOUNT_VALUE_TO_SAVE;

import com.samuelbraga.pierpont.application.adapters.accounts.SaveAccountAdapter;
import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.application.mapper.AccountMapper;
import com.samuelbraga.pierpont.infrastructure.repositories.AccountRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveAccountAdapterImpl implements SaveAccountAdapter {
  private final AccountRepository repository;
  private final AccountMapper mapper;

  @Override
  public AccountDTO execute(@NotNull AccountDTO accountDTO) {
    if (accountDTO == null) {
      throw new ExceptionBase(
        ERROR_INVALID_ACCOUNT_VALUE_TO_SAVE,
        HttpStatus.BAD_REQUEST
      );
    }
    var account = this.mapper.fromAccountDTOToAccount(accountDTO);
    var savedAccount = this.repository.save(account);
    return this.mapper.fromAccountToAccountDTO(savedAccount);
  }
}
