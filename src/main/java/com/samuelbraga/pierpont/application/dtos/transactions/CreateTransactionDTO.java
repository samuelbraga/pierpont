package com.samuelbraga.pierpont.application.dtos.transactions;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionDTO {
  private BigDecimal amount;

  private Long accountId;

  private OperationTypeEnum operationType;
}
