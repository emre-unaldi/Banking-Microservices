package unaldi.creditcardservice.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unaldi.creditcardservice.entity.CreditCard;
import unaldi.creditcardservice.entity.dto.CreditCardDTO;
import unaldi.creditcardservice.entity.request.CreditCardSaveRequest;
import unaldi.creditcardservice.entity.request.CreditCardUpdateRequest;
import unaldi.creditcardservice.repository.CreditCardRepository;
import unaldi.creditcardservice.service.abstracts.CreditCardService;
import unaldi.creditcardservice.service.abstracts.mapper.CreditCardMapper;
import unaldi.creditcardservice.utils.constant.ExceptionMessages;
import unaldi.creditcardservice.utils.constant.Messages;
import unaldi.creditcardservice.utils.exception.customExceptions.CreditCardNotFoundException;
import unaldi.creditcardservice.utils.result.DataResult;
import unaldi.creditcardservice.utils.result.Result;
import unaldi.creditcardservice.utils.result.SuccessDataResult;
import unaldi.creditcardservice.utils.result.SuccessResult;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Service
public class CreditCardImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public DataResult<CreditCardDTO> save(CreditCardSaveRequest creditCardSaveRequest) {
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
}
