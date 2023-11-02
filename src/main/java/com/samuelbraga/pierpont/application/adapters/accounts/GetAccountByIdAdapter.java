package com.samuelbraga.pierpont.application.adapters.accounts;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import java.util.Optional;

public interface GetAccountByIdAdapter {
  Optional<AccountDTO> execute(Long accountId);
}
