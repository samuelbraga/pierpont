package com.samuelbraga.pierpont.infrastructure.controllers;

import static com.samuelbraga.pierpont.Constants.ACCOUNT_ID_PARAM;

import com.samuelbraga.pierpont.application.handles.accounts.CreateAccountHandle;
import com.samuelbraga.pierpont.application.handles.accounts.SearchAccountHandle;
import com.samuelbraga.pierpont.application.mapper.AccountMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.AccountsApi;
import org.openapitools.model.AccountResponse;
import org.openapitools.model.CreateAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountsApi {
  private final CreateAccountHandle createAccountHandle;
  private final SearchAccountHandle searchAccountHandle;
  private final AccountMapper accountMapper;

  @Override
  public ResponseEntity<AccountResponse> createAccount(
    @Valid @RequestBody CreateAccountRequest createAccountRequest
  ) {
    var createAccountDTO =
      this.accountMapper.fromCreateAccountRequestToCreateAccountDTO(createAccountRequest);
    var accountDTO = this.createAccountHandle.execute(createAccountDTO);
    var response = this.accountMapper.fromAccountDTOToAccountResponse(accountDTO);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<AccountResponse> searchAccount(
    @PathVariable(ACCOUNT_ID_PARAM) Long accountId
  ) {
    var accountDTO = searchAccountHandle.execute(accountId);
    var response = this.accountMapper.fromAccountDTOToAccountResponse(accountDTO);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
