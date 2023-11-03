package com.samuelbraga.pierpont.domain.useCases.accounts;

import com.samuelbraga.pierpont.application.adapters.accounts.GetAccountByIdAdapter;
import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
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
class SearchAccountHandleImplTest {
  @Mock
  private GetAccountByIdAdapter getAccountByIdAdapter;

  private SearchAccountHandleImpl unit;

  @BeforeEach
  void setUp() {
    unit = new SearchAccountHandleImpl(getAccountByIdAdapter);
  }

  @Test
  void testExecuteWhenRepositoryContainAccountId() {
    var accountId = 1L;
    var documentNumber = "123456";
    var availableCreditLimit = BigDecimal.TEN;

    var accountDTO = AccountDTO
      .builder()
      .accountId(accountId)
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

    Mockito
      .when(getAccountByIdAdapter.execute(accountId))
      .thenReturn(Optional.of(accountDTO));

    var result = unit.execute(accountId);

    Assertions.assertEquals(accountDTO.getAccountId(), result.getAccountId());
    Assertions.assertEquals(
      accountDTO.getDocumentNumber(),
      result.getDocumentNumber()
    );
    Assertions.assertEquals(
      accountDTO.getAvailableCreditLimit(),
      result.getAvailableCreditLimit()
    );
  }

  @Test
  void testExecuteWhenRepositoryNotContainAccountId() {
    var accountId = 1L;

    Mockito
      .when(getAccountByIdAdapter.execute(accountId))
      .thenReturn(Optional.empty());

    Assertions.assertThrows(ExceptionBase.class, () -> unit.execute(accountId));
  }
}
