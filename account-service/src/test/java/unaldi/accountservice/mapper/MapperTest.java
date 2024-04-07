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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@SpringBootTest
public class MapperTest {
    @Test
    void should_convertSaveRequestToAccount() {
        AccountSaveRequest accountSaveRequest = new AccountSaveRequest(
                "239488942374895439",
                1L,
                2356.80,
                AccountType.DEPOSIT,
                AccountStatus.ACTIVE,
                2L
        );

        Account account = AccountMapper.INSTANCE.convertToSaveAccount(accountSaveRequest);

        assertEquals(accountSaveRequest.accountNumber(), account.getAccountNumber());
        assertEquals(accountSaveRequest.userId(), account.getUserId());
        assertEquals(accountSaveRequest.balance(), account.getBalance());
        assertEquals(accountSaveRequest.accountType(), account.getAccountType());
        assertEquals(accountSaveRequest.accountStatus(), account.getAccountStatus());
        assertEquals(accountSaveRequest.bankId(), account.getBankId());
    }

    @Test
    void should_convertUpdateRequestToAccount() {
        AccountUpdateRequest accountUpdateRequest = new AccountUpdateRequest(
                1L,
                "239488942374895439",
                1L,
                2356.80,
                AccountType.DEPOSIT,
                AccountStatus.ACTIVE,
                2L
        );

        Account account = AccountMapper.INSTANCE.convertToUpdateAccount(accountUpdateRequest);

        assertEquals(accountUpdateRequest.id(), account.getId());
        assertEquals(accountUpdateRequest.accountNumber(), account.getAccountNumber());
        assertEquals(accountUpdateRequest.userId(), account.getUserId());
        assertEquals(accountUpdateRequest.balance(), account.getBalance());
        assertEquals(accountUpdateRequest.accountType(), account.getAccountType());
        assertEquals(accountUpdateRequest.accountStatus(), account.getAccountStatus());
        assertEquals(accountUpdateRequest.bankId(), account.getBankId());
    }

    @Test
    void should_convertAccountToAccountDTO() {
        Account account = new Account(
                1L,
                "239488942374895439",
                1L,
                2356.80,
                AccountType.DEPOSIT,
                AccountStatus.ACTIVE,
                2L
        );

        AccountDTO accountDTO = AccountMapper.INSTANCE.convertToAccountDTO(account);

        assertEquals(accountDTO.id(), account.getId());
        assertEquals(accountDTO.accountNumber(), account.getAccountNumber());
        assertEquals(accountDTO.userId(), account.getUserId());
        assertEquals(accountDTO.balance(), account.getBalance());
        assertEquals(accountDTO.accountType(), account.getAccountType());
        assertEquals(accountDTO.accountStatus(), account.getAccountStatus());
        assertEquals(accountDTO.bankId(), account.getBankId());
    }

    @Test
    void should_convertListOfAccountsToListOfAccountDTOs() {
        List<Account> accountList = new ArrayList<>(Arrays.asList(
                new Account(
                        2L,
                        "239488942374895439",
                        2L,
                        2356.80,
                        AccountType.DEPOSIT,
                        AccountStatus.ACTIVE,
                        3L
                ),
                new Account(
                        1L,
                        "239488942374895439",
                        1L,
                        2356.80,
                        AccountType.DEPOSIT,
                        AccountStatus.ACTIVE,
                        2L
                )
        ));

        List<AccountDTO> accountDTOList = AccountMapper.INSTANCE.convertAccountDTOs(accountList);

        assertEquals(accountList.get(0).getId(), accountDTOList.get(0).id());
        assertEquals(accountList.get(1).getId(), accountDTOList.get(1).id());
    }
}
