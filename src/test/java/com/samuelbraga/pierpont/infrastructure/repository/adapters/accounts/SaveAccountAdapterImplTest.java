package com.samuelbraga.pierpont.infrastructure.repository.adapters.accounts;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.application.mapper.AccountMapperImpl;
import com.samuelbraga.pierpont.infrastructure.repositories.AccountRepository;
import com.samuelbraga.pierpont.infrastructure.repositories.adapters.accounts.SaveAccountAdapterImpl;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class SaveAccountAdapterImplTest {
  @Mock
  private AccountRepository repository;

  @Mock
  private AccountMapperImpl mapper;

  private SaveAccountAdapterImpl unit;

  @BeforeEach
  void setUp() {
    unit = new SaveAccountAdapterImpl(repository, mapper);
  }

  @Test
  void testExecuteWhenRepositoryNotContainAccount() {
    var accountId = 1L;
    var documentNumber = "123456";
    var availableCreditLimit = BigDecimal.TEN;

    var accountDTO = AccountDTO
      .builder()
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

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
      .when(mapper.fromAccountDTOToAccount(accountDTO))
      .thenCallRealMethod();
    Mockito
      .when(repository.save(Mockito.any(Account.class)))
      .thenReturn(account);
    Mockito.when(mapper.fromAccountToAccountDTO(account)).thenCallRealMethod();

    var result = unit.execute(accountDTO);

    Assertions.assertEquals(expected.getAccountId(), result.getAccountId());
    Assertions.assertEquals(
      expected.getDocumentNumber(),
      result.getDocumentNumber()
    );
    Assertions.assertEquals(
      expected.getAvailableCreditLimit(),
      result.getAvailableCreditLimit()
    );
  }

  @Test
  void testExecuteWhenRepositoryContainAccount() {
    var accountId = 1L;
    var documentNumber = "123456";
    var availableCreditLimit = BigDecimal.TEN;

    var accountDTO = AccountDTO
      .builder()
      .accountId(1L)
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

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
      .when(mapper.fromAccountDTOToAccount(accountDTO))
      .thenCallRealMethod();
    Mockito
      .when(repository.save(Mockito.any(Account.class)))
      .thenReturn(account);
    Mockito.when(mapper.fromAccountToAccountDTO(account)).thenCallRealMethod();

    var result = unit.execute(accountDTO);

    Assertions.assertEquals(expected.getAccountId(), result.getAccountId());
    Assertions.assertEquals(
      expected.getDocumentNumber(),
      result.getDocumentNumber()
    );
    Assertions.assertEquals(
      expected.getAvailableCreditLimit(),
      result.getAvailableCreditLimit()
    );
  }

  @Test
  void testExecuteWhenPassNullableToMethod() {
    Assertions.assertThrows(ExceptionBase.class, () -> unit.execute(null));
  }
}
