package com.samuelbraga.pierpont.application.handles.transactions;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import java.math.BigDecimal;

public interface TransactionCalculationHandle {
  AccountDTO execute(
    OperationTypeEnum operationTypeEnum,
    AccountDTO accountDTO,
    BigDecimal amount
  );
}
