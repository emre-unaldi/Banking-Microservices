package unaldi.creditcardservice.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unaldi.creditcardservice.utils.client.BankServiceClient;
import unaldi.creditcardservice.utils.client.UserServiceClient;
import unaldi.creditcardservice.entity.CreditCard;
import unaldi.creditcardservice.entity.dto.CreditCardDTO;
import unaldi.creditcardservice.entity.request.CreditCardSaveRequest;
import unaldi.creditcardservice.entity.request.CreditCardUpdateRequest;
import unaldi.creditcardservice.repository.CreditCardRepository;
import unaldi.creditcardservice.service.abstracts.CreditCardService;
import unaldi.creditcardservice.service.abstracts.mapper.CreditCardMapper;
import unaldi.creditcardservice.utils.client.response.BankResponse;
import unaldi.creditcardservice.utils.client.response.RestResponse;
import unaldi.creditcardservice.utils.client.response.UserResponse;
import unaldi.creditcardservice.utils.constant.ExceptionMessages;
import unaldi.creditcardservice.utils.constant.Messages;
import unaldi.creditcardservice.utils.exception.customExceptions.CreditCardNotFoundException;
import unaldi.creditcardservice.utils.result.*;

import java.util.List;
import java.util.Objects;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Service
public class CreditCardImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final UserServiceClient userServiceClient;
    private final BankServiceClient bankServiceClient;

    @Autowired
    public CreditCardImpl(CreditCardRepository creditCardRepository, UserServiceClient userServiceClient, BankServiceClient bankServiceClient)
    {
        this.creditCardRepository = creditCardRepository;
        this.userServiceClient = userServiceClient;
        this.bankServiceClient = bankServiceClient;
    }

    @Override
    public DataResult<CreditCardDTO> save(CreditCardSaveRequest creditCardSaveRequest) {
        userServiceClient.findById(creditCardSaveRequest.userId());
        bankServiceClient.findById(creditCardSaveRequest.bankId());

        CreditCard creditCard = CreditCardMapper.INSTANCE.convertToSaveCreditCard(creditCardSaveRequest);
        this.creditCardRepository.save(creditCard);

        return new SuccessDataResult<>(
                CreditCardMapper.INSTANCE.convertToCreditCardDTO(creditCard),
                Messages.CREDIT_CARD_CREATED
        );
    }

    @Override
    public DataResult<CreditCardDTO> update(CreditCardUpdateRequest creditCardUpdateRequest) {
        if(!this.creditCardRepository.existsById(creditCardUpdateRequest.id()))
            throw new CreditCardNotFoundException(ExceptionMessages.CREDIT_CARD_NOT_FOUND);

        userServiceClient.findById(creditCardUpdateRequest.userId());
        bankServiceClient.findById(creditCardUpdateRequest.bankId());

        CreditCard creditCard = CreditCardMapper.INSTANCE.convertToUpdateCreditCard(creditCardUpdateRequest);
        this.creditCardRepository.save(creditCard);

        return new SuccessDataResult<>(
                CreditCardMapper.INSTANCE.convertToCreditCardDTO(creditCard),
                Messages.CREDIT_CARD_UPDATED
        );
    }

    @Override
    public Result deleteById(Long creditCardId) {
        CreditCard creditCard = this.creditCardRepository
                .findById(creditCardId)
                .orElseThrow(() -> new CreditCardNotFoundException((ExceptionMessages.CREDIT_CARD_NOT_FOUND)));

        this.creditCardRepository.deleteById(creditCard.getId());

        return new SuccessResult(Messages.CREDIT_CARD_DELETED);
    }

    @Override
    public DataResult<CreditCardDTO> findById(Long creditCardId) {
        CreditCardDTO creditCardDTO = this.creditCardRepository
                .findById(creditCardId)
                .map(CreditCardMapper.INSTANCE::convertToCreditCardDTO)
                .orElseThrow(() -> new CreditCardNotFoundException((ExceptionMessages.CREDIT_CARD_NOT_FOUND)));

        return new SuccessDataResult<>(creditCardDTO, Messages.CREDIT_CARD_FOUND);
    }

    @Override
    public DataResult<List<CreditCardDTO>> findAll() {
        List<CreditCard> creditCardList = this.creditCardRepository.findAll();

        return new SuccessDataResult<>(
                CreditCardMapper.INSTANCE.convertCreditCardDTOs(creditCardList),
                Messages.CREDIT_CARDS_LISTED
        );
    }

    @Override
    public DataResult<UserResponse> findCreditCardUserByUserId(Long userId) {
        ResponseEntity<RestResponse<UserResponse>> response = userServiceClient.findById(userId);

        UserResponse userResponse = Objects.requireNonNull(response.getBody()).getData();

        return new SuccessDataResult<>(
                userResponse,
                Messages.CREDIT_CARD_USER_FOUND
        );
    }

    @Override
    public DataResult<BankResponse> findCreditCardBankByUserId(Long bankId) {
        ResponseEntity<RestResponse<BankResponse>> response = bankServiceClient.findById(bankId);

        BankResponse bankResponse = Objects.requireNonNull(response.getBody()).getData();

        return new SuccessDataResult<>(
                bankResponse,
                Messages.CREDIT_CARD_BANK_FOUND
        );
    }
}
