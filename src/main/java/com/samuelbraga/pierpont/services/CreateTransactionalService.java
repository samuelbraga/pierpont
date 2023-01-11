package com.samuelbraga.pierpont.services;

import com.samuelbraga.pierpont.models.OperationTypeEnum;
import com.samuelbraga.pierpont.models.Transaction;
import com.samuelbraga.pierpont.repositories.AccountRepository;
import com.samuelbraga.pierpont.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CreateTransactionRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransactionalService {
  private final TransactionRepository repository;
  private final AccountRepository accountRepository;
  private final SearchAccountService searchAccountService;
  private final SearchOperationTypeService searchOperationTypeService;
  private final CalculateAmountService calculateAmountService;
  private final AvailableCreditLimitValidationService availableCreditLimitValidationService;

  public void execute(CreateTransactionRequest request) {
    var operationTypeEnum = OperationTypeEnum.valueOf(
            request.getOperationType().name()
    );
    var operationType =
            this.searchOperationTypeService.execute(operationTypeEnum);
    var account = this.searchAccountService.execute(request.getAccountId());

    var amount = this.calculateAmountService.execute(request.getAmount(), operationTypeEnum);
    account = this.availableCreditLimitValidationService.execute(account, operationTypeEnum, request.getAmount());

    var transaction = Transaction
            .builder()
            .amount(amount)
            .account(account)
            .operationType(operationType)
            .build();

    this.accountRepository.save(account);
    this.repository.save(transaction);
  }
}
