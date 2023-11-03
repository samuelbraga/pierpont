package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class CalculateAmountService {

  public BigDecimal execute(BigDecimal amount, OperationTypeEnum operationType) {
    if (!(operationType.equals(OperationTypeEnum.PAYMENT))) {
      return amount.negate();
    }
    return amount;
  }
}
