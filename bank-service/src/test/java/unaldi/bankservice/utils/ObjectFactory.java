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
            bank = Bank.builder()
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

        return bank;
    }

    public BankUpdateRequest getBankUpdateRequest() {
        if (bankUpdateRequest == null) {
            bankUpdateRequest = BankUpdateRequest.builder()
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

        return bankUpdateRequest;
    }

    public BankSaveRequest getBankSaveRequest() {
        if (bankSaveRequest == null) {
            bankSaveRequest = BankSaveRequest.builder()
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

        return bankSaveRequest;
    }

    public List<Bank> getBankList() {
        if (bankList == null) {
            Bank bankOne = Bank.builder()
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

            Bank bankTwo = Bank.builder()
                    .id(2L)
                    .bankName("Example Bank")
                    .bankCode("EXBK")
                    .branchName("Main Branch")
                    .branchCode("MB")
                    .accountNumber("1234567812345678")
                    .address("KAYSERI / Incesu")
                    .email("info@examplebank.com")
                    .phoneNumber("0507 871 11 89")
                    .build();

            bankList = List.of(
                    bankOne,
                    bankTwo
            );
        }

        return bankList;
    }
}
