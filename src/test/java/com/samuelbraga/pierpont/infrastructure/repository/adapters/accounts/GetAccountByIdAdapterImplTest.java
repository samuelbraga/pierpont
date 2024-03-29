package com.samuelbraga.pierpont.infrastructure.repository.adapters.accounts;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.mapper.AccountMapperImpl;
import com.samuelbraga.pierpont.infrastructure.repositories.AccountRepository;
import com.samuelbraga.pierpont.infrastructure.repositories.adapters.accounts.GetAccountByIdAdapterImpl;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.Account;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAccountByIdAdapterImplTest {
  @Mock
  private AccountRepository repository;

  @Mock
  private AccountMapperImpl mapper;

  private GetAccountByIdAdapterImpl unit;

  @BeforeEach
  void setUp() {
    unit = new GetAccountByIdAdapterImpl(repository, mapper);
  }

  @Test
  void testExecuteWhenRepositoryContainAccountWithId() {
    var accountId = 1L;
    var documentNumber = "123456";
    var availableCreditLimit = BigDecimal.TEN;

    var account = Account
      .builder()
      .id(accountId)
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

    var expected = AccountDTO
      .builder()
      .accountId(accountId)
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

    Mockito
      .when(repository.findById(accountId))
      .thenReturn(Optional.of(account));
    Mockito
      .when(mapper.fromAccountToAccountDTO(Mockito.any(Account.class)))
      .thenCallRealMethod();

    var result = unit.execute(accountId);

    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(
      expected.getAccountId(),
      result.get().getAccountId()
    );
    Assertions.assertEquals(
      expected.getDocumentNumber(),
      result.get().getDocumentNumber()
    );
    Assertions.assertEquals(
      expected.getAvailableCreditLimit(),
      result.get().getAvailableCreditLimit()
    );
  }

  @Test
  void testExecuteWhenRepositoryNotContainAccountWithId() {
    var accountId = 1L;

    Mockito.when(repository.findById(accountId)).thenReturn(Optional.empty());

    var result = unit.execute(accountId);

    Assertions.assertTrue(result.isEmpty());
  }
}
