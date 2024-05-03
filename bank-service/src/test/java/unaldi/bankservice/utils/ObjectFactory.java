package unaldi.bankservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import unaldi.bankservice.entity.Bank;
import unaldi.bankservice.entity.request.BankSaveRequest;
import unaldi.bankservice.entity.request.BankUpdateRequest;

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
    private Bank bank;
    private BankUpdateRequest bankUpdateRequest;
    private BankSaveRequest bankSaveRequest;
    private List<Bank> bankList;

    public static synchronized ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }

        return instance;
    }

    public Bank getBank() {
        if (bank == null) {
            bank = new Bank(
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

        return bank;
    }

    public BankUpdateRequest getBankUpdateRequest() {
        if (bankUpdateRequest == null) {
            bankUpdateRequest = new BankUpdateRequest(
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

        return bankUpdateRequest;
    }

    public BankSaveRequest getBankSaveRequest() {
        if (bankSaveRequest == null) {
            bankSaveRequest = new BankSaveRequest(
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

        return bankSaveRequest;
    }

    public List<Bank> getBankList() {
        if (bankList == null) {
            bankList = List.of(
                    new Bank(
                            1L,
                            "Example Bank",
                            "EXBK",
                            "Main Branch",
                            "MB",
                            "1234567812345678",
                            "KAYSERI / Incesu",
                            "info@examplebank.com",
                            "0507 871 11 89"
                    ),
                    new Bank(
                            2L,
                            "Example Bank",
                            "EXBK",
                            "Main Branch",
                            "MB",
                            "1234567812345678",
                            "KAYSERI / Incesu",
                            "info@examplebank.com",
                            "0507 871 11 89"
                    )
            );
        }

        return bankList;
    }
}
