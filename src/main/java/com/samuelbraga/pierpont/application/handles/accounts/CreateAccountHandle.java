package com.samuelbraga.pierpont.application.handles.accounts;

import com.samuelbraga.pierpont.application.dtos.accounts.AccountDTO;
import com.samuelbraga.pierpont.application.dtos.accounts.CreateAccountDTO;

public interface CreateAccountHandle {
  AccountDTO execute(CreateAccountDTO createAccountDTO);
}
