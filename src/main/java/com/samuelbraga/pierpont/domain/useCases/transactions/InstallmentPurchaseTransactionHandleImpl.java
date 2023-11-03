package com.samuelbraga.pierpont.domain.useCases.transactions;

import static com.samuelbraga.pierpont.Constants.ERROR_AVAILABLE_CREDIT_LIMIT_EXCEEDED;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.application.handles.transactions.TransactionHandle;
import java.math.BigDecimal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("InstallmentPurchaseTransactionHandle")
public class InstallmentPurchaseTransactionHandleImpl implements TransactionHandle {

  @Override
  public AccountDTO execute(AccountDTO accountDTO, BigDecimal amount) {
    var availableCreditLimit = accountDTO.getAvailableCreditLimit();
    this.creditLimitValidation(amount, availableCreditLimit);
    accountDTO.setAvailableCreditLimit(availableCreditLimit.subtract(amount));
    return accountDTO;
  }

  private void creditLimitValidation(BigDecimal amount, BigDecimal availableCreditLimit) {
    if (amount.compareTo(availableCreditLimit) > 0) {
      throw new ExceptionBase(
        ERROR_AVAILABLE_CREDIT_LIMIT_EXCEEDED,
        HttpStatus.BAD_REQUEST
      );
    }
  }
}
