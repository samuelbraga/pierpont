package com.samuelbraga.pierpont;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
  public static final String ERROR_ACCOUNT_NUMBER_EXISTS =
    "Document number already exists";
  public static final String ERROR_ACCOUNT_ID_NOT_EXISTS =
    "Account id not exist";
  public static final String ERROR_OPERATION_TYPE_NOT_EXISTS =
    "Operation type not exists";
  public static final String ERROR_AVAILABLE_CREDIT_LIMIT_EXCEEDED =
    "Available credit limit was exceeded";
  public static final String ACCOUNT_ID_PARAM = "accountId";
}
