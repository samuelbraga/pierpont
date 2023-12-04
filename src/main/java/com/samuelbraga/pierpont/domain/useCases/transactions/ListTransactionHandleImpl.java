package com.samuelbraga.pierpont.domain.useCases.transactions;

import static com.samuelbraga.pierpont.Constants.ERROR_ACCOUNT_ID_NOT_EXISTS;

import com.samuelbraga.pierpont.application.adapters.accounts.GetAccountByIdAdapter;
import com.samuelbraga.pierpont.application.adapters.transactions.ListTransactionByAccountAdapter;
import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.application.handles.transactions.ListTransactionHandle;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListTransactionHandleImpl implements ListTransactionHandle {
  private final ListTransactionByAccountAdapter listTransactionByAccountAdapter;
  private final GetAccountByIdAdapter getAccountByIdAdapter;

  @Override
  public List<TransactionDTO> execute(Long accountId) {
    return listTransactionByAccountAdapter.execute(this.getAccount(accountId));
  }

  private AccountDTO getAccount(Long accountId) {
    return this.getAccountByIdAdapter.execute(accountId)
      .orElseThrow(
        () ->
          new ExceptionBase(ERROR_ACCOUNT_ID_NOT_EXISTS, HttpStatus.BAD_REQUEST)
      );
  }
}
