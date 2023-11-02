//package com.samuelbraga.pierpont.controllers;
//
//import com.samuelbraga.pierpont.infrastructure.controllers.TransactionalController;
//
//import java.math.BigDecimal;
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.openapitools.model.CreateTransactionRequest;
//import org.openapitools.model.OperationType;
//import org.springframework.http.HttpStatus;
//
//@ExtendWith(MockitoExtension.class)
//class TransactionalControllerTest {
//  @Mock
//  private CreateTransactionalService createTransactionalService;
//
//  private TransactionalController unit;
//
//  @BeforeEach
//  void setUp() {
//    unit = new TransactionalController(createTransactionalService);
//  }
//
//  @Test
//  void testWhenICreateTransactionShouldBeSuccess() {
//    var accountId = 13L;
//    var operationType = OperationType.CASH_PURCHASE;
//    var amount = BigDecimal.valueOf(-50);
//
//    var request = new CreateTransactionRequest();
//    request.setAccountId(accountId);
//    request.setOperationType(operationType);
//    request.setAmount(amount);
//
//    var response = this.unit.createTransaction(request);
//
//    Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//  }
//}
