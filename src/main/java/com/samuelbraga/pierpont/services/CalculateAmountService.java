package com.samuelbraga.pierpont.services;

import com.samuelbraga.pierpont.models.OperationTypeEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculateAmountService {

    public BigDecimal execute(BigDecimal amount, OperationTypeEnum operationType) {
        if (!(operationType.equals(OperationTypeEnum.PAYMENT))) {
            return amount.negate();
        }
        return amount;
    }
}
