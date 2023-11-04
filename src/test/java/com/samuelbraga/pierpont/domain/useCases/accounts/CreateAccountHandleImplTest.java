package com.samuelbraga.pierpont.domain.useCases.accounts;

import static com.samuelbraga.pierpont.Constants.ERROR_ACCOUNT_NUMBER_EXISTS;

import com.samuelbraga.pierpont.application.adapters.accounts.GetAccountByDocumentNumberAdapter;
import com.samuelbraga.pierpont.application.adapters.accounts.SaveAccountAdapter;
import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.accounts.CreateAccountDTO;
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
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class CreateAccountHandleImplTest {
  @Mock
  private SaveAccountAdapter saveAccountAdapter;

  @Mock
  private GetAccountByDocumentNumberAdapter getAccountByDocumentNumberAdapter;

  private CreateAccountHandleImpl unit;

  @BeforeEach
  void setUp() {
    unit =
      new CreateAccountHandleImpl(
        saveAccountAdapter,
        getAccountByDocumentNumberAdapter
      );
  }

  @Test
  void testExecuteWhenRepositoryNotContainDocumentNumber() {
    var documentNumber = "123456";
    var availableCreditLimit = BigDecimal.TEN;

    var createAccountDTO = CreateAccountDTO
      .builder()
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

    var accountDTO = AccountDTO
      .builder()
      .accountId(1L)
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

    Mockito
      .when(getAccountByDocumentNumberAdapter.execute(documentNumber))
      .thenReturn(Optional.empty());
    Mockito
      .when(saveAccountAdapter.execute(Mockito.any(AccountDTO.class)))
      .thenReturn(accountDTO);

    var result = unit.execute(createAccountDTO);

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
  void testExecuteWhenRepositoryContainDocumentNumber() {
    var documentNumber = "123456";
    var availableCreditLimit = BigDecimal.TEN;

    var createAccountDTO = CreateAccountDTO
      .builder()
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

    var accountDTO = AccountDTO
      .builder()
      .accountId(1L)
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

    Mockito
      .when(getAccountByDocumentNumberAdapter.execute(documentNumber))
      .thenReturn(Optional.of(accountDTO));

    ExceptionBase exception = Assertions.assertThrows(
      ExceptionBase.class,
      () -> unit.execute(createAccountDTO)
    );

    Assertions.assertEquals(
      ERROR_ACCOUNT_NUMBER_EXISTS,
      exception.getMessage()
    );
    Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
  }
}
