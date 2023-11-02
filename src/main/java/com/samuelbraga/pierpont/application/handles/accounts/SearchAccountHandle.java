package com.samuelbraga.pierpont.application.handles.accounts;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;

public interface SearchAccountHandle {
  AccountDTO execute(Long accountId);
}
