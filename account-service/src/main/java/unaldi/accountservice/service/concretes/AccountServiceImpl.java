package unaldi.accountservice.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unaldi.accountservice.entity.Account;
import unaldi.accountservice.entity.dto.AccountDTO;
import unaldi.accountservice.entity.request.AccountSaveRequest;
import unaldi.accountservice.entity.request.AccountUpdateRequest;
import unaldi.accountservice.repository.AccountRepository;
import unaldi.accountservice.service.abstracts.AccountService;
import unaldi.accountservice.service.abstracts.mapper.AccountMapper;
import unaldi.accountservice.utils.constant.ExceptionMessages;
import unaldi.accountservice.utils.constant.Messages;
import unaldi.accountservice.utils.exception.customExceptions.AccountNotFoundException;
import unaldi.accountservice.utils.result.DataResult;
import unaldi.accountservice.utils.result.Result;
import unaldi.accountservice.utils.result.SuccessDataResult;
import unaldi.accountservice.utils.result.SuccessResult;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public DataResult<AccountDTO> save(AccountSaveRequest accountSaveRequest) {
        Account account = AccountMapper.INSTANCE.convertToSaveAccount(accountSaveRequest);
        this.accountRepository.save(account);

        return new SuccessDataResult<>(
                AccountMapper.INSTANCE.convertToAccountDTO(account),
                Messages.ACCOUNT_CREATED
        );
    }

    @Override
    public DataResult<AccountDTO> update(AccountUpdateRequest accountUpdateRequest) {
        if(!this.accountRepository.existsById(accountUpdateRequest.id()))
            throw new AccountNotFoundException(ExceptionMessages.ACCOUNT_NOT_FOUND);

        Account account = AccountMapper.INSTANCE.convertToUpdateAccount(accountUpdateRequest);
        this.accountRepository.save(account);

        return new SuccessDataResult<>(
                AccountMapper.INSTANCE.convertToAccountDTO(account),
                Messages.ACCOUNT_UPDATED
        );
    }

    @Override
    public Result deleteById(Long accountId) {
        Account account = this.accountRepository
                .findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACCOUNT_NOT_FOUND));

        this.accountRepository.deleteById(account.getId());

        return new SuccessResult(Messages.ACCOUNT_DELETED);
    }

    @Override
    public DataResult<AccountDTO> findById(Long accountId) {
        AccountDTO accountDTO = this.accountRepository
                .findById(accountId)
                .map(AccountMapper.INSTANCE::convertToAccountDTO)
                .orElseThrow(() -> new AccountNotFoundException(ExceptionMessages.ACCOUNT_NOT_FOUND));

        return new SuccessDataResult<>(
                accountDTO,
                Messages.ACCOUNTS_LISTED
        );
    }

    @Override
    public DataResult<List<AccountDTO>> findAll() {
        List<Account> accountList = this.accountRepository.findAll();

        return new SuccessDataResult<>(
                AccountMapper.INSTANCE.convertAccountDTOs(accountList),
                Messages.ACCOUNTS_LISTED
        );
    }
}
