package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.adapters.accounts.SaveAccountAdapter;
import com.samuelbraga.pierpont.application.adapters.transactions.SaveTransactionAdapter;
import com.samuelbraga.pierpont.application.dtos.transactions.CreateTransactionDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.application.handles.accounts.SearchAccountHandle;
import com.samuelbraga.pierpont.application.handles.transactions.CreateTransactionalHandle;
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

  private final CalculateAmountService calculateAmountService;
  private final AvailableCreditLimitValidationService availableCreditLimitValidationService;

  @Override
  public void execute(CreateTransactionDTO createTransactionDTO) {
    var operationTypeEnum = OperationTypeEnum.valueOf(
      createTransactionDTO.getOperationType().name()
    );

    this.validateOperationTypeHandle.execute(operationTypeEnum);
    var account =
      this.searchAccountHandle.execute(createTransactionDTO.getAccountId());
    var amount =
      this.calculateAmountService.execute(
          createTransactionDTO.getAmount(),
          operationTypeEnum
        );
    account =
      this.availableCreditLimitValidationService.execute(
          account,
          operationTypeEnum,
          createTransactionDTO.getAmount()
        );

    var transaction = TransactionDTO
      .builder()
      .amount(amount)
      .account(account)
      .operationType(operationTypeEnum)
      .build();

    this.saveAccountAdapter.execute(account);
    this.saveTransactionAdapter.execute(transaction);
  }
}
