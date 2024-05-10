package unaldi.accountservice.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import unaldi.accountservice.entity.Account;
import unaldi.accountservice.entity.dto.AccountDTO;
import unaldi.accountservice.entity.enums.AccountStatus;
import unaldi.accountservice.entity.enums.AccountType;
import unaldi.accountservice.entity.request.AccountSaveRequest;
import unaldi.accountservice.entity.request.AccountUpdateRequest;
import unaldi.accountservice.service.abstracts.mapper.AccountMapper;
import unaldi.accountservice.utils.FailTestMessages;
import unaldi.accountservice.utils.ObjectFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@SpringBootTest
class MapperTest {
    @Test
    void should_convertSaveRequestToAccount() {
        AccountSaveRequest accountSaveRequest = ObjectFactory.getInstance().getAccountSaveRequest();
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
        AccountUpdateRequest accountUpdateRequest = ObjectFactory.getInstance().getAccountUpdateRequest();
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
        Account account = ObjectFactory.getInstance().getAccount();
        AccountDTO accountDTO = AccountMapper.INSTANCE.convertToAccountDTO(account);

        assertAll(
                () -> assertEquals(accountDTO.id(), account.getId()),
                () -> assertEquals(accountDTO.accountNumber(), account.getAccountNumber()),
                () -> assertEquals(accountDTO.userId(), account.getUserId()),
                () -> assertEquals(accountDTO.balance(), account.getBalance()),
                () -> assertEquals(accountDTO.accountType(), account.getAccountType()),
                () -> assertEquals(accountDTO.accountStatus(), account.getAccountStatus()),
                () -> assertEquals(accountDTO.bankId(), account.getBankId())
        );
    }

    @Test
    void should_convertListOfAccountsToListOfAccountDTOs() {
        List<Account> accountList = ObjectFactory.getInstance().getAccountList();
        List<AccountDTO> accountDTOList = AccountMapper.INSTANCE.convertAccountDTOs(accountList);

        assertEquals(accountList.get(0).getId(), accountDTOList.get(0).id());
        assertEquals(accountList.get(1).getId(), accountDTOList.get(1).id());
    }
}
