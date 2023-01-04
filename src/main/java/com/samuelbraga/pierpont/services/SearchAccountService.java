package com.samuelbraga.pierpont.services;

import static com.samuelbraga.pierpont.Constants.ERROR_ACCOUNT_ID_NOT_EXISTS;

import com.samuelbraga.pierpont.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.models.Account;
import com.samuelbraga.pierpont.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchAccountService {
  private final AccountRepository repository;

  public Account execute(Long accountId) {
    return this.repository.findById(accountId)
      .orElseThrow(
        () ->
          new ExceptionBase(ERROR_ACCOUNT_ID_NOT_EXISTS, HttpStatus.BAD_REQUEST)
      );
  }
}
