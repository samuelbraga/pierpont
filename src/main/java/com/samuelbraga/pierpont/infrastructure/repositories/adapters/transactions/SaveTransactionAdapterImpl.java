package com.samuelbraga.pierpont.infrastructure.repositories.adapters.transactions;

import com.samuelbraga.pierpont.application.adapters.transactions.SaveTransactionAdapter;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.application.mapper.AccountMapper;
import com.samuelbraga.pierpont.application.mapper.OperationTypeMapper;
import com.samuelbraga.pierpont.application.mapper.TransactionMapper;
import com.samuelbraga.pierpont.infrastructure.repositories.TransactionRepository;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveTransactionAdapterImpl implements SaveTransactionAdapter {
  public final TransactionRepository repository;
  public final TransactionMapper transactionMapper;
  public final OperationTypeMapper operationTypeMapper;
  public final AccountMapper accountMapper;

  @Override
  public TransactionDTO execute(TransactionDTO transactionDTO) {
    var operationType =
      this.operationTypeMapper.fromOperationTypeEnumToOperationType(
          transactionDTO.getOperationType()
        );
    var account = this.accountMapper.fromAccountDTOToAccount(transactionDTO.getAccount());

    var transaction = Transaction
      .builder()
      .account(account)
      .operationType(operationType)
      .amount(transactionDTO.getAmount())
      .build();

    var savedTransaction = this.repository.save(transaction);
    return this.transactionMapper.fromTransactionToTransactionDTO(savedTransaction);
  }
}
