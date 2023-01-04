package com.samuelbraga.pierpont.controllers;

import com.samuelbraga.pierpont.services.CreateTransactionalService;
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
  private final CreateTransactionalService createTransactionalService;

  @Override
  public ResponseEntity<Void> createTransaction(
    @Valid @RequestBody CreateTransactionRequest createTransactionRequest
  ) {
    this.createTransactionalService.execute(createTransactionRequest);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
