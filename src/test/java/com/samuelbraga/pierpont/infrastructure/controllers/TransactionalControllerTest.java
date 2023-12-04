package com.samuelbraga.pierpont.infrastructure.controllers;

import com.samuelbraga.pierpont.application.dtos.transactions.CreateTransactionDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.application.handles.transactions.CreateTransactionalHandle;
import com.samuelbraga.pierpont.application.handles.transactions.ListTransactionHandle;
import com.samuelbraga.pierpont.application.mapper.TransactionMapperImpl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.CreateTransactionRequest;
import org.openapitools.model.OperationType;
import org.openapitools.model.TransactionResponse;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class TransactionalControllerTest {
  @Mock
  private CreateTransactionalHandle createTransactionalHandle;

  @Mock
  private ListTransactionHandle listTransactionHandle;

  @Mock
  private TransactionMapperImpl transactionMapper;

  private TransactionalController unit;

  @BeforeEach
  void setUp() {
    unit =
      new TransactionalController(
        createTransactionalHandle,
        listTransactionHandle,
        transactionMapper
      );
  }

  @Test
  void testWhenICreateTransactionShouldBeSuccess() {
    var accountId = 13L;
    var operationType = OperationType.INSTALLMENT_PURCHASE;
    var amount = BigDecimal.valueOf(50);

    var request = new CreateTransactionRequest();
    request.setAccountId(accountId);
    request.setOperationType(operationType);
    request.setAmount(amount);

    Mockito
      .when(
        transactionMapper.fromCreateTransactionRequestToCreateTransactionDTO(
          request
        )
      )
      .thenCallRealMethod();

    var response = this.unit.createTransaction(request);

    Mockito
      .verify(createTransactionalHandle, Mockito.times(1))
      .execute(Mockito.any(CreateTransactionDTO.class));

    Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  void testWhenIListTransactionsShouldBeSuccess() {
    var accountId = 13L;
    var transactions = List.of(
      new TransactionDTO(),
      new TransactionDTO(),
      new TransactionDTO()
    );

    Mockito
      .when(listTransactionHandle.execute(Mockito.eq(accountId)))
      .thenReturn(transactions);
    Mockito
      .when(
        transactionMapper.fromTransactionDTIOToTransactionResponse(
          Mockito.any(TransactionDTO.class)
        )
      )
      .thenReturn(new TransactionResponse());

    var response = unit.listTransactions(accountId);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(
      3,
      Objects.requireNonNull(response.getBody()).size()
    );
  }
}
