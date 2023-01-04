package com.samuelbraga.pierpont.services;

import com.samuelbraga.pierpont.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.models.Account;
import com.samuelbraga.pierpont.repositories.AccountRepository;
import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class CreateAccountServiceTest {
  @Mock
  private AccountRepository repository;

  private CreateAccountService unit;

  @BeforeEach
  void setUp() {
    unit = new CreateAccountService(repository);
  }

  @Test
  void testWhenIExecuteShouldBeSuccess() {
    var documentNumber = "123456";

    Mockito
      .when(this.repository.findByDocumentNumber(Mockito.eq(documentNumber)))
      .thenReturn(Optional.empty());

    this.unit.execute(documentNumber);

    Mockito
      .verify(this.repository, Mockito.times(1))
      .save(Mockito.any(Account.class));
  }

  @Test
  void testWhenIExecuteShouldBeFailed() {
    var documentNumber = "123456";
    var account = Account.builder().documentNumber(documentNumber).build();

    Mockito
      .when(this.repository.findByDocumentNumber(Mockito.eq(documentNumber)))
      .thenReturn(Optional.of(account));

    Assert.assertThrows(
      ExceptionBase.class,
      () -> this.unit.execute(documentNumber)
    );
  }
}
