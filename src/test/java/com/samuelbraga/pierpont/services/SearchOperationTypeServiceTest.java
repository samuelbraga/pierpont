//package com.samuelbraga.pierpont.services;
//
//import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
//import com.samuelbraga.pierpont.domain.models.OperationType;
//import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
//import com.samuelbraga.pierpont.infrastructure.repositories.OperationTypeRepository;
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
//class SearchOperationTypeServiceTest {
//  @Mock
//  private OperationTypeRepository repository;
//
//  private SearchOperationTypeService unit;
//
//  @BeforeEach
//  void setUp() {
//    unit = new SearchOperationTypeService(repository);
//  }
//
//  @Test
//  void testWhenIExecuteShouldBeSuccess() {
//    var operation = OperationTypeEnum.CASH_PURCHASE;
//
//    var expected = OperationType
//      .builder()
//      .id(operation.getValue().longValue())
//      .description(operation.getKey())
//      .build();
//
//    Mockito
//      .when(this.repository.findById(operation.getValue().longValue()))
//      .thenReturn(Optional.of(expected));
//
//    var result = this.unit.execute(operation);
//
//    Assert.assertEquals(expected, result);
//  }
//
//  @Test
//  void testWhenIExecuteShouldBeFailed() {
//    var operation = OperationTypeEnum.CASH_PURCHASE;
//
//    Mockito
//      .when(this.repository.findById(operation.getValue().longValue()))
//      .thenReturn(Optional.empty());
//
//    Assert.assertThrows(
//      ExceptionBase.class,
//      () -> this.unit.execute(operation)
//    );
//  }
//}
