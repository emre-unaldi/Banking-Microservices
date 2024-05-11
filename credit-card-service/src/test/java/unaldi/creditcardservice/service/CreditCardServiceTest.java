package unaldi.creditcardservice.service;

import org.junit.jupiter.api.BeforeAll;
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
    private static CreditCard creditCard;
    private static List<CreditCard> creditCardList;
    private static CreditCardSaveRequest creditCardSaveRequest;
    private static CreditCardUpdateRequest creditCardUpdateRequest;
    private static UserResponse userResponse;
    private static BankResponse bankResponse;
    private static RestResponse<UserResponse> userRestResponse;
    private static RestResponse<BankResponse> bankRestResponse;
    private final Long nonExistentCreditCardId = -1L;

    @Mock
    private CreditCardRepository creditCardRepository;
    @Mock
    private LogProducer logProducer;
    @Mock
    private UserServiceClient userServiceClient;
    @Mock
    private BankServiceClient bankServiceClient;
    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @BeforeAll
    static void setUp() {
        creditCard = ObjectFactory.getInstance().getCreditCard();
        creditCardList = ObjectFactory.getInstance().getCreditCardList();
        creditCardSaveRequest = ObjectFactory.getInstance().getCreditCardSaveRequest();
        creditCardUpdateRequest = ObjectFactory.getInstance().getCreditCardUpdateRequest();
        userResponse = ObjectFactory.getInstance().getUserResponse();
        bankResponse = ObjectFactory.getInstance().getBankResponse();
        userRestResponse = ObjectFactory.getInstance().getRestResponse(userResponse);
        bankRestResponse = ObjectFactory.getInstance().getRestResponse(bankResponse);
    }

    @Test
    void givenCreditCardSaveRequest_whenSave_thenCreditCardShouldBeSaved() {
        when(creditCardRepository.save(any(CreditCard.class))).thenReturn(creditCard);

        DataResult<CreditCardDTO> result = creditCardService.save(creditCardSaveRequest);
        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARD_SAVE);
        assertNotNull(result.getData());

        verify(creditCardRepository, times(1)).save(any(CreditCard.class));
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenCreditCardUpdateRequest_whenUpdate_thenCreditCardShouldBeUpdated() {
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
        when(creditCardRepository.findById(creditCard.getId())).thenReturn(Optional.of(creditCard));

        Result result = creditCardService.deleteById(creditCard.getId());
        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARD_DELETE);

        verify(creditCardRepository, times(1)).deleteById(creditCard.getId());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenCreditCardId_whenFindById_thenCreditCardShouldBeFound() {
        when(creditCardRepository.findById(creditCard.getId())).thenReturn(Optional.of(creditCard));

        DataResult<CreditCardDTO> result = creditCardService.findById(creditCard.getId());
        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARD_FIND);

        verify(creditCardRepository, times(1)).findById(creditCard.getId());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenUserResponse_whenFindCreditCardUserByUserId_thenCreditCardUserShouldBeFound() {
        when(userServiceClient.findById(userResponse.id())).thenReturn(ResponseEntity.ok(userRestResponse));

        DataResult<UserResponse> result = creditCardService.findCreditCardUserByUserId(userResponse.id());
        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARD_USER_FIND);

        verify(userServiceClient, times(1)).findById(userResponse.id());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenBankResponse_whenFindCreditCardBankByBankId_thenCreditCardBankShouldBeFound() {
        when(bankServiceClient.findById(bankResponse.id())).thenReturn(ResponseEntity.ok(bankRestResponse));

        DataResult<BankResponse> result = creditCardService.findCreditCardBankByBankId(bankResponse.id());
        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARD_BANK_FIND);

        verify(bankServiceClient, times(1)).findById(bankResponse.id());
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenCreditCardList_whenFindAll_thenAllCreditCardsShouldBeReturned() {
        when(creditCardRepository.findAll()).thenReturn(creditCardList);

        DataResult<List<CreditCardDTO>> result = creditCardService.findAll();
        assertTrue(result.getSuccess(), FailTestMessages.CREDIT_CARDS_FIND);

        verify(creditCardRepository, times(1)).findAll();
        verify(logProducer, times(1)).sendToLog(any(LogRequest.class));
    }

    @Test
    void givenNonExistentCreditCardUpdateRequest_whenUpdate_thenCreditCardNotFoundExceptionShouldBeThrown() {
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
        when(creditCardRepository.findById(nonExistentCreditCardId)).thenReturn(Optional.empty());

        assertThrows(CreditCardNotFoundException.class, () -> {
            creditCardService.findById(nonExistentCreditCardId);
        }, FailTestMessages.CREDIT_CARD_FIND_EXCEPTION);

        verify(creditCardRepository, times(1)).findById(nonExistentCreditCardId);
        verify(logProducer, never()).sendToLog(any(LogRequest.class));
    }
}
