package com.samuelbraga.pierpont.application.dtos.accounts;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountDTO {
  private String documentNumber;
  private BigDecimal availableCreditLimit;
}
