package unaldi.bankservice.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import unaldi.bankservice.entity.Bank;
import unaldi.bankservice.entity.dto.BankDTO;
import unaldi.bankservice.entity.request.BankSaveRequest;
import unaldi.bankservice.entity.request.BankUpdateRequest;
import unaldi.bankservice.service.abstracts.mapper.BankMapper;
import unaldi.bankservice.utils.FailTestMessages;
import unaldi.bankservice.utils.ObjectFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
class BankMapperTest {
    private static Bank bank;
    private static List<Bank> bankList;
    private static BankSaveRequest bankSaveRequest;
    private static BankUpdateRequest bankUpdateRequest;

    @BeforeAll
    static void setUp() {
        bank = ObjectFactory.getInstance().getBank();
        bankList = ObjectFactory.getInstance().getBankList();
        bankSaveRequest = ObjectFactory.getInstance().getBankSaveRequest();
        bankUpdateRequest = ObjectFactory.getInstance().getBankUpdateRequest();
    }

    @Test
    void should_convertBankSaveRequestToBank() {
        Bank bank = BankMapper.INSTANCE.convertToSaveBank(bankSaveRequest);

        assertAll(
                () -> assertEquals(bankSaveRequest.bankName(), bank.getBankName(), FailTestMessages.BANK_NAME_MISMATCH),
                () -> assertEquals(bankSaveRequest.bankCode(), bank.getBankCode(), FailTestMessages.BANK_CODE_MISMATCH),
                () -> assertEquals(bankSaveRequest.branchName(), bank.getBranchName(), FailTestMessages.BRANCH_NAME_MISMATCH),
                () -> assertEquals(bankSaveRequest.branchCode(), bank.getBranchCode(), FailTestMessages.BRANCH_CODE_MISMATCH),
                () -> assertEquals(bankSaveRequest.accountNumber(), bank.getAccountNumber(), FailTestMessages.ACCOUNT_NUMBER_MISMATCH),
                () -> assertEquals(bankSaveRequest.address(), bank.getAddress(), FailTestMessages.ADDRESS_MISMATCH),
                () -> assertEquals(bankSaveRequest.email(), bank.getEmail(), FailTestMessages.EMAIL_MISMATCH),
                () -> assertEquals(bankSaveRequest.phoneNumber(), bank.getPhoneNumber(), FailTestMessages.PHONE_NUMBER_MISMATCH)
        );
    }

    @Test
    void should_convertUpdateRequestToBank() {
        Bank bank = BankMapper.INSTANCE.convertToUpdateBank(bankUpdateRequest);

        assertAll(
                () -> assertEquals(bankUpdateRequest.bankName(), bank.getBankName(), FailTestMessages.BANK_NAME_MISMATCH),
                () -> assertEquals(bankUpdateRequest.bankCode(), bank.getBankCode(), FailTestMessages.BANK_CODE_MISMATCH),
                () -> assertEquals(bankUpdateRequest.branchName(), bank.getBranchName(), FailTestMessages.BRANCH_NAME_MISMATCH),
                () -> assertEquals(bankUpdateRequest.branchCode(), bank.getBranchCode(), FailTestMessages.BRANCH_CODE_MISMATCH),
                () -> assertEquals(bankUpdateRequest.accountNumber(), bank.getAccountNumber(), FailTestMessages.ACCOUNT_NUMBER_MISMATCH),
                () -> assertEquals(bankUpdateRequest.address(), bank.getAddress(), FailTestMessages.ADDRESS_MISMATCH),
                () -> assertEquals(bankUpdateRequest.email(), bank.getEmail(), FailTestMessages.EMAIL_MISMATCH),
                () -> assertEquals(bankUpdateRequest.phoneNumber(), bank.getPhoneNumber(), FailTestMessages.PHONE_NUMBER_MISMATCH)
        );
    }

    @Test
    void should_convertBankToBankDTO() {
        BankDTO bankDTO = BankMapper.INSTANCE.convertToBankDTO(bank);

        assertAll(
                () -> assertEquals(bank.getBankName(), bankDTO.bankName(), FailTestMessages.BANK_NAME_MISMATCH),
                () -> assertEquals(bank.getBankCode(), bankDTO.bankCode(), FailTestMessages.BANK_CODE_MISMATCH),
                () -> assertEquals(bank.getBranchName(), bankDTO.branchName(), FailTestMessages.BRANCH_NAME_MISMATCH),
                () -> assertEquals(bank.getBranchCode(), bankDTO.branchCode(), FailTestMessages.BRANCH_CODE_MISMATCH),
                () -> assertEquals(bank.getAccountNumber(), bankDTO.accountNumber(), FailTestMessages.ACCOUNT_NUMBER_MISMATCH),
                () -> assertEquals(bank.getAddress(), bankDTO.address(), FailTestMessages.ADDRESS_MISMATCH),
                () -> assertEquals(bank.getEmail(), bankDTO.email(), FailTestMessages.EMAIL_MISMATCH),
                () -> assertEquals(bank.getPhoneNumber(), bankDTO.phoneNumber(), FailTestMessages.PHONE_NUMBER_MISMATCH)
        );
    }

    @Test
    void should_convertListOfBanksToListOfBankDTOs() {
        List<BankDTO> bankDTOList = BankMapper.INSTANCE.convertBankDTOs(bankList);

        assertEquals(bankList.get(0).getId(), bankDTOList.get(0).id());
        assertEquals(bankList.get(1).getId(), bankDTOList.get(1).id());
    }
}
