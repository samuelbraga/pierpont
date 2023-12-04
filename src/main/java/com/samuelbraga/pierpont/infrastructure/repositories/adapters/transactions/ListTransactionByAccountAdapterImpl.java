package com.samuelbraga.pierpont.infrastructure.repositories.adapters.transactions;

import com.samuelbraga.pierpont.application.adapters.transactions.ListTransactionByAccountAdapter;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.application.mapper.TransactionMapper;
import com.samuelbraga.pierpont.infrastructure.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTransactionByAccountAdapterImpl implements ListTransactionByAccountAdapter {
    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    @Override
    public List<TransactionDTO> execute(Long accountId) {
        var transactions = repository.findAllByAccountId(accountId);
        return transactions
                .stream()
                .map(mapper::fromTransactionToTransactionDTO)
                .toList();
    }
}
