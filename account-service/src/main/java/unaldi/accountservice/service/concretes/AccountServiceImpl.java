package unaldi.accountservice.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unaldi.accountservice.entity.Account;
import unaldi.accountservice.entity.dto.AccountDTO;
import unaldi.accountservice.entity.request.AccountSaveRequest;
import unaldi.accountservice.entity.request.AccountUpdateRequest;
import unaldi.accountservice.repository.AccountRepository;
import unaldi.accountservice.service.abstracts.AccountService;
import unaldi.accountservice.service.abstracts.mapper.AccountMapper;
import unaldi.accountservice.utils.constant.Caches;
import unaldi.accountservice.utils.rabbitMQ.enums.LogType;
import unaldi.accountservice.utils.rabbitMQ.enums.OperationType;
import unaldi.accountservice.utils.rabbitMQ.producer.LogProducer;
import unaldi.accountservice.utils.rabbitMQ.request.LogRequest;
import unaldi.accountservice.utils.client.BankServiceClient;
import unaldi.accountservice.utils.client.UserServiceClient;
import unaldi.accountservice.utils.client.dto.BankResponse;
import unaldi.accountservice.utils.client.dto.RestResponse;
import unaldi.accountservice.utils.client.dto.UserResponse;
import unaldi.accountservice.utils.constant.ExceptionMessages;
import unaldi.accountservice.utils.constant.Messages;
import unaldi.accountservice.utils.exception.customExceptions.AccountNotFoundException;
import unaldi.accountservice.utils.result.DataResult;
import unaldi.accountservice.utils.result.Result;
import unaldi.accountservice.utils.result.SuccessDataResult;
import unaldi.accountservice.utils.result.SuccessResult;

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
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserServiceClient userServiceClient;
    private final BankServiceClient bankServiceClient;
    private final LogProducer logProducer;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, UserServiceClient userServiceClient, BankServiceClient bankServiceClient, LogProducer logProducer) {
        this.accountRepository = accountRepository;
        this.userServiceClient = userServiceClient;
        this.bankServiceClient = bankServiceClient;
        this.logProducer = logProducer;
    }

    @CacheEvict(value = Caches.ACCOUNTS_CACHE, allEntries = true, condition = "#result.success != false")
    @Override
    public DataResult<AccountDTO> save(AccountSaveRequest accountSaveRequest) {
        userServiceClient.findById(accountSaveRequest.userId());
        bankServiceClient.findById(accountSaveRequest.bankId());

        Account account = AccountMapper.INSTANCE.convertToSaveAccount(accountSaveRequest);
        this.accountRepository.save(account);

        logProducer.sendToLog(prepareLogRequest(OperationType.POST,Messages.ACCOUNT_CREATED));

        return new SuccessDataResult<>(
                AccountMapper.INSTANCE.convertToAccountDTO(account),
                Messages.ACCOUNT_CREATED
        );
    }

    @CachePut(value = Caches.ACCOUNT_CACHE, key = "#accountUpdateRequest.id()", unless = "#result.success != true")
    @CacheEvict(value = Caches.ACCOUNTS_CACHE, allEntries = true, condition = "#result.success != false")
    @Override
    public DataResult<AccountDTO> update(AccountUpdateRequest accountUpdateRequest) {
        if(!this.accountRepository.existsById(accountUpdateRequest.id()))
            throw new AccountNotFoundException(ExceptionMessages.ACCOUNT_NOT_FOUND);

        userServiceClient.findById(accountUpdateRequest.userId());
        bankServiceClient.findById(accountUpdateRequest.bankId());

        Account account = AccountMapper.INSTANCE.convertToUpdateAccount(accountUpdateRequest);
        this.accountRepository.save(account);

        logProducer.sendToLog(prepareLogRequest(OperationType.PUT,Messages.ACCOUNT_UPDATED));

        return new SuccessDataResult<>(
                AccountMapper.INSTANCE.convertToAccountDTO(account),
                Messages.ACCOUNT_UPDATED
        );
    }

    @Caching(
            evict = {
                    @CacheEvict(value = Caches.ACCOUNTS_CACHE, allEntries = true, condition = "#result.success != false"),
                    @CacheEvict(value = Caches.ACCOUNT_CACHE, key = "#accountId", condition = "#result.success != false")
            }
    )
    @Override
    public Result deleteById(Long accountId) {
        Account account = this.accountRepository
                .findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACCOUNT_NOT_FOUND));

        this.accountRepository.deleteById(account.getId());

        logProducer.sendToLog(prepareLogRequest(OperationType.DELETE,Messages.ACCOUNT_DELETED));

        return new SuccessResult(Messages.ACCOUNT_DELETED);
    }

    @Cacheable(value = Caches.ACCOUNT_CACHE, key = "#accountId", unless = "#result.success != true")
    @Override
    public DataResult<AccountDTO> findById(Long accountId) {
        AccountDTO accountDTO = this.accountRepository
                .findById(accountId)
                .map(AccountMapper.INSTANCE::convertToAccountDTO)
                .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACCOUNT_NOT_FOUND));

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.ACCOUNT_FOUND));

        return new SuccessDataResult<>(
                accountDTO,
                Messages.ACCOUNT_FOUND
        );
    }

    @Cacheable(value = Caches.ACCOUNTS_CACHE, key = "'all'", unless = "#result.success != true")
    @Override
    public DataResult<List<AccountDTO>> findAll() {
        List<Account> accountList = this.accountRepository.findAll();

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.ACCOUNTS_LISTED));

        return new SuccessDataResult<>(
                AccountMapper.INSTANCE.convertAccountDTOs(accountList),
                Messages.ACCOUNTS_LISTED
        );
    }

    @Cacheable(value = Caches.ACCOUNT_USER_CACHE, key = "#userId", unless = "#result.success != true")
    @Override
    public DataResult<UserResponse> findAccountUserByUserId(Long userId) {
        ResponseEntity<RestResponse<UserResponse>> response = userServiceClient.findById(userId);

        UserResponse userResponse = Objects.requireNonNull(response.getBody()).getData();

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.ACCOUNT_USER_FOUND));

        return new SuccessDataResult<>(
                userResponse,
                Messages.ACCOUNT_USER_FOUND
        );
    }

    @Cacheable(value = Caches.ACCOUNT_BANK_CACHE, key = "#bankId", unless = "#result.success != true")
    @Override
    public DataResult<BankResponse> findAccountBankByBankId(Long bankId) {
        ResponseEntity<RestResponse<BankResponse>> response = bankServiceClient.findById(bankId);

        BankResponse bankResponse = Objects.requireNonNull(response.getBody()).getData();

        logProducer.sendToLog(prepareLogRequest(OperationType.GET,Messages.ACCOUNT_BANK_FOUND));

        return new SuccessDataResult<>(
                bankResponse,
                Messages.ACCOUNT_BANK_FOUND
        );
    }

    private LogRequest prepareLogRequest(
            OperationType operationType,
            String message
    )
    {
        return LogRequest
                .builder()
                .serviceName("account-service")
                .operationType(operationType)
                .logType(LogType.INFO)
                .message(message)
                .timestamp(LocalDateTime.now())
                .exception(null)
                .build();
    }
}
