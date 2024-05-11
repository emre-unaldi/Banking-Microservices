package unaldi.bankservice.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import unaldi.bankservice.entity.Bank;
import unaldi.bankservice.entity.dto.BankDTO;
import unaldi.bankservice.entity.request.BankSaveRequest;
import unaldi.bankservice.entity.request.BankUpdateRequest;
import unaldi.bankservice.repository.BankRepository;
import unaldi.bankservice.service.concretes.BankServiceImpl;
import unaldi.bankservice.utils.FailTestMessages;
import unaldi.bankservice.utils.ObjectFactory;
import unaldi.bankservice.utils.exception.customExceptions.BankNotFoundException;
import unaldi.bankservice.utils.rabbitMQ.producer.LogProducer;
import unaldi.bankservice.utils.rabbitMQ.request.LogRequest;
import unaldi.bankservice.utils.result.DataResult;
import unaldi.bankservice.utils.result.Result;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@ExtendWith(MockitoExtension.class)
class BankServiceTest {
    private static Bank bank;
    private static List<Bank> bankList;
    private static BankSaveRequest bankSaveRequest;
    private static BankUpdateRequest bankUpdateRequest;
    private final Long nonExistentBankId = -1L;

    @Mock
    private BankRepository bankRepository;
    @Mock
    private LogProducer logProducer;
    @InjectMocks
    private BankServiceImpl bankService;

    @BeforeAll
    static void setUp() {
        bank = ObjectFactory.getInstance().getBank();
        bankList = ObjectFactory.getInstance().getBankList();
        bankSaveRequest = ObjectFactory.getInstance().getBankSaveRequest();
        bankUpdateRequest = ObjectFactory.getInstance().getBankUpdateRequest();
    }

    @Test
    void givenBankSaveRequest_whenSave_thenBankShouldBeSaved() {
        when(bankRepository.save(any(Bank.class))).thenReturn(bank);

        DataResult<BankDTO> result = bankService.save(bankSaveRequest);
        assertTrue(result.getSuccess(), FailTestMessages.BANK_SAVE);

        verify(bankRepository, times(1)).save(any(Bank.class));
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }


    @Test
    void givenBankUpdateRequest_whenUpdate_thenBankShouldBeUpdated() {
        when(bankRepository.existsById(bankUpdateRequest.id())).thenReturn(true);
        when(bankRepository.save(any(Bank.class))).thenReturn(bank);

        DataResult<BankDTO> result = bankService.update(bankUpdateRequest);
        assertTrue(result.getSuccess(), FailTestMessages.BANK_UPDATE);

        verify(bankRepository, times(1)).existsById(anyLong());
        verify(bankRepository, times(1)).save(any(Bank.class));
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenBankId_whenDeleteById_thenBankShouldBeDeleted() {
        when(bankRepository.findById(bank.getId())).thenReturn(Optional.of(bank));

        Result result = bankService.deleteById(bank.getId());
        assertTrue(result.getSuccess(), FailTestMessages.BANK_DELETE);

        verify(bankRepository, times(1)).deleteById(bank.getId());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenBankId_whenFindById_thenBankShouldBeFound() {
        when(bankRepository.findById(bank.getId())).thenReturn(Optional.of(bank));

        DataResult<BankDTO> result = bankService.findById(bank.getId());
        assertTrue(result.getSuccess(), FailTestMessages.BANK_FIND);

        verify(bankRepository, times(1)).findById(bank.getId());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenBankList_whenFindAll_thenAllBanksShouldBeReturned() {
        when(bankRepository.findAll()).thenReturn(bankList);

        DataResult<List<BankDTO>> result = bankService.findAll();
        assertTrue(result.getSuccess(), FailTestMessages.BANKS_FIND);

        verify(bankRepository, times(1)).findAll();
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentBankUpdateRequest_whenUpdate_thenBankNotFoundExceptionShouldBeThrown() {
        when(bankRepository.existsById(bankUpdateRequest.id())).thenReturn(false);

        assertThrows(BankNotFoundException.class, () -> {
            bankService.update(bankUpdateRequest);
        }, FailTestMessages.BANK_UPDATE_EXCEPTION);

        verify(bankRepository, times(1)).existsById(anyLong());
        verify(bankRepository, never()).save(any(Bank.class));
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentBankId_whenDeleteById_thenBankNotFoundExceptionShouldBeThrown() {
        when(bankRepository.findById(nonExistentBankId)).thenReturn(Optional.empty());

        assertThrows(BankNotFoundException.class, () -> {
            bankService.deleteById(nonExistentBankId);
        }, FailTestMessages.BANK_DELETE_EXCEPTION);

        verify(bankRepository, times(1)).findById(nonExistentBankId);
        verify(bankRepository, never()).deleteById(anyLong());
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentBankId_whenFindById_thenBankNotFoundExceptionShouldBeThrown() {
        when(bankRepository.findById(nonExistentBankId)).thenReturn(Optional.empty());

        assertThrows(BankNotFoundException.class, () -> {
            bankService.findById(nonExistentBankId);
        }, FailTestMessages.BANK_FIND_EXCEPTION);

        verify(bankRepository, times(1)).findById(nonExistentBankId);
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }
}
