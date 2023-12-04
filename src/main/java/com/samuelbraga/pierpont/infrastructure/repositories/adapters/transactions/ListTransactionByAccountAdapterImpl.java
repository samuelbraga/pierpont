package com.samuelbraga.pierpont.infrastructure.repositories.adapters.transactions;

import com.samuelbraga.pierpont.application.adapters.transactions.ListTransactionByAccountAdapter;
import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.application.mapper.AccountMapper;
import com.samuelbraga.pierpont.application.mapper.TransactionMapper;
import com.samuelbraga.pierpont.infrastructure.repositories.TransactionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListTransactionByAccountAdapterImpl
  implements ListTransactionByAccountAdapter {
  private final TransactionRepository repository;
  private final TransactionMapper transactionMapper;
  private final AccountMapper accountMapper;

  @Override
  public List<TransactionDTO> execute(AccountDTO accountDTO) {
    var account = this.accountMapper.fromAccountDTOToAccount(accountDTO);
    var transactions = repository.findAllByAccount(account);
    return transactions
      .stream()
      .map(transactionMapper::fromTransactionToTransactionDTO)
      .toList();
  }
}
