package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CashPurchaseTransactionHandleImplTest {
  private CashPurchaseTransactionHandleImpl unit;

  @BeforeEach
  void setUp() {
    unit = new CashPurchaseTransactionHandleImpl();
  }

  @Test
  void testExecuteWhenPassAmount() {
    var accountDTO = AccountDTO
      .builder()
      .accountId(1L)
      .documentNumber("123456")
      .availableCreditLimit(BigDecimal.TEN)
      .build();

    var result = unit.execute(accountDTO, BigDecimal.ONE);

    Assertions.assertEquals(accountDTO, result);
  }
}
