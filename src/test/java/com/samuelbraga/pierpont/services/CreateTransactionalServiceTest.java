package com.samuelbraga.pierpont.services;

import com.samuelbraga.pierpont.models.Account;
import com.samuelbraga.pierpont.models.OperationType;
import com.samuelbraga.pierpont.models.OperationTypeEnum;
import com.samuelbraga.pierpont.models.Transaction;
import com.samuelbraga.pierpont.repositories.TransactionRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.CreateTransactionRequest;

@ExtendWith(MockitoExtension.class)
class CreateTransactionalServiceTest {
  @Mock
  private TransactionRepository repository;

  @Mock
  private SearchAccountService searchAccountService;

  @Mock
  private SearchOperationTypeService searchOperationTypeService;

  private CreateTransactionalService unit;

  @BeforeEach
  void setUp() {
    unit =
      new CreateTransactionalService(
        repository,
        searchAccountService,
        searchOperationTypeService
      );
  }

  @Test
  void testWhenIExecuteShouldBeSuccess() {
    var accountId = 13L;
    var documentNumber = "123456";
    var amount = BigDecimal.valueOf(-50);
    var operationTypeEnum = OperationTypeEnum.valueOf(
      org.openapitools.model.OperationType.CASH_PURCHASE.name()
    );

    var account = Account
      .builder()
      .id(accountId)
      .documentNumber(documentNumber)
      .build();

    var operationType = OperationType
      .builder()
      .id(1L)
      .description("CASH_PURCHASE")
      .build();

    var request = new CreateTransactionRequest();
    request.setAccountId(accountId);
    request.setOperationType(
      org.openapitools.model.OperationType.CASH_PURCHASE
    );
    request.setAmount(amount);

    Mockito
      .when(this.searchAccountService.execute(Mockito.eq(accountId)))
      .thenReturn(account);
    Mockito
      .when(
        this.searchOperationTypeService.execute(Mockito.eq(operationTypeEnum))
      )
      .thenReturn(operationType);

    this.unit.execute(request);

    Mockito
      .verify(this.repository, Mockito.times(1))
      .save(Mockito.any(Transaction.class));
  }
}
