package com.samuelbraga.pierpont.domain.useCases.transactions;

import static com.samuelbraga.pierpont.Constants.ERROR_OPERATION_TYPE_NOT_EXISTS;

import com.samuelbraga.pierpont.application.adapters.transactions.GetOperationTypeByIdAdapter;
import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import com.samuelbraga.pierpont.application.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.application.handles.transactions.ValidateOperationTypeHandle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateOperationTypeHandleImpl implements ValidateOperationTypeHandle {
  private final GetOperationTypeByIdAdapter getOperationTypeByIdAdapter;

  @Override
  public void execute(OperationTypeEnum operationTypeEnum) {
    this.getOperationTypeByIdAdapter.execute(operationTypeEnum.getValue().longValue())
      .orElseThrow(
        () -> new ExceptionBase(ERROR_OPERATION_TYPE_NOT_EXISTS, HttpStatus.BAD_REQUEST)
      );
  }
}
