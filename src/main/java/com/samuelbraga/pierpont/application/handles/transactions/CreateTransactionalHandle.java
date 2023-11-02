package com.samuelbraga.pierpont.application.handles.transactions;

import com.samuelbraga.pierpont.application.dtos.transactions.CreateTransactionDTO;

public interface CreateTransactionalHandle {
  void execute(CreateTransactionDTO createTransactionDTO);
}
