package com.samuelbraga.pierpont.application.handles.transactions;

import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import java.util.List;

public interface ListTransactionHandle {
  List<TransactionDTO> execute(Long accountId);
}
