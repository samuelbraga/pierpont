package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.handles.transactions.TransactionHandle;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service("PaymentTransactionHandle")
public class PaymentTransactionHandleImpl implements TransactionHandle {

  @Override
  public AccountDTO execute(AccountDTO accountDTO, BigDecimal amount) {
    var creditAvailable = accountDTO.getAvailableCreditLimit();
    accountDTO.setAvailableCreditLimit(creditAvailable.add(amount));
    return accountDTO;
  }
}
