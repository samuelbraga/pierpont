package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static com.samuelbraga.pierpont.Constants.ERROR_AVAILABLE_CREDIT_LIMIT_EXCEEDED;

class InstallmentPurchaseTransactionHandleImplTest {
  private InstallmentPurchaseTransactionHandleImpl unit;
  private AccountDTO accountDTO;

  @BeforeEach
  void setUp() {
    unit = new InstallmentPurchaseTransactionHandleImpl();
    accountDTO =
      AccountDTO
        .builder()
        .accountId(1L)
        .documentNumber("123456")
        .availableCreditLimit(BigDecimal.TEN)
        .build();
  }

  @Test
  void testExecuteWhenAccountHasMoreAvailableCreditLimitThenAmountNeeded() {
    var expected = AccountDTO
      .builder()
      .accountId(1L)
      .documentNumber("123456")
      .availableCreditLimit(BigDecimal.valueOf(9))
      .build();

    var result = unit.execute(accountDTO, BigDecimal.ONE);

    Assertions.assertEquals(
      expected.getAvailableCreditLimit(),
      result.getAvailableCreditLimit()
    );
  }

  @Test
  void testExecuteWhenAccountHasEqualAvailableCreditLimitThenAmountNeeded() {
    var expected = AccountDTO
      .builder()
      .accountId(1L)
      .documentNumber("123456")
      .availableCreditLimit(BigDecimal.ZERO)
      .build();

    var result = unit.execute(accountDTO, BigDecimal.TEN);

    Assertions.assertEquals(
      expected.getAvailableCreditLimit(),
      result.getAvailableCreditLimit()
    );
  }

  @Test
  void testExecuteWhenAccountHasLessAvailableCreditLimitThenAmountNeeded() {
    ExceptionBase exception = Assertions.assertThrows(
            ExceptionBase.class,
            () -> unit.execute(accountDTO, BigDecimal.valueOf(11))
    );

    Assertions.assertEquals(ERROR_AVAILABLE_CREDIT_LIMIT_EXCEEDED, exception.getMessage());
    Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
  }
}
