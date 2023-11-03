package com.samuelbraga.pierpont.domain.useCases.accounts;

import static com.samuelbraga.pierpont.Constants.ERROR_ACCOUNT_ID_NOT_EXISTS;

import com.samuelbraga.pierpont.application.adapters.accounts.GetAccountByIdAdapter;
import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.application.handles.accounts.SearchAccountHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchAccountHandleImpl implements SearchAccountHandle {
  private final GetAccountByIdAdapter getAccountByIdAdapter;

  @Override
  public AccountDTO execute(Long accountId) {
    return this.getAccountByIdAdapter.execute(accountId)
      .orElseThrow(
        () ->
          new ExceptionBase(ERROR_ACCOUNT_ID_NOT_EXISTS, HttpStatus.BAD_REQUEST)
      );
  }
}
