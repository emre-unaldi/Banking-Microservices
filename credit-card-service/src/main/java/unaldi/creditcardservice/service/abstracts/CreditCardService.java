package unaldi.creditcardservice.service.abstracts;


import unaldi.creditcardservice.entity.dto.CreditCardDTO;
import unaldi.creditcardservice.entity.request.CreditCardSaveRequest;
import unaldi.creditcardservice.entity.request.CreditCardUpdateRequest;
import unaldi.creditcardservice.utils.client.dto.BankResponse;
import unaldi.creditcardservice.utils.client.dto.UserResponse;
import unaldi.creditcardservice.utils.result.DataResult;
import unaldi.creditcardservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface CreditCardService {
    DataResult<CreditCardDTO> save(CreditCardSaveRequest creditCardSaveRequest);
    DataResult<CreditCardDTO> update(CreditCardUpdateRequest creditCardUpdateRequest);
    Result deleteById(Long creditCardId);
    DataResult<CreditCardDTO> findById(Long creditCardId);
    DataResult<List<CreditCardDTO>> findAll();
    DataResult<UserResponse> findCreditCardUserByUserId(Long userId);
    DataResult<BankResponse> findCreditCardBankByUserId(Long bankId);
}
