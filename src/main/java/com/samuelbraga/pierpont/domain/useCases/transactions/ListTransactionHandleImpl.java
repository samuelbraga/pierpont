package com.samuelbraga.pierpont.domain.useCases.transactions;

import com.samuelbraga.pierpont.application.adapters.transactions.ListTransactionByAccountAdapter;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.application.handles.transactions.ListTransactionHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListTransactionHandleImpl implements ListTransactionHandle {
    private final ListTransactionByAccountAdapter listTransactionByAccountAdapter;

    @Override
    public List<TransactionDTO> execute(Long accountId) {
        return listTransactionByAccountAdapter.execute(accountId);
    }
}
