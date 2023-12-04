package com.samuelbraga.pierpont.infrastructure.repository.adapters.transactions;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.application.mapper.AccountMapperImpl;
import com.samuelbraga.pierpont.application.mapper.TransactionMapperImpl;
import com.samuelbraga.pierpont.infrastructure.repositories.TransactionRepository;
import com.samuelbraga.pierpont.infrastructure.repositories.adapters.transactions.ListTransactionByAccountAdapterImpl;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.Account;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.Transaction;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListTransactionByAccountAdapterImplTest {
  @Mock
  private TransactionRepository repository;

  @Mock
  private TransactionMapperImpl transactionMapper;

  @Mock
  private AccountMapperImpl accountMapper;

  private ListTransactionByAccountAdapterImpl unit;

  @BeforeEach
  void setUp() {
    this.unit =
      new ListTransactionByAccountAdapterImpl(
        repository,
        transactionMapper,
        accountMapper
      );
  }

  @Test
  void testExecuteWhenAccountExistsAndReturnTransactions() {
    var accountDTO = AccountDTO.builder().accountId(123L).build();
    var account = Account.builder().build();
    var transactions = List.of(
      new Transaction(),
      new Transaction(),
      new Transaction()
    );

    Mockito
      .when(accountMapper.fromAccountDTOToAccount(Mockito.eq(accountDTO)))
      .thenReturn(account);
    Mockito
      .when(repository.findAllByAccount(Mockito.eq(account)))
      .thenReturn(transactions);
    Mockito
      .when(
        transactionMapper.fromTransactionToTransactionDTO(
          Mockito.any(Transaction.class)
        )
      )
      .thenReturn(new TransactionDTO());

    var result = unit.execute(accountDTO);
    Assertions.assertEquals(3, result.size());
  }

  @Test
  void testExecuteWhenAccountExistsAndReturnEmptyTransactions() {
    var accountDTO = AccountDTO.builder().accountId(123L).build();
    var account = Account.builder().build();
    var transactions = new ArrayList<Transaction>();

    Mockito
      .when(accountMapper.fromAccountDTOToAccount(Mockito.eq(accountDTO)))
      .thenReturn(account);
    Mockito
      .when(repository.findAllByAccount(Mockito.eq(account)))
      .thenReturn(transactions);

    var result = unit.execute(accountDTO);
    Assertions.assertEquals(0, result.size());
  }
}
