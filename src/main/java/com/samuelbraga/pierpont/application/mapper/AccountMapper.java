package com.samuelbraga.pierpont.application.mapper;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.accounts.CreateAccountDTO;
import com.samuelbraga.pierpont.infrastructure.repositories.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.AccountResponse;
import org.openapitools.model.CreateAccountRequest;

@Mapper(componentModel = "spring")
public interface AccountMapper {
  @Mapping(source = "accountId", target = "id")
  Account fromAccountDTOToAccount(AccountDTO from);

  @Mapping(source = "id", target = "accountId")
  AccountDTO fromAccountToAccountDTO(Account from);

  AccountResponse fromAccountDTOToAccountResponse(AccountDTO from);
  CreateAccountDTO fromCreateAccountRequestToCreateAccountDTO(CreateAccountRequest from);
}
