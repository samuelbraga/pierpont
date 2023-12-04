package com.samuelbraga.pierpont.domain.useCases.transactions;

import static com.samuelbraga.pierpont.Constants.ERROR_ACCOUNT_ID_NOT_EXISTS;

import com.samuelbraga.pierpont.application.adapters.accounts.GetAccountByIdAdapter;
import com.samuelbraga.pierpont.application.adapters.transactions.ListTransactionByAccountAdapter;
import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class ListTransactionHandleImplTest {
  @Mock
  private ListTransactionByAccountAdapter listTransactionByAccountAdapter;

  @Mock
  private GetAccountByIdAdapter getAccountByIdAdapter;

  private ListTransactionHandleImpl unit;

  @BeforeEach
  void setUp() {
    unit =
      new ListTransactionHandleImpl(
        listTransactionByAccountAdapter,
        getAccountByIdAdapter
      );
  }

  @Test
  void testExecuteWhenAccountExistsAndAdapterReturnListOfTransactions() {
    var accountId = 123L;
    var accountDTO = AccountDTO.builder().accountId(accountId).build();
    var expected = List.of(
      new TransactionDTO(),
      new TransactionDTO(),
      new TransactionDTO()
    );

    Mockito
      .when(getAccountByIdAdapter.execute(accountId))
      .thenReturn(Optional.of(accountDTO));
    Mockito
      .when(listTransactionByAccountAdapter.execute(accountDTO))
      .thenReturn(expected);

    var result = unit.execute(accountId);
    Assertions.assertEquals(expected.size(), result.size());
    Assertions.assertEquals(expected, result);
  }

  @Test
  void testExecuteWhenAccountExistsAndAdapterReturnEmptyList() {
    var accountId = 123L;
    var accountDTO = AccountDTO.builder().accountId(accountId).build();
    var expected = new ArrayList<TransactionDTO>();

    Mockito
      .when(getAccountByIdAdapter.execute(accountId))
      .thenReturn(Optional.of(accountDTO));
    Mockito
      .when(listTransactionByAccountAdapter.execute(accountDTO))
      .thenReturn(expected);

    var result = unit.execute(accountId);
    Assertions.assertEquals(expected.size(), result.size());
    Assertions.assertEquals(expected, result);
  }

  @Test
  void testExecuteWhenAccountNotExists() {
    var accountId = 123L;
    Mockito
      .when(getAccountByIdAdapter.execute(accountId))
      .thenReturn(Optional.empty());

    ExceptionBase exception = Assertions.assertThrows(
      ExceptionBase.class,
      () -> unit.execute(accountId)
    );

    Assertions.assertEquals(
      ERROR_ACCOUNT_ID_NOT_EXISTS,
      exception.getMessage()
    );
    Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
  }
}
