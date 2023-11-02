package com.samuelbraga.pierpont.infrastructure.controllers;

import com.samuelbraga.pierpont.application.handles.transactions.CreateTransactionalHandle;
import com.samuelbraga.pierpont.application.mapper.TransactionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.TransactionApi;
import org.openapitools.model.CreateTransactionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionalController implements TransactionApi {
  private final CreateTransactionalHandle createTransactionalHandle;
  private final TransactionMapper transactionMapper;

  @Override
  public ResponseEntity<Void> createTransaction(
    @Valid @RequestBody CreateTransactionRequest createTransactionRequest
  ) {
    var createTransactionDTO =
      this.transactionMapper.fromCreateTransactionRequestToCreateTransactionDTO(
          createTransactionRequest
        );
    this.createTransactionalHandle.execute(createTransactionDTO);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
