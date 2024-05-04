package unaldi.creditcardservice.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unaldi.creditcardservice.utils.constant.Caches;
import unaldi.creditcardservice.utils.rabbitMQ.enums.LogType;
import unaldi.creditcardservice.utils.rabbitMQ.enums.OperationType;
import unaldi.creditcardservice.utils.rabbitMQ.producer.LogProducer;
import unaldi.creditcardservice.utils.rabbitMQ.request.LogRequest;
import unaldi.creditcardservice.utils.client.BankServiceClient;
import unaldi.creditcardservice.utils.client.UserServiceClient;
import unaldi.creditcardservice.entity.CreditCard;
import unaldi.creditcardservice.entity.dto.CreditCardDTO;
import unaldi.creditcardservice.entity.request.CreditCardSaveRequest;
import unaldi.creditcardservice.entity.request.CreditCardUpdateRequest;
import unaldi.creditcardservice.repository.CreditCardRepository;
import unaldi.creditcardservice.service.abstracts.CreditCardService;
import unaldi.creditcardservice.service.abstracts.mapper.CreditCardMapper;
import unaldi.creditcardservice.utils.client.dto.BankResponse;
import unaldi.creditcardservice.utils.client.dto.RestResponse;
import unaldi.creditcardservice.utils.client.dto.UserResponse;
import unaldi.creditcardservice.utils.constant.ExceptionMessages;
import unaldi.creditcardservice.utils.constant.Messages;
import unaldi.creditcardservice.utils.exception.customExceptions.CreditCardNotFoundException;
import unaldi.creditcardservice.utils.result.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Service
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final UserServiceClient userServiceClient;
    private final BankServiceClient bankServiceClient;
    private final LogProducer logProducer;

    @Autowired
    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, UserServiceClient userServiceClient, BankServiceClient bankServiceClient, LogProducer logProducer)
    {
        this.creditCardRepository = creditCardRepository;
        this.userServiceClient = userServiceClient;
        this.bankServiceClient = bankServiceClient;
        this.logProducer = logProducer;
    }

    @CacheEvict(value = Caches.CREDIT_CARDS_CACHE, allEntries = true, condition = "#result.success != false")
    @Override
    public DataResult<CreditCardDTO> save(CreditCardSaveRequest creditCardSaveRequest) {
        userServiceClient.findById(creditCardSaveRequest.userId());
        bankServiceClient.findById(creditCardSaveRequest.bankId());

        CreditCard creditCard = CreditCardMapper.INSTANCE.convertToSaveCreditCard(creditCardSaveRequest);
        this.creditCardRepository.save(creditCard);

        logProducer.sendToLog(prepareLogRequest(OperationType.POST,Messages.CREDIT_CARD_CREATED));

        return new SuccessDataResult<>(
                CreditCardMapper.INSTANCE.convertToCreditCardDTO(creditCard),
                Messages.CREDIT_CARD_CREATED
        );
    }

    @CachePut(value = Caches.CREDIT_CARD_CACHE, key = "#creditCardUpdateRequest.id()", unless = "#result.success != true")
    @CacheEvict(value = Caches.CREDIT_CARDS_CACHE, allEntries = true, condition = "#result.success != false")
    @Override
    public DataResult<CreditCardDTO> update(CreditCardUpdateRequest creditCardUpdateRequest) {
        if(!this.creditCardRepository.existsById(creditCardUpdateRequest.id()))
            throw new CreditCardNotFoundException(ExceptionMessages.CREDIT_CARD_NOT_FOUND);

        userServiceClient.findById(creditCardUpdateRequest.userId());
        bankServiceClient.findById(creditCardUpdateRequest.bankId());

        CreditCard creditCard = CreditCardMapper.INSTANCE.convertToUpdateCreditCard(creditCardUpdateRequest);
        this.creditCardRepository.save(creditCard);

        logProducer.sendToLog(prepareLogRequest(OperationType.PUT,Messages.CREDIT_CARD_UPDATED));

        return new SuccessDataResult<>(
                CreditCardMapper.INSTANCE.convertToCreditCardDTO(creditCard),
                Messages.CREDIT_CARD_UPDATED
        );
    }

    @Caching(
            evict = {
                    @CacheEvict(value = Caches.CREDIT_CARDS_CACHE, allEntries = true, condition = "#result.success != false"),
                    @CacheEvict(value = Caches.CREDIT_CARD_CACHE, key = "#creditCardId", condition = "#result.success != false")
            }
    )
    @Override
    public Result deleteById(Long creditCardId) {
        CreditCard creditCard = this.creditCardRepository
                .findById(creditCardId)
                .orElseThrow(() -> new CreditCardNotFoundException(ExceptionMessages.CREDIT_CARD_NOT_FOUND));

        this.creditCardRepository.deleteById(creditCard.getId());

        logProducer.sendToLog(prepareLogRequest(OperationType.DELETE,Messages.CREDIT_CARD_DELETED));

        return new SuccessResult(Messages.CREDIT_CARD_DELETED);
    }

    @Cacheable(value = Caches.CREDIT_CARD_CACHE, key = "#creditCardId", unless = "#result.success != true")
    @Override
    public DataResult<CreditCardDTO> findById(Long creditCardId) {
        CreditCardDTO creditCardDTO = this.creditCardRepository
                .findById(creditCardId)
                .map(CreditCardMapper.INSTANCE::convertToCreditCardDTO)
                .orElseThrow(() -> new CreditCardNotFoundException(ExceptionMessages.CREDIT_CARD_NOT_FOUND));

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.CREDIT_CARD_BANK_FOUND));

        return new SuccessDataResult<>(creditCardDTO, Messages.CREDIT_CARD_FOUND);
    }

    @Cacheable(value = Caches.CREDIT_CARDS_CACHE, key = "'all'", unless = "#result.success != true")
    @Override
    public DataResult<List<CreditCardDTO>> findAll() {
        List<CreditCard> creditCardList = this.creditCardRepository.findAll();

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.CREDIT_CARDS_LISTED));

        return new SuccessDataResult<>(
                CreditCardMapper.INSTANCE.convertCreditCardDTOs(creditCardList),
                Messages.CREDIT_CARDS_LISTED
        );
    }

    @Cacheable(value = Caches.CREDIT_CARD_USER_CACHE, key = "#userId", unless = "#result.success != true")
    @Override
    public DataResult<UserResponse> findCreditCardUserByUserId(Long userId) {
        ResponseEntity<RestResponse<UserResponse>> response = userServiceClient.findById(userId);

        UserResponse userResponse = Objects.requireNonNull(response.getBody()).getData();

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.CREDIT_CARD_USER_FOUND));

        return new SuccessDataResult<>(
                userResponse,
                Messages.CREDIT_CARD_USER_FOUND
        );
    }

    @Cacheable(value = Caches.CREDIT_CARD_BANK_CACHE, key = "#bankId", unless = "#result.success != true")
    @Override
    public DataResult<BankResponse> findCreditCardBankByBankId(Long bankId) {
        ResponseEntity<RestResponse<BankResponse>> response = bankServiceClient.findById(bankId);

        BankResponse bankResponse = Objects.requireNonNull(response.getBody()).getData();

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.CREDIT_CARD_BANK_FOUND));

        return new SuccessDataResult<>(
                bankResponse,
                Messages.CREDIT_CARD_BANK_FOUND
        );
    }

    private LogRequest prepareLogRequest(
            OperationType operationType,
            String message
    )
    {
        return LogRequest
                .builder()
                .serviceName("credit-card-service")
                .operationType(operationType)
                .logType(LogType.INFO)
                .message(message)
                .timestamp(LocalDateTime.now())
                .exception(null)
                .build();
    }
}
