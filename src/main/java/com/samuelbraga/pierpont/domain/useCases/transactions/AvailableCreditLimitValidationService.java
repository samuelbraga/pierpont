package com.samuelbraga.pierpont.domain.useCases.transactions;

import static com.samuelbraga.pierpont.Constants.ERROR_AVAILABLE_CREDIT_LIMIT_EXCEEDED;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
import java.math.BigDecimal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AvailableCreditLimitValidationService {

  public AccountDTO execute(
    AccountDTO account,
    OperationTypeEnum operationType,
    BigDecimal amount
  ) {
    if (!(operationType.equals(OperationTypeEnum.PAYMENT))) {
      var availableCreditLimit = account.getAvailableCreditLimit();
      if (amount.compareTo(availableCreditLimit) > 0) {
        throw new ExceptionBase(
          ERROR_AVAILABLE_CREDIT_LIMIT_EXCEEDED,
          HttpStatus.BAD_REQUEST
        );
      }

      account.setAvailableCreditLimit(availableCreditLimit.subtract(amount));
    } else {
      var creditAvailable = account.getAvailableCreditLimit();
      account.setAvailableCreditLimit(creditAvailable.add(amount));
    }

    return account;
  }
}
