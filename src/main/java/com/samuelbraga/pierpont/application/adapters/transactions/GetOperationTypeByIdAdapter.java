package com.samuelbraga.pierpont.application.adapters.transactions;

import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;
import java.util.Optional;

public interface GetOperationTypeByIdAdapter {
  Optional<OperationTypeEnum> execute(Long id);
}
