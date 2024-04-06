package unaldi.bankservice.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import unaldi.bankservice.entity.Bank;
import unaldi.bankservice.entity.dto.BankDTO;
import unaldi.bankservice.entity.request.BankSaveRequest;
import unaldi.bankservice.entity.request.BankUpdateRequest;
import unaldi.bankservice.service.abstracts.mapper.BankMapper;

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
    void should_convertSaveRequestToBank() {
        BankSaveRequest bankSaveRequest = new BankSaveRequest(
                "Example Bank",
                "EXBK",
                "Main Branch",
                "MB",
                "1234567812345678",
                "KAYSERI / Incesu",
                "info@examplebank.com",
                "0507 871 11 89"
        );

        Bank bank = BankMapper.INSTANCE.convertToSaveBank(bankSaveRequest);

        assertEquals(bankSaveRequest.bankName(), bank.getBankName());
        assertEquals(bankSaveRequest.bankCode(), bank.getBankCode());
        assertEquals(bankSaveRequest.branchName(), bank.getBranchName());
        assertEquals(bankSaveRequest.branchCode(), bank.getBranchCode());
        assertEquals(bankSaveRequest.accountNumber(), bank.getAccountNumber());
        assertEquals(bankSaveRequest.address(), bank.getAddress());
        assertEquals(bankSaveRequest.email(), bank.getEmail());
        assertEquals(bankSaveRequest.phoneNumber(), bank.getPhoneNumber());
    }

    @Test
    void should_convertUpdateRequestToBank() {
        BankUpdateRequest bankUpdateRequest = new BankUpdateRequest(
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

        Bank bank = BankMapper.INSTANCE.convertToUpdateBank(bankUpdateRequest);

        assertEquals(bankUpdateRequest.bankName(), bank.getBankName());
        assertEquals(bankUpdateRequest.bankCode(), bank.getBankCode());
        assertEquals(bankUpdateRequest.branchName(), bank.getBranchName());
        assertEquals(bankUpdateRequest.branchCode(), bank.getBranchCode());
        assertEquals(bankUpdateRequest.accountNumber(), bank.getAccountNumber());
        assertEquals(bankUpdateRequest.address(), bank.getAddress());
        assertEquals(bankUpdateRequest.email(), bank.getEmail());
        assertEquals(bankUpdateRequest.phoneNumber(), bank.getPhoneNumber());
    }


    @Test
    void should_convertBankToBankDTO() {
        Bank bank = new Bank(
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

        BankDTO bankDTO = BankMapper.INSTANCE.convertToBankDTO(bank);

        assertEquals(bankDTO.bankName(), bank.getBankName());
        assertEquals(bankDTO.bankCode(), bank.getBankCode());
        assertEquals(bankDTO.branchName(), bank.getBranchName());
        assertEquals(bankDTO.branchCode(), bank.getBranchCode());
        assertEquals(bankDTO.accountNumber(), bank.getAccountNumber());
        assertEquals(bankDTO.address(), bank.getAddress());
        assertEquals(bankDTO.email(), bank.getEmail());
        assertEquals(bankDTO.phoneNumber(), bank.getPhoneNumber());
    }

    @Test
    void should_convertListOfBanksToListOfBankDTOs() {
        List<Bank> bankList = new ArrayList<>(Arrays.asList(
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
        ));

        List<BankDTO> bankDTOList = BankMapper.INSTANCE.convertBankDTOs(bankList);

        assertEquals(bankList.get(0).getId(), bankDTOList.get(0).id());
        assertEquals(bankList.get(1).getId(), bankDTOList.get(1).id());
    }


}
