package com.samuelbraga.pierpont.services;

import static com.samuelbraga.pierpont.Constants.ERROR_OPERATION_TYPE_NOT_EXISTS;

import com.samuelbraga.pierpont.exceptions.ExceptionBase;
import com.samuelbraga.pierpont.models.OperationType;
import com.samuelbraga.pierpont.models.OperationTypeEnum;
import com.samuelbraga.pierpont.repositories.OperationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchOperationTypeService {
  private final OperationTypeRepository repository;

  public OperationType execute(OperationTypeEnum operationTypeEnum) {
    return this.repository.findById(operationTypeEnum.getValue().longValue())
      .orElseThrow(
        () ->
          new ExceptionBase(
            ERROR_OPERATION_TYPE_NOT_EXISTS,
            HttpStatus.BAD_REQUEST
          )
      );
  }
}
