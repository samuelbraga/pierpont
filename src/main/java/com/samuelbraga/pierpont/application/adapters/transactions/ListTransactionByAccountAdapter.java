package com.samuelbraga.pierpont.application.adapters.transactions;

import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;

import java.util.List;

public interface ListTransactionByAccountAdapter {
    List<TransactionDTO> execute(Long accountId);
}
