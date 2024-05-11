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
            account = Account.builder()
                    .id(1L)
                    .accountNumber("239488942374895439")
                    .userId(1L)
                    .balance(2356.80)
                    .accountType(AccountType.DEPOSIT)
                    .accountStatus(AccountStatus.ACTIVE)
                    .bankId(1L)
                    .build();
        }

        return account;
    }

    public AccountUpdateRequest getAccountUpdateRequest() {
        if (accountUpdateRequest == null) {
            accountUpdateRequest = AccountUpdateRequest.builder()
                    .id(1L)
                    .accountNumber("239488942374895439")
                    .userId(1L)
                    .balance(2356.80)
                    .accountType(AccountType.DEPOSIT)
                    .accountStatus(AccountStatus.ACTIVE)
                    .bankId(1L)
                    .build();
        }

        return accountUpdateRequest;
    }

    public AccountSaveRequest getAccountSaveRequest() {
        if (accountSaveRequest == null) {
            accountSaveRequest = AccountSaveRequest.builder()
                    .accountNumber("239488942374895439")
                    .userId(1L)
                    .balance(2356.80)
                    .accountType(AccountType.DEPOSIT)
                    .accountStatus(AccountStatus.ACTIVE)
                    .bankId(1L)
                    .build();
        }

        return accountSaveRequest;
    }

    public List<Account> getAccountList() {
        if (accountList == null) {
            Account accountOne = Account.builder()
                    .id(1L)
                    .accountNumber("239488942374895439")
                    .userId(1L)
                    .balance(2356.80)
                    .accountType(AccountType.DEPOSIT)
                    .accountStatus(AccountStatus.ACTIVE)
                    .bankId(1L)
                    .build();

            Account accountTwo = Account.builder()
                    .id(2L)
                    .accountNumber("239488942374895439")
                    .userId(1L)
                    .balance(2356.80)
                    .accountType(AccountType.DEPOSIT)
                    .accountStatus(AccountStatus.ACTIVE)
                    .bankId(1L)
                    .build();

            accountList = List.of(
                    accountOne,
                    accountTwo
            );
        }

        return accountList;
    }

    public UserResponse getUserResponse() {
        if (userResponse == null) {
            userResponse = UserResponse.builder()
                    .id(1L)
                    .username("eunaldi")
                    .password("121221")
                    .email("emree.unaldi@gmail.com")
                    .firstName("Emre")
                    .lastName("Ünaldı")
                    .phoneNumber("05078711189")
                    .birthDate(LocalDate.of(2001, 1, 1))
                    .gender(Gender.MALE)
                    .build();
        }

        return userResponse;
    }

    public BankResponse getBankResponse() {
        if (bankResponse == null) {
            bankResponse = BankResponse.builder()
                    .id(1L)
                    .bankName("Example Bank")
                    .bankCode("EXBK")
                    .branchName("Main Branch")
                    .branchCode("MB")
                    .accountNumber("1234567812345678")
                    .address("KAYSERI / Incesu")
                    .email("info@examplebank.com")
                    .phoneNumber("0507 871 11 89")
                    .build();
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