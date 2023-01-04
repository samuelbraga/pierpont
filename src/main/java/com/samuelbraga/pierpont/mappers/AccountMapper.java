package com.samuelbraga.pierpont.mappers;

import com.samuelbraga.pierpont.models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.AccountResponse;

@Mapper(componentModel = "spring")
public interface AccountMapper {
  @Mapping(source = "id", target = "accountId")
  AccountResponse fromAccount(Account from);
}
