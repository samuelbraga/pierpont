package com.samuelbraga.pierpont.domain.useCases.accounts;

import static com.samuelbraga.pierpont.Constants.ERROR_ACCOUNT_NUMBER_EXISTS;

import com.samuelbraga.pierpont.application.adapters.accounts.GetAccountByDocumentNumberAdapter;
import com.samuelbraga.pierpont.application.adapters.accounts.SaveAccountAdapter;
import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.accounts.CreateAccountDTO;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.application.handles.accounts.CreateAccountHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountHandleImpl implements CreateAccountHandle {
  private final SaveAccountAdapter saveAccountAdapter;
  private final GetAccountByDocumentNumberAdapter getAccountByDocumentNumberAdapter;

  public AccountDTO execute(CreateAccountDTO createAccountDTO) {
    this.validateAccountNumber(createAccountDTO.getDocumentNumber());

    var account = AccountDTO
      .builder()
      .documentNumber(createAccountDTO.getDocumentNumber())
      .availableCreditLimit(createAccountDTO.getAvailableCreditLimit())
      .build();

    return this.saveAccountAdapter.execute(account);
  }

  private void validateAccountNumber(String documentNumber) {
    this.getAccountByDocumentNumberAdapter.execute(documentNumber)
      .ifPresent(
        _account -> {
          throw new ExceptionBase(
            ERROR_ACCOUNT_NUMBER_EXISTS,
            HttpStatus.BAD_REQUEST
          );
        }
      );
  }
}
