package com.samuelbraga.pierpont.services;

import static com.samuelbraga.pierpont.Constants.ERROR_ACCOUNT_NUMBER_EXISTS;

import com.samuelbraga.pierpont.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.models.Account;
import com.samuelbraga.pierpont.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CreateAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountService {
  private final AccountRepository repository;

  public Account execute(CreateAccountRequest request) {
    this.validateAccountNumber(request.getDocumentNumber());
    var account = Account
      .builder()
      .documentNumber(request.getDocumentNumber())
      .availableCreditLimit(request.getAvailableCreditLimit())
      .build();
    return this.repository.save(account);
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
