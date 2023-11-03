package com.samuelbraga.pierpont.infrastructure.repositories.adapters.transactions;

import com.samuelbraga.pierpont.application.adapters.transactions.GetOperationTypeByIdAdapter;
import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import com.samuelbraga.pierpont.application.mapper.OperationTypeMapper;
import com.samuelbraga.pierpont.infrastructure.repositories.OperationTypeRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOperationTypeByIdAdapterImpl implements GetOperationTypeByIdAdapter {
  private final OperationTypeRepository repository;
  private final OperationTypeMapper mapper;

  @Override
  public Optional<OperationTypeEnum> execute(Long id) {
    var operationType = this.repository.findById(id);
    return operationType.map(this.mapper::fromOperationTypeToOperationTypeEnum);
  }
}
