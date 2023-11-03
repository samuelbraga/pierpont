package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentTransactionHandleImplTest {
  private PaymentTransactionHandleImpl unit;
  private AccountDTO accountDTO;

  @BeforeEach
  void setUp() {
    unit = new PaymentTransactionHandleImpl();
    accountDTO =
      AccountDTO
        .builder()
        .accountId(1L)
        .documentNumber("123456")
        .availableCreditLimit(BigDecimal.TEN)
        .build();
  }

  @Test
  void testExecuteWhenPassPositiveValue() {
    var expected = AccountDTO
      .builder()
      .accountId(1L)
      .documentNumber("123456")
      .availableCreditLimit(BigDecimal.valueOf(20))
      .build();

    var result = unit.execute(accountDTO, BigDecimal.TEN);

    Assertions.assertEquals(
      expected.getAvailableCreditLimit(),
      result.getAvailableCreditLimit()
    );
  }

  @Test
  void testExecuteWhenPassZeroValue() {
    var expected = AccountDTO
      .builder()
      .accountId(1L)
      .documentNumber("123456")
      .availableCreditLimit(BigDecimal.TEN)
      .build();

    var result = unit.execute(accountDTO, BigDecimal.ZERO);

    Assertions.assertEquals(
      expected.getAvailableCreditLimit(),
      result.getAvailableCreditLimit()
    );
  }

  @Test
  void testExecuteWhenPassNegativeValue() {
    var expected = AccountDTO
      .builder()
      .accountId(1L)
      .documentNumber("123456")
      .availableCreditLimit(BigDecimal.valueOf(20))
      .build();

    var result = unit.execute(accountDTO, BigDecimal.TEN.negate());

    Assertions.assertEquals(
      expected.getAvailableCreditLimit(),
      result.getAvailableCreditLimit()
    );
  }
}
