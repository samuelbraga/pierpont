package com.samuelbraga.pierpont.controllers;

import com.samuelbraga.pierpont.mappers.AccountMapper;
import com.samuelbraga.pierpont.services.CreateAccountService;
import com.samuelbraga.pierpont.services.SearchAccountService;
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

import static com.samuelbraga.pierpont.Constants.ACCOUNT_ID_PARAM;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountsApi {
  private final CreateAccountService createAccountService;
  private final SearchAccountService searchAccountService;
  private final AccountMapper accountMapper;

  @Override
  public ResponseEntity<AccountResponse> createAccount(
          @Valid @RequestBody CreateAccountRequest createAccountRequest
  ) {
    var account = createAccountService.execute(createAccountRequest);
    var response = this.accountMapper.fromAccount(account);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<AccountResponse> searchAccount(
    @PathVariable(ACCOUNT_ID_PARAM) Long accountId
  ) {
    var account = searchAccountService.execute(accountId);
    var response = this.accountMapper.fromAccount(account);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
