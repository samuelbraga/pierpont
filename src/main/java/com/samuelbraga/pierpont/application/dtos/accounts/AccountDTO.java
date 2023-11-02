package com.samuelbraga.pierpont.application.dtos.accounts;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
  private Long accountId;

  private String documentNumber;

  private BigDecimal availableCreditLimit;
}
