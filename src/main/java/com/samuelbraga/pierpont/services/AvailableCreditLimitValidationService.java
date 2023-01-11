package com.samuelbraga.pierpont.services;

import com.samuelbraga.pierpont.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.models.Account;
import com.samuelbraga.pierpont.models.OperationTypeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.samuelbraga.pierpont.Constants.ERROR_AVAILABLE_CREDIT_LIMIT_EXCEEDED;

@Service
public class AvailableCreditLimitValidationService {
    public Account execute(Account account, OperationTypeEnum operationType, BigDecimal amount) {
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
