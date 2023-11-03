package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalculateAmountServiceTest {

  @Test
  void testExecuteWhenPassPaymentOperationType() {
    var amount = BigDecimal.TEN;
    var result = CalculateAmountService.execute(
      amount,
      OperationTypeEnum.PAYMENT
    );
    Assertions.assertEquals(amount, result);
  }

  @ParameterizedTest
  @ValueSource(
    strings = { "CASH_PURCHASE", "INSTALLMENT_PURCHASE", "WITHDRAWAL" }
  )
  void testExecuteWhenNotPassPaymentOperationType(
    String operationTypeDescription
  ) {
    var operationTypeEnum = OperationTypeEnum.valueOf(operationTypeDescription);
    var amount = BigDecimal.TEN;
    var result = CalculateAmountService.execute(amount, operationTypeEnum);
    Assertions.assertEquals(amount.negate(), result);
  }
}
