package com.samuelbraga.pierpont.infrastructure.controllers;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.accounts.CreateAccountDTO;
import com.samuelbraga.pierpont.application.handles.accounts.CreateAccountHandle;
import com.samuelbraga.pierpont.application.handles.accounts.SearchAccountHandle;
import com.samuelbraga.pierpont.application.mapper.AccountMapperImpl;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.AccountResponse;
import org.openapitools.model.CreateAccountRequest;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
  @Mock
  private CreateAccountHandle createAccountHandle;

  @Mock
  private SearchAccountHandle searchAccountHandle;

  @Mock
  private AccountMapperImpl accountMapper;

  private AccountController unit;

  @BeforeEach
  void setUp() {
    unit =
      new AccountController(
        createAccountHandle,
        searchAccountHandle,
        accountMapper
      );
  }

  @Test
  void testWhenICreateAccountShouldBeSuccess() {
    var documentNumber = "123456";
    var availableCreditLimit = BigDecimal.valueOf(10);

    var request = new CreateAccountRequest();
    request.setDocumentNumber(documentNumber);
    request.setAvailableCreditLimit(availableCreditLimit);

    var accountDTO = AccountDTO
      .builder()
      .accountId(1L)
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

    var expectedResponse = new AccountResponse();
    expectedResponse.accountId(1L);
    expectedResponse.documentNumber(documentNumber);
    expectedResponse.availableCreditLimit(availableCreditLimit);

    Mockito
      .when(createAccountHandle.execute(Mockito.any(CreateAccountDTO.class)))
      .thenReturn(accountDTO);
    Mockito
      .when(accountMapper.fromCreateAccountRequestToCreateAccountDTO(request))
      .thenCallRealMethod();
    Mockito
      .when(accountMapper.fromAccountDTOToAccountResponse(accountDTO))
      .thenCallRealMethod();

    var response = this.unit.createAccount(request);

    Mockito
      .verify(createAccountHandle, Mockito.times(1))
      .execute(Mockito.any(CreateAccountDTO.class));

    Assertions.assertEquals(expectedResponse, response.getBody());
    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  void testWhenISearchAccountShouldBeSuccess() {
    var accountId = 1L;
    var documentNumber = "123456";
    var availableCreditLimit = BigDecimal.valueOf(10);

    var accountDTO = AccountDTO
      .builder()
      .accountId(1L)
      .documentNumber(documentNumber)
      .availableCreditLimit(availableCreditLimit)
      .build();

    var expectedResponse = new AccountResponse();
    expectedResponse.accountId(1L);
    expectedResponse.documentNumber(documentNumber);
    expectedResponse.availableCreditLimit(availableCreditLimit);

    Mockito.when(searchAccountHandle.execute(accountId)).thenReturn(accountDTO);
    Mockito
      .when(accountMapper.fromAccountDTOToAccountResponse(accountDTO))
      .thenCallRealMethod();

    var response = this.unit.searchAccount(accountId);

    Mockito.verify(searchAccountHandle, Mockito.times(1)).execute(accountId);

    Assertions.assertEquals(expectedResponse, response.getBody());
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}
