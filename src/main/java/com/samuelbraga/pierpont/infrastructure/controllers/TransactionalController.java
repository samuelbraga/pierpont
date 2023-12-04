package com.samuelbraga.pierpont.infrastructure.controllers;

import static com.samuelbraga.pierpont.Constants.ACCOUNT_ID_PARAM;

import com.samuelbraga.pierpont.application.handles.transactions.CreateTransactionalHandle;
import com.samuelbraga.pierpont.application.handles.transactions.ListTransactionHandle;
import com.samuelbraga.pierpont.application.mapper.TransactionMapper;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.TransactionApi;
import org.openapitools.model.CreateTransactionRequest;
import org.openapitools.model.TransactionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionalController implements TransactionApi {
  private final CreateTransactionalHandle createTransactionalHandle;
  private final ListTransactionHandle listTransactionHandle;
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

  @Override
  public ResponseEntity<List<TransactionResponse>> listTransactions(
    @NotNull @Parameter(
      name = ACCOUNT_ID_PARAM,
      required = true
    ) @Valid @RequestParam(value = ACCOUNT_ID_PARAM) Long accountId
  ) {
    var transactions = this.listTransactionHandle.execute(accountId);
    var response = transactions
      .stream()
      .map(this.transactionMapper::fromTransactionDTIOToTransactionResponse)
      .toList();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
