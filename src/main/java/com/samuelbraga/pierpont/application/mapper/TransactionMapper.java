package com.samuelbraga.pierpont.application.mapper;

import com.samuelbraga.pierpont.application.dtos.transactions.CreateTransactionDTO;
import com.samuelbraga.pierpont.application.dtos.transactions.TransactionDTO;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.Transaction;
import org.mapstruct.Mapper;
import org.openapitools.model.CreateTransactionRequest;
import org.openapitools.model.TransactionResponse;

@Mapper(
  componentModel = "spring",
  uses = { OperationTypeMapper.class, AccountMapper.class }
)
public interface TransactionMapper {
  TransactionDTO fromTransactionToTransactionDTO(Transaction from);
  CreateTransactionDTO fromCreateTransactionRequestToCreateTransactionDTO(
    CreateTransactionRequest from
  );
  TransactionResponse fromTransactionDTIOToTransactionResponse(TransactionDTO from);
}
