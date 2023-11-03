package com.samuelbraga.pierpont.application.mapper;

import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.OperationType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationTypeMapper {
  default OperationType fromOperationTypeEnumToOperationType(
    OperationTypeEnum from
  ) {
    return OperationType
      .builder()
      .id(from.getValue().longValue())
      .description(from.getKey())
      .build();
  }

  default OperationTypeEnum fromOperationTypeToOperationTypeEnum(
    OperationType from
  ) {
    return OperationTypeEnum.valueOf(from.getDescription());
  }
}
