package unaldi.accountservice.service.abstracts;

import unaldi.accountservice.entity.dto.AccountDTO;
import unaldi.accountservice.entity.request.AccountSaveRequest;
import unaldi.accountservice.entity.request.AccountUpdateRequest;
import unaldi.accountservice.utils.result.DataResult;
import unaldi.accountservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface AccountService {
    DataResult<AccountDTO> save(AccountSaveRequest accountSaveRequest);
    DataResult<AccountDTO> update(AccountUpdateRequest accountUpdateRequest);
    Result deleteById(Long accountId);
    DataResult<AccountDTO> findById(Long accountId);
    DataResult<List<AccountDTO>> findAll();
}
