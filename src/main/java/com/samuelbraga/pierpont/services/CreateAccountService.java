package com.samuelbraga.pierpont.services;

import static com.samuelbraga.pierpont.Constants.ERROR_ACCOUNT_NUMBER_EXISTS;

import com.samuelbraga.pierpont.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.models.Account;
import com.samuelbraga.pierpont.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountService {
  private final AccountRepository repository;

  public void execute(String documentNumber) {
    this.validateAccountNumber(documentNumber);
    var account = Account.builder().documentNumber(documentNumber).build();
    this.repository.save(account);
  }

  private void validateAccountNumber(String documentNumber) {
    this.repository.findByDocumentNumber(documentNumber)
      .ifPresent(
        _account -> {
          throw new ExceptionBase(
            ERROR_ACCOUNT_NUMBER_EXISTS,
            HttpStatus.BAD_REQUEST
          );
        }
      );
  }
}
