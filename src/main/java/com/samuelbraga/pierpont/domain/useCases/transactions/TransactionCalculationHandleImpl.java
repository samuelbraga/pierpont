package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import com.samuelbraga.pierpont.application.handles.transactions.TransactionCalculationHandle;
import com.samuelbraga.pierpont.application.handles.transactions.TransactionHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionCalculationHandleImpl
  implements TransactionCalculationHandle {
  @Autowired
  @Qualifier("PaymentTransactionHandle")
  private TransactionHandle paymentTransactionHandle;

  @Autowired
  @Qualifier("CashPurchaseTransactionHandle")
  private TransactionHandle cashPurchaseTransactionHandle;

  @Autowired
  @Qualifier("InstallmentPurchaseTransactionHandle")
  private TransactionHandle installmentPurchaseTransactionHandle;

  @Autowired
  @Qualifier("WithdrawalTransactionHandle")
  private TransactionHandle withdrawalTransactionHandle;

  public AccountDTO execute(
    OperationTypeEnum operationTypeEnum,
    AccountDTO accountDTO,
    BigDecimal amount
  ) {
    return switch (operationTypeEnum) {
      case PAYMENT -> this.paymentTransactionHandle.execute(accountDTO, amount);
      case CASH_PURCHASE -> this.cashPurchaseTransactionHandle.execute(accountDTO, amount);
      case INSTALLMENT_PURCHASE -> this.installmentPurchaseTransactionHandle.execute(
        accountDTO,
        amount
      );
      case WITHDRAWAL -> this.withdrawalTransactionHandle.execute(accountDTO, amount);
    };
  }
}
