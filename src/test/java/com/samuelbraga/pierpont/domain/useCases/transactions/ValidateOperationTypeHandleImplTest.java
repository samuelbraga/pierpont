package com.samuelbraga.pierpont.domain.useCases.transactions;

import static com.samuelbraga.pierpont.Constants.ERROR_OPERATION_TYPE_NOT_EXISTS;

import com.samuelbraga.pierpont.application.adapters.transactions.GetOperationTypeByIdAdapter;
import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
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
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class ValidateOperationTypeHandleImplTest {
  @Mock
  private GetOperationTypeByIdAdapter getOperationTypeByIdAdapter;

  private ValidateOperationTypeHandleImpl unit;

  @BeforeEach
  void setUp() {
    unit = new ValidateOperationTypeHandleImpl(getOperationTypeByIdAdapter);
  }

  @ParameterizedTest
  @ValueSource(
    strings = { "CASH_PURCHASE", "INSTALLMENT_PURCHASE", "WITHDRAWAL" }
  )
  void testExecuteWhenPassExistsOperationType(String operationTypeDescription) {
    var operationTypeEnum = OperationTypeEnum.valueOf(operationTypeDescription);
    Mockito
      .when(
        getOperationTypeByIdAdapter.execute(
          operationTypeEnum.getValue().longValue()
        )
      )
      .thenReturn(Optional.of(operationTypeEnum));

    unit.execute(operationTypeEnum);

    Mockito
      .verify(getOperationTypeByIdAdapter, Mockito.times(1))
      .execute(operationTypeEnum.getValue().longValue());
  }

  @Test
  void testExecuteWhenPassNotExistsOperationType() {
    Mockito
      .when(getOperationTypeByIdAdapter.execute(Mockito.any()))
      .thenReturn(Optional.empty());

    ExceptionBase exception = Assertions.assertThrows(
      ExceptionBase.class,
      () -> unit.execute(OperationTypeEnum.PAYMENT)
    );

    Assertions.assertEquals(
      ERROR_OPERATION_TYPE_NOT_EXISTS,
      exception.getMessage()
    );
    Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getCode());
  }
}
