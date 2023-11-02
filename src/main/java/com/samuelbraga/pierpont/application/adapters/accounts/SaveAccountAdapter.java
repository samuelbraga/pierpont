package com.samuelbraga.pierpont.application.adapters.accounts;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;

public interface SaveAccountAdapter {
  AccountDTO execute(AccountDTO account);
}
