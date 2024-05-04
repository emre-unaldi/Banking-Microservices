package unaldi.creditcardservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import unaldi.creditcardservice.entity.CreditCard;
import unaldi.creditcardservice.entity.dto.CreditCardDTO;
import unaldi.creditcardservice.entity.request.CreditCardSaveRequest;
import unaldi.creditcardservice.entity.request.CreditCardUpdateRequest;
import unaldi.creditcardservice.repository.CreditCardRepository;
import unaldi.creditcardservice.service.concretes.CreditCardServiceImpl;
import unaldi.creditcardservice.utils.FailTestMessages;
import unaldi.creditcardservice.utils.ObjectFactory;
import unaldi.creditcardservice.utils.client.BankServiceClient;
import unaldi.creditcardservice.utils.client.UserServiceClient;
import unaldi.creditcardservice.utils.client.dto.BankResponse;
import unaldi.creditcardservice.utils.client.dto.RestResponse;
import unaldi.creditcardservice.utils.client.dto.UserResponse;
import unaldi.creditcardservice.utils.exception.customExceptions.CreditCardNotFoundException;
import unaldi.creditcardservice.utils.rabbitMQ.producer.LogProducer;
import unaldi.creditcardservice.utils.rabbitMQ.request.LogRequest;
import unaldi.creditcardservice.utils.result.DataResult;
import unaldi.creditcardservice.utils.result.Result;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@ExtendWith(MockitoExtension.class)
class CreditCardServiceTest {
    @Mock
    CreditCardRepository creditCardRepository;

    @Mock
    private LogProducer logProducer;

    @Mock
    private UserServiceClient userServiceClient;

    @Mock
    private BankServiceClient bankServiceClient;

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @Test
    void givenCreditCardSaveRequest_whenSave_thenCreditCardShouldBeSaved() {
        CreditCardSaveRequest creditCardSaveRequest = ObjectFactory.getInstance().getCreditCardSaveRequest();
        CreditCard creditCard = ObjectFactory.getInstance().getCreditCard();

        when(creditCardRepository.save(any(CreditCard.class))).thenReturn(creditCard);

        DataResult<CreditCardDTO> result = creditCardService.save(creditCardSaveRequest);

        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARD_SAVE);
        assertNotNull(result.getData());

        verify(creditCardRepository, times(1)).save(any(CreditCard.class));
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenCreditCardUpdateRequest_whenUpdate_thenCreditCardShouldBeUpdated() {
        CreditCardUpdateRequest creditCardUpdateRequest = ObjectFactory.getInstance().getCreditCardUpdateRequest();
        CreditCard creditCard = ObjectFactory.getInstance().getCreditCard();

        when(creditCardRepository.existsById(creditCardUpdateRequest.id())).thenReturn(true);
        when(creditCardRepository.save(any(CreditCard.class))).thenReturn(creditCard);

        DataResult<CreditCardDTO> result = creditCardService.update(creditCardUpdateRequest);

        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARD_UPDATE);

        verify(creditCardRepository, times(1)).existsById(anyLong());
        verify(creditCardRepository, times(1)).save(any(CreditCard.class));
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenCreditCardId_whenDeleteById_thenCreditCardShouldBeDeleted() {
        CreditCard creditCard = ObjectFactory.getInstance().getCreditCard();

        when(creditCardRepository.findById(creditCard.getId())).thenReturn(Optional.of(creditCard));

        Result result = creditCardService.deleteById(creditCard.getId());

        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARD_DELETE);

        verify(creditCardRepository, times(1)).deleteById(creditCard.getId());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenCreditCardId_whenFindById_thenCreditCardShouldBeFound() {
        CreditCard creditCard = ObjectFactory.getInstance().getCreditCard();

        when(creditCardRepository.findById(creditCard.getId())).thenReturn(Optional.of(creditCard));

        DataResult<CreditCardDTO> result = creditCardService.findById(creditCard.getId());

        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARD_FIND);

        verify(creditCardRepository, times(1)).findById(creditCard.getId());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenUserResponse_whenFindCreditCardUserByUserId_thenCreditCardUserShouldBeFound() {
        UserResponse userResponse = ObjectFactory.getInstance().getUserResponse();
        RestResponse<UserResponse> userRestResponse = ObjectFactory.getInstance().getRestResponse(userResponse);
        ResponseEntity<RestResponse<UserResponse>> response = ResponseEntity.ok(userRestResponse);

        when(userServiceClient.findById(userResponse.id())).thenReturn(response);

        DataResult<UserResponse> result = creditCardService.findCreditCardUserByUserId(userResponse.id());

        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARD_USER_FIND);

        verify(userServiceClient, times(1)).findById(userResponse.id());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenBankResponse_whenFindCreditCardBankByBankId_thenCreditCardBankShouldBeFound() {
        BankResponse bankResponse = ObjectFactory.getInstance().getBankResponse();
        RestResponse<BankResponse> bankRestResponse = ObjectFactory.getInstance().getRestResponse(bankResponse);
        ResponseEntity<RestResponse<BankResponse>> response = ResponseEntity.ok(bankRestResponse);

        when(bankServiceClient.findById(bankResponse.id())).thenReturn(response);

        DataResult<BankResponse> result = creditCardService.findCreditCardBankByBankId(bankResponse.id());

        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARD_BANK_FIND);

        verify(bankServiceClient, times(1)).findById(bankResponse.id());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenCreditCardList_whenFindAll_thenAllCreditCardsShouldBeReturned() {
        List<CreditCard> creditCardList = ObjectFactory.getInstance().getCreditCardList();

        when(creditCardRepository.findAll()).thenReturn(creditCardList);

        DataResult<List<CreditCardDTO>> result = creditCardService.findAll();

        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARDS_FIND);

        verify(creditCardRepository, times(1)).findAll();
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentCreditCardUpdateRequest_whenUpdate_thenCreditCardNotFoundExceptionShouldBeThrown() {
        CreditCardUpdateRequest creditCardUpdateRequest = ObjectFactory.getInstance().getCreditCardUpdateRequest();

        when(creditCardRepository.existsById(creditCardUpdateRequest.id())).thenReturn(false);

        assertThrows(CreditCardNotFoundException.class, () -> {
            creditCardService.update(creditCardUpdateRequest);
        }, FailTestMessages.CREDIT_CARD_UPDATE_EXCEPTION);

        verify(creditCardRepository, times(1)).existsById(anyLong());
        verify(creditCardRepository, never()).save(any(CreditCard.class));
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentCreditCardId_whenDeleteById_thenCreditCardNotFoundExceptionShouldBeThrown() {
        Long nonExistentCreditCardId = -1L;

        when(creditCardRepository.findById(nonExistentCreditCardId)).thenReturn(Optional.empty());

        assertThrows(CreditCardNotFoundException.class, () -> {
            creditCardService.deleteById(nonExistentCreditCardId);
        }, FailTestMessages.CREDIT_CARD_DELETE_EXCEPTION);

        verify(creditCardRepository, times(1)).findById(nonExistentCreditCardId);
        verify(creditCardRepository, never()).deleteById(anyLong());
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentCreditCardId_whenFindById_thenCreditCardNotFoundExceptionShouldBeThrown() {
        Long nonExistentCreditCardId = -1L;

        when(creditCardRepository.findById(nonExistentCreditCardId)).thenReturn(Optional.empty());

        assertThrows(CreditCardNotFoundException.class, () -> {
            creditCardService.findById(nonExistentCreditCardId);
        }, FailTestMessages.CREDIT_CARD_FIND_EXCEPTION);

        verify(creditCardRepository, times(1)).findById(nonExistentCreditCardId);
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }
}
