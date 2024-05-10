package unaldi.accountservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import unaldi.accountservice.entity.Account;
import unaldi.accountservice.entity.dto.AccountDTO;
import unaldi.accountservice.entity.request.AccountSaveRequest;
import unaldi.accountservice.entity.request.AccountUpdateRequest;
import unaldi.accountservice.repository.AccountRepository;
import unaldi.accountservice.service.concretes.AccountServiceImpl;
import unaldi.accountservice.utils.FailTestMessages;
import unaldi.accountservice.utils.ObjectFactory;
import unaldi.accountservice.utils.client.BankServiceClient;
import unaldi.accountservice.utils.client.UserServiceClient;
import unaldi.accountservice.utils.client.dto.BankResponse;
import unaldi.accountservice.utils.client.dto.RestResponse;
import unaldi.accountservice.utils.client.dto.UserResponse;
import unaldi.accountservice.utils.exception.customExceptions.AccountNotFoundException;
import unaldi.accountservice.utils.rabbitMQ.producer.LogProducer;
import unaldi.accountservice.utils.rabbitMQ.request.LogRequest;
import unaldi.accountservice.utils.result.DataResult;
import unaldi.accountservice.utils.result.Result;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;

    @Mock
    private LogProducer logProducer;

    @Mock
    private UserServiceClient userServiceClient;

    @Mock
    private BankServiceClient bankServiceClient;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void givenAccountSaveRequest_whenSave_thenAccountShouldBeSaved() {
        AccountSaveRequest accountSaveRequest = ObjectFactory.getInstance().getAccountSaveRequest();
        Account account = ObjectFactory.getInstance().getAccount();

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        DataResult<AccountDTO> result = accountService.save(accountSaveRequest);

        assertTrue(result.getSuccess(), FailTestMessages.ACCOUNT_SAVE);

        verify(accountRepository, times(1)).save(any(Account.class));
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenAccountUpdateRequest_whenUpdate_thenAccountShouldBeUpdated() {
        AccountUpdateRequest accountUpdateRequest = ObjectFactory.getInstance().getAccountUpdateRequest();
        Account account = ObjectFactory.getInstance().getAccount();

        when(accountRepository.existsById(accountUpdateRequest.id())).thenReturn(true);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        DataResult<AccountDTO> result = accountService.update(accountUpdateRequest);

        assertTrue(result.getSuccess(), FailTestMessages.ACCOUNT_UPDATE);

        verify(accountRepository, times(1)).existsById(anyLong());
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenAccountId_whenDeleteById_thenAccountShouldBeDeleted() {
        Account account = ObjectFactory.getInstance().getAccount();

        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        Result result = accountService.deleteById(account.getId());

        assertTrue(result.getSuccess(), FailTestMessages.ACCOUNT_DELETE);

        verify(accountRepository, times(1)).deleteById(account.getId());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenAccountId_whenFindById_thenAccountShouldBeFound() {
        Account account = ObjectFactory.getInstance().getAccount();

        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

        DataResult<AccountDTO> result = accountService.findById(account.getId());

        assertTrue(result.getSuccess(), FailTestMessages.ACCOUNT_FIND);

        verify(accountRepository, times(1)).findById(account.getId());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenUserResponse_whenFindAccountUserByUserId_thenAccountUserShouldBeFound() {
        UserResponse userResponse = ObjectFactory.getInstance().getUserResponse();
        RestResponse<UserResponse> userRestResponse = ObjectFactory.getInstance().getRestResponse(userResponse);
        ResponseEntity<RestResponse<UserResponse>> response = ResponseEntity.ok(userRestResponse);

        when(userServiceClient.findById(userResponse.id())).thenReturn(response);

        DataResult<UserResponse> result = accountService.findAccountUserByUserId(userResponse.id());

        assertTrue(result.getSuccess(), FailTestMessages.ACCOUNT_USER_FIND);

        verify(userServiceClient, times(1)).findById(userResponse.id());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenBankResponse_whenFindAccountBankByBankId_thenAccountBankShouldBeFound() {
        BankResponse bankResponse = ObjectFactory.getInstance().getBankResponse();
        RestResponse<BankResponse> bankRestResponse = ObjectFactory.getInstance().getRestResponse(bankResponse);
        ResponseEntity<RestResponse<BankResponse>> response = ResponseEntity.ok(bankRestResponse);

        when(bankServiceClient.findById(bankResponse.id())).thenReturn(response);

        DataResult<BankResponse> result = accountService.findAccountBankByBankId(bankResponse.id());

        assertTrue(result.getSuccess(), FailTestMessages.ACCOUNT_BANK_FIND);

        verify(bankServiceClient, times(1)).findById(bankResponse.id());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenAccountList_whenFindAll_thenAllAccountsShouldBeReturned() {
        List<Account> accountList = ObjectFactory.getInstance().getAccountList();

        when(accountRepository.findAll()).thenReturn(accountList);

        DataResult<List<AccountDTO>> result = accountService.findAll();

        assertTrue(result.getSuccess(), FailTestMessages.ACCOUNTS_FIND);

        verify(accountRepository, times(1)).findAll();
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentAccountUpdateRequest_whenUpdate_thenAccountNotFoundExceptionShouldBeThrown() {
        AccountUpdateRequest accountUpdateRequest = ObjectFactory.getInstance().getAccountUpdateRequest();

        when(accountRepository.existsById(accountUpdateRequest.id())).thenReturn(false);

        assertThrows(AccountNotFoundException.class, () -> {
            accountService.update(accountUpdateRequest);
        }, FailTestMessages.ACCOUNT_UPDATE_EXCEPTION);

        verify(accountRepository, times(1)).existsById(anyLong());
        verify(accountRepository, never()).save(any(Account.class));
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentAccountId_whenDeleteById_thenAccountNotFoundExceptionShouldBeThrown() {
        Long nonExistentAccountId = -1L;

        when(accountRepository.findById(nonExistentAccountId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> {
            accountService.deleteById(nonExistentAccountId);
        }, FailTestMessages.ACCOUNT_DELETE_EXCEPTION);

        verify(accountRepository, times(1)).findById(nonExistentAccountId);
        verify(accountRepository, never()).deleteById(anyLong());
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentAccountId_whenFindById_thenAccountNotFoundExceptionShouldBeThrown() {
        Long nonExistentAccountId = -1L;

        when(accountRepository.findById(nonExistentAccountId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> {
            accountService.findById(nonExistentAccountId);
        }, FailTestMessages.ACCOUNT_FIND_EXCEPTION);

        verify(accountRepository, times(1)).findById(nonExistentAccountId);
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }
}
