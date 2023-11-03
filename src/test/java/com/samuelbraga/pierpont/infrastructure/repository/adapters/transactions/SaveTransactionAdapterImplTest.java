package com.samuelbraga.pierpont.infrastructure.repository.adapters.transactions;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.application.mapper.AccountMapperImpl;
import com.samuelbraga.pierpont.application.mapper.OperationTypeMapperImpl;
import com.samuelbraga.pierpont.application.mapper.TransactionMapperImpl;
import com.samuelbraga.pierpont.infrastructure.repositories.TransactionRepository;
import com.samuelbraga.pierpont.infrastructure.repositories.adapters.transactions.SaveTransactionAdapterImpl;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.Account;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.OperationType;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.Transaction;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveTransactionAdapterImplTest {
  @Mock
  private TransactionRepository repository;

  @InjectMocks
  private TransactionMapperImpl transactionMapper;

  @Mock
  private OperationTypeMapperImpl operationTypeMapper;

  @Mock
  private AccountMapperImpl accountMapper;

  private SaveTransactionAdapterImpl unit;

  @BeforeEach
  void setUp() {
    unit =
      new SaveTransactionAdapterImpl(
        repository,
        transactionMapper,
        operationTypeMapper,
        accountMapper
      );
  }

  @Test
  void testExecuteWhenRepositoryNotContainTransaction() {
    var transactionId = 1L;
    var amount = BigDecimal.ONE;
    var accountId = 1L;
    var documentNumber = "123456";
    var availableCreditLimit = BigDecimal.TEN;

    var accountDTO = AccountDTO
      .builder()
      .accountId(accountId)
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

    var transactionDTO = TransactionDTO
      .builder()
      .operationType(OperationTypeEnum.PAYMENT)
      .account(accountDTO)
      .amount(amount)
      .build();

    var account = Account
      .builder()
      .id(accountId)
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

    var operationType = OperationType
      .builder()
      .id(4L)
      .description("PAYMENT")
      .build();

    var transaction = Transaction
      .builder()
      .id(transactionId)
      .amount(amount)
      .account(account)
      .operationType(operationType)
      .build();

    Mockito
      .when(
        operationTypeMapper.fromOperationTypeEnumToOperationType(
          OperationTypeEnum.PAYMENT
        )
      )
      .thenCallRealMethod();
    Mockito
      .when(accountMapper.fromAccountDTOToAccount(accountDTO))
      .thenCallRealMethod();
    Mockito
      .when(repository.save(Mockito.any(Transaction.class)))
      .thenReturn(transaction);
    Mockito
      .when(transactionMapper.fromTransactionToTransactionDTO(transaction))
      .thenCallRealMethod();
    Mockito
      .when(accountMapper.fromAccountToAccountDTO(account))
      .thenCallRealMethod();

    var result = unit.execute(transactionDTO);

    Assertions.assertEquals(transactionDTO.getAmount(), result.getAmount());
    Assertions.assertEquals(
      transactionDTO.getOperationType(),
      result.getOperationType()
    );
    Assertions.assertEquals(
      transactionDTO.getAccount().getAccountId(),
      result.getAccount().getAccountId()
    );
  }
}
