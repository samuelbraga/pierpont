package com.samuelbraga.pierpont.services;

import com.samuelbraga.pierpont.models.OperationTypeEnum;
import com.samuelbraga.pierpont.models.Transaction;
import com.samuelbraga.pierpont.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CreateTransactionRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransactionalService {
  private final TransactionRepository repository;
  private final SearchAccountService searchAccountService;
  private final SearchOperationTypeService searchOperationTypeService;

  public void execute(CreateTransactionRequest request) {
    var operationTypeEnum = OperationTypeEnum.valueOf(
      request.getOperationType().name()
    );
    var operationType =
      this.searchOperationTypeService.execute(operationTypeEnum);
    var account = this.searchAccountService.execute(request.getAccountId());
    var transaction = Transaction
      .builder()
      .amount(request.getAmount())
      .account(account)
      .operationType(operationType)
      .build();
    this.repository.save(transaction);
  }
}
