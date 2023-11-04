package com.samuelbraga.pierpont.infrastructure.repository.adapters.transactions;

import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import com.samuelbraga.pierpont.application.mapper.OperationTypeMapperImpl;
import com.samuelbraga.pierpont.infrastructure.repositories.OperationTypeRepository;
import com.samuelbraga.pierpont.infrastructure.repositories.adapters.transactions.GetOperationTypeByIdAdapterImpl;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.OperationType;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetOperationTypeByIdAdapterImplTest {
  @Mock
  private OperationTypeRepository repository;

  @Mock
  private OperationTypeMapperImpl mapper;

  private GetOperationTypeByIdAdapterImpl unit;

  @BeforeEach
  void setUp() {
    unit = new GetOperationTypeByIdAdapterImpl(repository, mapper);
  }

  @ParameterizedTest
  @ValueSource(
    strings = { "CASH_PURCHASE", "INSTALLMENT_PURCHASE", "WITHDRAWAL" }
  )
  void testExecuteWhenRepositoryContainOperationType(
    String operationTypeDescription
  ) {
    var operationTypeEnum = OperationTypeEnum.valueOf(operationTypeDescription);
    var operationTypeId = operationTypeEnum.getValue().longValue();

    var operationType = OperationType
      .builder()
      .id(operationTypeId)
      .description(operationTypeDescription)
      .build();

    var expected = OperationTypeEnum.valueOf(operationTypeDescription);

    Mockito
      .when(repository.findById(operationTypeId))
      .thenReturn(Optional.of(operationType));
    Mockito
      .when(
        mapper.fromOperationTypeToOperationTypeEnum(
          Mockito.any(OperationType.class)
        )
      )
      .thenCallRealMethod();

    var result = unit.execute(operationTypeId);

    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(expected.getKey(), result.get().getKey());
    Assertions.assertEquals(expected.getValue(), result.get().getValue());
  }

  @Test
  void testExecuteWhenRepositoryNotContainOperationType() {
    var operationTypeId = 1L;

    Mockito
      .when(repository.findById(operationTypeId))
      .thenReturn(Optional.empty());

    var result = unit.execute(operationTypeId);

    Assertions.assertTrue(result.isEmpty());
  }
}
