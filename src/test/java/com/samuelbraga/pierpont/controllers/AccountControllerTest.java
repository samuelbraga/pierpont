//package com.samuelbraga.pierpont.controllers;
//
//import com.samuelbraga.pierpont.infrastructure.controllers.AccountController;
//import com.samuelbraga.pierpont.mappers.AccountMapperImpl;
//import com.samuelbraga.pierpont.domain.models.Account;
//import com.samuelbraga.pierpont.domain.useCases.accounts.CreateAccountHandleImpl;
//import com.samuelbraga.pierpont.infrastructure.repositories.adapters.accounts.GetAccountByIdAdapterImpl;
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.openapitools.model.AccountResponse;
//import org.openapitools.model.CreateAccountRequest;
//import org.springframework.http.HttpStatus;
//
//@ExtendWith(MockitoExtension.class)
//class AccountControllerTest {
//  @Mock
//  private CreateAccountHandleImpl createAccountHandleImpl;
//
//  @Mock
//  private GetAccountByIdAdapterImpl getAccountByIdHandleImpl;
//
//  @Mock
//  private AccountMapperImpl accountMapper;
//
//  private AccountController unit;
//
//  @BeforeEach
//  void setUp() {
//    unit =
//      new AccountController(
//              createAccountHandleImpl,
//              getAccountByIdHandleImpl,
//        accountMapper
//      );
//  }
//
//  @Test
//  void testWhenICreateAccountShouldBeSuccess() {
//    var documentNumber = "123456";
//    var request = new CreateAccountRequest();
//    request.setDocumentNumber(documentNumber);
//
//    var response = this.unit.createAccount(request);
//
//    Mockito
//      .verify(createAccountHandleImpl, Mockito.times(1))
//      .execute(documentNumber);
//    Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//  }
//
//  @Test
//  void testWhenISearchAccountShouldBeSuccess() {
//    var accountId = 13L;
//    var documentNumber = "123456";
//    var account = Account
//      .builder()
//      .id(13L)
//      .documentNumber(documentNumber)
//      .build();
//
//    var accountResponse = new AccountResponse();
//    accountResponse.setAccountId(13L);
//    accountResponse.setDocumentNumber(documentNumber);
//
//    Mockito.when(accountMapper.fromAccountToAccountDTO(account)).thenCallRealMethod();
//    Mockito.when(getAccountByIdHandleImpl.execute(accountId)).thenReturn(account);
//
//    var response = this.unit.searchAccount(accountId);
//
//    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
//    Assert.assertEquals(accountResponse, response.getBody());
//  }
//}
