package unaldi.creditcardservice.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import unaldi.creditcardservice.entity.CreditCard;
import unaldi.creditcardservice.entity.dto.CreditCardDTO;
import unaldi.creditcardservice.entity.request.CreditCardSaveRequest;
import unaldi.creditcardservice.entity.request.CreditCardUpdateRequest;
import unaldi.creditcardservice.service.abstracts.mapper.CreditCardMapper;

import java.time.LocalDate;
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
    void should_convertSaveRequestToCreditCard() {
        CreditCardSaveRequest creditCardSaveRequest = new CreditCardSaveRequest(
                "54984387545",
                1L,
                LocalDate.of(2001,1,1),
                "1234",
                3650.50,
                1650.50,
                1L
        );

        CreditCard creditCard = CreditCardMapper.INSTANCE.convertToSaveCreditCard(creditCardSaveRequest);

        assertEquals(creditCardSaveRequest.cardNumber(), creditCard.getCardNumber());
        assertEquals(creditCardSaveRequest.userId(), creditCard.getUserId());
        assertEquals(creditCardSaveRequest.expirationDate(), creditCard.getExpirationDate());
        assertEquals(creditCardSaveRequest.cvv(), creditCard.getCvv());
        assertEquals(creditCardSaveRequest.creditLimit(), creditCard.getCreditLimit());
        assertEquals(creditCardSaveRequest.debtAmount(), creditCard.getDebtAmount());
        assertEquals(creditCardSaveRequest.bankId(), creditCard.getBankId());
    }

    @Test
    void should_convertUpdateRequestToCreditCard() {
        CreditCardUpdateRequest creditCardUpdateRequest = new CreditCardUpdateRequest(
                1L,
                "54984387545",
                1L,
                LocalDate.of(2001,1,1),
                "1234",
                3650.50,
                1650.50,
                1L
        );

        CreditCard creditCard = CreditCardMapper.INSTANCE.convertToUpdateCreditCard(creditCardUpdateRequest);

        assertEquals(creditCardUpdateRequest.cardNumber(), creditCard.getCardNumber());
        assertEquals(creditCardUpdateRequest.userId(), creditCard.getUserId());
        assertEquals(creditCardUpdateRequest.expirationDate(), creditCard.getExpirationDate());
        assertEquals(creditCardUpdateRequest.cvv(), creditCard.getCvv());
        assertEquals(creditCardUpdateRequest.creditLimit(), creditCard.getCreditLimit());
        assertEquals(creditCardUpdateRequest.debtAmount(), creditCard.getDebtAmount());
        assertEquals(creditCardUpdateRequest.bankId(), creditCard.getBankId());
    }

    @Test
    void should_convertCreditCardToCreditCardDTO() {
        CreditCard creditCard = new CreditCard(
                1L,
                "54984387545",
                1L,
                LocalDate.of(2001,1,1),
                "1234",
                3650.50,
                1650.50,
                1L
        );

        CreditCardDTO creditCardDTO = CreditCardMapper.INSTANCE.convertToCreditCardDTO(creditCard);

        assertEquals(creditCardDTO.cardNumber(), creditCard.getCardNumber());
        assertEquals(creditCardDTO.userId(), creditCard.getUserId());
        assertEquals(creditCardDTO.expirationDate(), creditCard.getExpirationDate());
        assertEquals(creditCardDTO.cvv(), creditCard.getCvv());
        assertEquals(creditCardDTO.creditLimit(), creditCard.getCreditLimit());
        assertEquals(creditCardDTO.debtAmount(), creditCard.getDebtAmount());
        assertEquals(creditCardDTO.bankId(), creditCard.getBankId());
    }

    @Test
    void should_convertListOfCreditCardsToListOfCreditCardDTOs() {
        List<CreditCard> creditCardList = new ArrayList<>(Arrays.asList(
                new CreditCard(
                        1L,
                        "54984387545",
                        1L,
                        LocalDate.of(2001,1,1),
                        "1234",
                        3650.50,
                        1650.50,
                        1L
                ),
                new CreditCard(
                        2L,
                        "54984387545",
                        1L,
                        LocalDate.of(2001,1,1),
                        "1234",
                        3650.50,
                        1650.50,
                        1L
                )
        ));

        List<CreditCardDTO> creditCardDTOList = CreditCardMapper.INSTANCE.convertCreditCardDTOs(creditCardList);

        assertEquals(creditCardList.get(0).getId(), creditCardDTOList.get(0).id());
        assertEquals(creditCardList.get(1).getId(), creditCardDTOList.get(1).id());
    }
}
