package com.samuelbraga.pierpont.application.dtos.transactions;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
  private Long id;

  private BigDecimal amount;

  private AccountDTO account;

  private OperationTypeEnum operationType;
}
