package unaldi.accountservice.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import unaldi.accountservice.entity.Account;
import unaldi.accountservice.entity.dto.AccountDTO;
import unaldi.accountservice.entity.request.AccountSaveRequest;
import unaldi.accountservice.entity.request.AccountUpdateRequest;
import unaldi.accountservice.service.abstracts.mapper.AccountMapper;
import unaldi.accountservice.utils.FailTestMessages;
import unaldi.accountservice.utils.ObjectFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
class AccountMapperTest {
    private static Account account;
    private static List<Account> accountList;
    private static AccountSaveRequest accountSaveRequest;
    private static AccountUpdateRequest accountUpdateRequest;

    @BeforeAll
    static void setUp() {
        account = ObjectFactory.getInstance().getAccount();
        accountList = ObjectFactory.getInstance().getAccountList();
        accountSaveRequest = ObjectFactory.getInstance().getAccountSaveRequest();
        accountUpdateRequest = ObjectFactory.getInstance().getAccountUpdateRequest();
    }

    @Test
    void should_convertSaveRequestToAccount() {
        Account account = AccountMapper.INSTANCE.convertToSaveAccount(accountSaveRequest);

        assertAll(
                () -> assertEquals(accountSaveRequest.accountNumber(), account.getAccountNumber(), FailTestMessages.ACCOUNT_NUMBER_MISMATCH),
                () -> assertEquals(accountSaveRequest.userId(), account.getUserId(), FailTestMessages.USER_ID_MISMATCH),
                () -> assertEquals(accountSaveRequest.balance(), account.getBalance(), FailTestMessages.BALANCE_MISMATCH),
                () -> assertEquals(accountSaveRequest.accountType(), account.getAccountType(), FailTestMessages.ACCOUNT_TYPE_MISMATCH),
                () -> assertEquals(accountSaveRequest.accountStatus(), account.getAccountStatus(), FailTestMessages.ACCOUNT_STATUS_MISMATCH),
                () -> assertEquals(accountSaveRequest.bankId(), account.getBankId(), FailTestMessages.BANK_ID_MISMATCH)
        );
    }

    @Test
    void should_convertUpdateRequestToAccount() {
        Account account = AccountMapper.INSTANCE.convertToUpdateAccount(accountUpdateRequest);

        assertAll(
                () -> assertEquals(accountUpdateRequest.accountNumber(), account.getAccountNumber(), FailTestMessages.ACCOUNT_NUMBER_MISMATCH),
                () -> assertEquals(accountUpdateRequest.userId(), account.getUserId(), FailTestMessages.USER_ID_MISMATCH),
                () -> assertEquals(accountUpdateRequest.balance(), account.getBalance(), FailTestMessages.BALANCE_MISMATCH),
                () -> assertEquals(accountUpdateRequest.accountType(), account.getAccountType(), FailTestMessages.ACCOUNT_TYPE_MISMATCH),
                () -> assertEquals(accountUpdateRequest.accountStatus(), account.getAccountStatus(), FailTestMessages.ACCOUNT_STATUS_MISMATCH),
                () -> assertEquals(accountUpdateRequest.bankId(), account.getBankId(), FailTestMessages.BANK_ID_MISMATCH)
        );
    }

    @Test
    void should_convertAccountToAccountDTO() {
        AccountDTO accountDTO = AccountMapper.INSTANCE.convertToAccountDTO(account);

        assertAll(
                () -> assertEquals(accountDTO.accountNumber(), account.getAccountNumber(), FailTestMessages.ACCOUNT_NUMBER_MISMATCH),
                () -> assertEquals(accountDTO.userId(), account.getUserId(), FailTestMessages.USER_ID_MISMATCH),
                () -> assertEquals(accountDTO.balance(), account.getBalance(), FailTestMessages.BALANCE_MISMATCH),
                () -> assertEquals(accountDTO.accountType(), account.getAccountType(), FailTestMessages.ACCOUNT_TYPE_MISMATCH),
                () -> assertEquals(accountDTO.accountStatus(), account.getAccountStatus(), FailTestMessages.ACCOUNT_STATUS_MISMATCH),
                () -> assertEquals(accountDTO.bankId(), account.getBankId(), FailTestMessages.BANK_ID_MISMATCH)
        );
    }

    @Test
    void should_convertListOfAccountsToListOfAccountDTOs() {
        List<AccountDTO> accountDTOList = AccountMapper.INSTANCE.convertAccountDTOs(accountList);

        assertEquals(accountList.get(0).getId(), accountDTOList.get(0).id());
        assertEquals(accountList.get(1).getId(), accountDTOList.get(1).id());
    }
}
