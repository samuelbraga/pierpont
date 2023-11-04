package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.adapters.accounts.SaveAccountAdapter;
import com.samuelbraga.pierpont.application.adapters.transactions.SaveTransactionAdapter;
import com.samuelbraga.pierpont.application.dtos.transactions.CreateTransactionDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.application.handles.accounts.SearchAccountHandle;
import com.samuelbraga.pierpont.application.handles.transactions.CreateTransactionalHandle;
import com.samuelbraga.pierpont.application.handles.transactions.TransactionCalculationHandle;
import com.samuelbraga.pierpont.application.handles.transactions.ValidateOperationTypeHandle;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateTransactionalHandleImpl
  implements CreateTransactionalHandle {
  private final SaveAccountAdapter saveAccountAdapter;
  private final SaveTransactionAdapter saveTransactionAdapter;

  private final ValidateOperationTypeHandle validateOperationTypeHandle;
  private final SearchAccountHandle searchAccountHandle;
  private final TransactionCalculationHandle transactionCalculationHandle;

  @Override
  public void execute(CreateTransactionDTO createTransactionDTO) {
    this.validateOperationTypeHandle.execute(
        createTransactionDTO.getOperationType()
      );

    var account =
      this.searchAccountHandle.execute(createTransactionDTO.getAccountId());
    account =
      transactionCalculationHandle.execute(
        createTransactionDTO.getOperationType(),
        account,
        createTransactionDTO.getAmount()
      );

    var amount = CalculateAmountService.execute(
      createTransactionDTO.getAmount(),
      createTransactionDTO.getOperationType()
    );

    var transaction = TransactionDTO
      .builder()
      .amount(amount)
      .account(account)
      .operationType(createTransactionDTO.getOperationType())
      .build();

    this.saveAccountAdapter.execute(account);
    this.saveTransactionAdapter.execute(transaction);
  }
}
