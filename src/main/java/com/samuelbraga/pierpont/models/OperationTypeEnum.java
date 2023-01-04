package com.samuelbraga.pierpont.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OperationTypeEnum {
  CASH_PURCHASE("CASH_PURCHASE", 1),
  INSTALLMENT_PURCHASE("INSTALLMENT_PURCHASE", 2),
  WITHDRAWAL("WITHDRAWAL", 3),
  PAYMENT("PAYMENT", 4);

  @Getter
  final String key;

  @Getter
  private final Integer value;
}
