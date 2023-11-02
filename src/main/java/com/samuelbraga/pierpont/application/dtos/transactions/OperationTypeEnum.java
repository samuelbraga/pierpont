package com.samuelbraga.pierpont.application.dtos.transactions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationTypeEnum {
  CASH_PURCHASE("CASH_PURCHASE", 1),
  INSTALLMENT_PURCHASE("INSTALLMENT_PURCHASE", 2),
  WITHDRAWAL("WITHDRAWAL", 3),
  PAYMENT("PAYMENT", 4);

  final String key;

  private final Integer value;
}
