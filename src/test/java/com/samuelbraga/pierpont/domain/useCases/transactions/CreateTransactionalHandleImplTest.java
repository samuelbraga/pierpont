package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.adapters.accounts.SaveAccountAdapter;
import com.samuelbraga.pierpont.application.adapters.transactions.SaveTransactionAdapter;
import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.CreateTransactionDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.application.handles.accounts.SearchAccountHandle;
import com.samuelbraga.pierpont.application.handles.transactions.TransactionCalculationHandle;
import com.samuelbraga.pierpont.application.handles.transactions.ValidateOperationTypeHandle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class CreateTransactionalHandleImplTest {
  @Mock
  private SaveAccountAdapter saveAccountAdapter;

  @Mock
  private SaveTransactionAdapter saveTransactionAdapter;

  @Mock
  private ValidateOperationTypeHandle validateOperationTypeHandle;

  @Mock
  private SearchAccountHandle searchAccountHandle;

  @Mock
  private TransactionCalculationHandle transactionCalculationHandle;

  private CreateTransactionalHandleImpl unit;

  @BeforeEach
  void setUp() {
    unit =
      new CreateTransactionalHandleImpl(
        saveAccountAdapter,
        saveTransactionAdapter,
        validateOperationTypeHandle,
        searchAccountHandle,
        transactionCalculationHandle
      );
  }

  @Test
  void testExecuteWhenCreateATransaction() {
    var accountId = 1L;
    var documentNumber = "13456";
    var availableCreditLimit = BigDecimal.ONE;
    var amount = BigDecimal.TEN;
    var operationType = OperationTypeEnum.PAYMENT;

    var createTransactionDTO = CreateTransactionDTO
      .builder()
      .accountId(accountId)
      .amount(amount)
      .operationType(operationType)
      .build();

    var accountDTO = AccountDTO
      .builder()
      .accountId(accountId)
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

    Mockito
      .doNothing()
      .when(validateOperationTypeHandle)
      .execute(operationType);
    Mockito.when(searchAccountHandle.execute(accountId)).thenReturn(accountDTO);
    Mockito
      .when(
        transactionCalculationHandle.execute(operationType, accountDTO, amount)
      )
      .thenReturn(accountDTO);

    unit.execute(createTransactionDTO);

    Mockito.verify(saveAccountAdapter, Mockito.times(1)).execute(accountDTO);
    Mockito
      .verify(saveTransactionAdapter, Mockito.times(1))
      .execute(Mockito.any(TransactionDTO.class));
  }

  @Test
  void testExecuteWhenInvalidOperationType() {
    var accountId = 1L;
    var amount = BigDecimal.TEN;
    var operationType = OperationTypeEnum.PAYMENT;

    var createTransactionDTO = CreateTransactionDTO
      .builder()
      .accountId(accountId)
      .amount(amount)
      .operationType(operationType)
      .build();

    Mockito
      .doThrow(new ExceptionBase("error", HttpStatus.BAD_REQUEST))
      .when(validateOperationTypeHandle)
      .execute(operationType);

    ExceptionBase exception = Assertions.assertThrows(
            ExceptionBase.class,
            () -> unit.execute(createTransactionDTO)
    );

    Assertions.assertEquals("error", exception.getMessage());
    Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());

    Mockito
      .verify(saveAccountAdapter, Mockito.times(0))
      .execute(Mockito.any(AccountDTO.class));
    Mockito
      .verify(saveTransactionAdapter, Mockito.times(0))
      .execute(Mockito.any(TransactionDTO.class));
  }

  @Test
  void testExecuteWhenNotExistAccount() {
    var accountId = 1L;
    var amount = BigDecimal.TEN;
    var operationType = OperationTypeEnum.PAYMENT;

    var createTransactionDTO = CreateTransactionDTO
      .builder()
      .accountId(accountId)
      .amount(amount)
      .operationType(operationType)
      .build();

    Mockito
      .doNothing()
      .when(validateOperationTypeHandle)
      .execute(operationType);
    Mockito
      .when(searchAccountHandle.execute(accountId))
      .thenThrow(new ExceptionBase("error", HttpStatus.BAD_REQUEST));

    ExceptionBase exception = Assertions.assertThrows(
            ExceptionBase.class,
            () -> unit.execute(createTransactionDTO)
    );

    Assertions.assertEquals("error", exception.getMessage());
    Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());

    Mockito
      .verify(saveAccountAdapter, Mockito.times(0))
      .execute(Mockito.any(AccountDTO.class));
    Mockito
      .verify(saveTransactionAdapter, Mockito.times(0))
      .execute(Mockito.any(TransactionDTO.class));
  }
}
