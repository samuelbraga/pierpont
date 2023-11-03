package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.handles.transactions.TransactionHandle;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service("CashPurchaseTransactionHandle")
public class CashPurchaseTransactionHandleImpl implements TransactionHandle {

  @Override
  public AccountDTO execute(AccountDTO accountDTO, BigDecimal _amount) {
    return accountDTO;
  }
}
