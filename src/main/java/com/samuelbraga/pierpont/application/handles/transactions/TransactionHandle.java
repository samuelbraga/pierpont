package com.samuelbraga.pierpont.application.handles.transactions;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import java.math.BigDecimal;

public interface TransactionHandle {
  AccountDTO execute(AccountDTO accountDTO, BigDecimal amount);
}
