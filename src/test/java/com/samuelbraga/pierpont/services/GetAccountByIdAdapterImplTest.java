//package com.samuelbraga.pierpont.services;
//
//import com.samuelbraga.pierpont.infrastructure.repositories.adapters.accounts.GetAccountByIdAdapterImpl;
//import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
//import com.samuelbraga.pierpont.domain.models.Account;
//import com.samuelbraga.pierpont.infrastructure.repositories.AccountRepository;
//import java.util.Optional;
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class GetAccountByIdAdapterImplTest {
//  @Mock
//  private AccountRepository repository;
//
//  private GetAccountByIdAdapterImpl unit;
//
//  @BeforeEach
//  void setUp() {
//    unit = new GetAccountByIdAdapterImpl(repository);
//  }
//
//  @Test
//  void testWhenIExecuteShouldBeSuccess() {
//    var accountId = 13L;
//    var documentNumber = "123456";
//    var expected = Account
//      .builder()
//      .id(accountId)
//      .documentNumber(documentNumber)
//      .build();
//
//    Mockito
//      .when(this.repository.findById(accountId))
//      .thenReturn(Optional.of(expected));
//
//    var result = this.unit.execute(accountId);
//
//    Assert.assertEquals(expected, result);
//  }
//
//  @Test
//  void testWhenIExecuteShouldBeFailed() {
//    var accountId = 13L;
//
//    Mockito
//      .when(this.repository.findById(accountId))
//      .thenReturn(Optional.empty());
//
//    Assert.assertThrows(
//      ExceptionBase.class,
//      () -> this.unit.execute(accountId)
//    );
//  }
//}
