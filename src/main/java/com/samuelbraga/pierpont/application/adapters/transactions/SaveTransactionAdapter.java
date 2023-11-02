package com.samuelbraga.pierpont.application.adapters.transactions;

import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;

public interface SaveTransactionAdapter {
  TransactionDTO execute(TransactionDTO transactionDTO);
}
