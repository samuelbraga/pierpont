package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import com.samuelbraga.pierpont.application.handles.transactions.TransactionHandle;
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
class TransactionCalculationHandleImplTest {
  @Mock
  private TransactionHandle paymentTransactionHandle;

  @Mock
  private TransactionHandle cashPurchaseTransactionHandle;

  @Mock
  private TransactionHandle installmentPurchaseTransactionHandle;

  @Mock
  private TransactionHandle withdrawalTransactionHandle;

  @InjectMocks
  private TransactionCalculationHandleImpl unit;

  private AccountDTO accountDTO;

  @BeforeEach
  void setUp() {
    accountDTO =
      AccountDTO
        .builder()
        .accountId(1L)
        .documentNumber("123456")
        .availableCreditLimit(BigDecimal.TEN)
        .build();
  }

  @Test
  void testExecuteWithPaymentOperation() {
    Mockito
      .when(paymentTransactionHandle.execute(accountDTO, BigDecimal.ONE))
      .thenReturn(accountDTO);
    var result = unit.execute(
      OperationTypeEnum.PAYMENT,
      accountDTO,
      BigDecimal.ONE
    );
    Assertions.assertEquals(accountDTO, result);
  }

  @Test
  void testExecuteWithCashPurchaseOperation() {
    Mockito
      .when(cashPurchaseTransactionHandle.execute(accountDTO, BigDecimal.ONE))
      .thenReturn(accountDTO);
    var result = unit.execute(
      OperationTypeEnum.CASH_PURCHASE,
      accountDTO,
      BigDecimal.ONE
    );
    Assertions.assertEquals(accountDTO, result);
  }

  @Test
  void testExecuteWithInstallmentPurchaseOperation() {
    Mockito
      .when(
        installmentPurchaseTransactionHandle.execute(accountDTO, BigDecimal.ONE)
      )
      .thenReturn(accountDTO);
    var result = unit.execute(
      OperationTypeEnum.INSTALLMENT_PURCHASE,
      accountDTO,
      BigDecimal.ONE
    );
    Assertions.assertEquals(accountDTO, result);
  }

  @Test
  void testExecuteWithWithdrawOperation() {
    Mockito
      .when(withdrawalTransactionHandle.execute(accountDTO, BigDecimal.ONE))
      .thenReturn(accountDTO);
    var result = unit.execute(
      OperationTypeEnum.WITHDRAWAL,
      accountDTO,
      BigDecimal.ONE
    );
    Assertions.assertEquals(accountDTO, result);
  }
}
