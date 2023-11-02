package com.samuelbraga.pierpont.application.handles.transactions;

import com.samuelbraga.pierpont.application.dtos.transactions.OperationTypeEnum;

public interface ValidateOperationTypeHandle {
  void execute(OperationTypeEnum operationTypeEnum);
}
