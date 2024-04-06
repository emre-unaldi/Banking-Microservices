package unaldi.bankservice.service.abstracts;

import unaldi.bankservice.entity.dto.BankDTO;
import unaldi.bankservice.entity.request.BankSaveRequest;
import unaldi.bankservice.entity.request.BankUpdateRequest;
import unaldi.bankservice.utils.result.DataResult;
import unaldi.bankservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface BankService {
    DataResult<BankDTO> save(BankSaveRequest bankSaveRequest);
    DataResult<BankDTO> update(BankUpdateRequest bankUpdateRequest);
    Result deleteById(Long bankId);
    DataResult<BankDTO> findById(Long bankId);
    DataResult<List<BankDTO>> findAll();
}
