package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import java.math.BigDecimal;

public abstract class CalculateAmountService {

  public static BigDecimal execute(
    BigDecimal amount,
    OperationTypeEnum operationType
  ) {
    if (!(operationType.equals(OperationTypeEnum.PAYMENT))) {
      return amount.negate();
    }
    return amount;
  }
}
