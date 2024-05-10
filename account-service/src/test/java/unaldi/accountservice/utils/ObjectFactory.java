package unaldi.accountservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import unaldi.accountservice.entity.Account;
import unaldi.accountservice.entity.enums.AccountStatus;
import unaldi.accountservice.entity.enums.AccountType;
import unaldi.accountservice.entity.request.AccountSaveRequest;
import unaldi.accountservice.entity.request.AccountUpdateRequest;
import unaldi.accountservice.utils.client.dto.BankResponse;
import unaldi.accountservice.utils.client.dto.RestResponse;
import unaldi.accountservice.utils.client.dto.UserResponse;
import unaldi.accountservice.utils.client.enums.Gender;

import java.time.LocalDate;
import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectFactory {
    private static ObjectFactory instance;
    private Account account;
    private AccountUpdateRequest accountUpdateRequest;
    private AccountSaveRequest accountSaveRequest;
    private List<Account> accountList;
    private UserResponse userResponse;
    private BankResponse bankResponse;

    public static synchronized ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }

        return instance;
    }

    public Account getAccount() {
        if (account == null) {
            account = new Account(
                    1L,
                    "239488942374895439",
                    1L,
                    2356.80,
                    AccountType.DEPOSIT,
                    AccountStatus.ACTIVE,
                    2L
            );
        }

        return account;
    }

    public AccountUpdateRequest getAccountUpdateRequest() {
        if (accountUpdateRequest == null) {
            accountUpdateRequest = new AccountUpdateRequest(
                    1L,
                    "239488942374895439",
                    1L,
                    2356.80,
                    AccountType.DEPOSIT,
                    AccountStatus.ACTIVE,
                    2L
            );
        }

        return accountUpdateRequest;
    }

    public AccountSaveRequest getAccountSaveRequest() {
        if (accountSaveRequest == null) {
            accountSaveRequest = new AccountSaveRequest(
                    "239488942374895439",
                    1L,
                    2356.80,
                    AccountType.DEPOSIT,
                    AccountStatus.ACTIVE,
                    2L
            );
        }

        return accountSaveRequest;
    }

    public List<Account> getAccountList() {
        if (accountList == null) {
            accountList = List.of(
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
            );
        }

        return accountList;
    }

    public UserResponse getUserResponse() {
        if (userResponse == null) {
            userResponse = new UserResponse(
                    1L,
                    "eunaldi",
                    "121221",
                    "emree.unaldi@gmail.com",
                    "Emre",
                    "Ünaldı",
                    "05078711189",
                    LocalDate.of(2001, 1, 1),
                    Gender.MALE
            );
        }

        return userResponse;
    }

    public BankResponse getBankResponse() {
        if (bankResponse == null) {
            bankResponse = new BankResponse(
                    1L,
                    "Example Bank",
                    "EXBK",
                    "Main Branch",
                    "MB",
                    "1234567812345678",
                    "KAYSERI / Incesu",
                    "info@examplebank.com",
                    "0507 871 11 89"
            );
        }

        return bankResponse;
    }

    public <T> RestResponse<T> getRestResponse(T entityResponse) {
        return RestResponse.<T>builder()
                .data(entityResponse)
                .success(true)
                .build();
    }
}