package unaldi.creditcardservice.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import unaldi.creditcardservice.entity.CreditCard;
import unaldi.creditcardservice.entity.dto.CreditCardDTO;
import unaldi.creditcardservice.entity.request.CreditCardSaveRequest;
import unaldi.creditcardservice.entity.request.CreditCardUpdateRequest;
import unaldi.creditcardservice.service.abstracts.mapper.CreditCardMapper;
import unaldi.creditcardservice.utils.FailTestMessages;
import unaldi.creditcardservice.utils.ObjectFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@SpringBootTest
class MapperTest {
    @Test
    void should_convertSaveRequestToCreditCard() {
        CreditCardSaveRequest creditCardSaveRequest = ObjectFactory.getInstance().getCreditCardSaveRequest();
        CreditCard creditCard = CreditCardMapper.INSTANCE.convertToSaveCreditCard(creditCardSaveRequest);

        assertAll(
                () -> assertEquals(creditCardSaveRequest.cardNumber(), creditCard.getCardNumber(), FailTestMessages.CARD_NUMBER_MISMATCH),
                () -> assertEquals(creditCardSaveRequest.userId(), creditCard.getUserId(), FailTestMessages.USER_ID_MISMATCH),
                () -> assertEquals(creditCardSaveRequest.expirationDate(), creditCard.getExpirationDate(), FailTestMessages.EXPIRATION_DATE_MISMATCH),
                () -> assertEquals(creditCardSaveRequest.cvv(), creditCard.getCvv(), FailTestMessages.CVV_MISMATCH),
                () -> assertEquals(creditCardSaveRequest.creditLimit(), creditCard.getCreditLimit(), FailTestMessages.CREDIT_LIMIT_MISMATCH),
                () -> assertEquals(creditCardSaveRequest.debtAmount(), creditCard.getDebtAmount(), FailTestMessages.DEBT_AMOUNT_MISMATCH),
                () -> assertEquals(creditCardSaveRequest.bankId(), creditCard.getBankId(), FailTestMessages.BANK_ID_MISMATCH)
        );
    }

    @Test
    void should_convertUpdateRequestToCreditCard() {
        CreditCardUpdateRequest creditCardUpdateRequest = ObjectFactory.getInstance().getCreditCardUpdateRequest();
        CreditCard creditCard = CreditCardMapper.INSTANCE.convertToUpdateCreditCard(creditCardUpdateRequest);

        assertAll(
                () -> assertEquals(creditCardUpdateRequest.cardNumber(), creditCard.getCardNumber(), FailTestMessages.CARD_NUMBER_MISMATCH),
                () -> assertEquals(creditCardUpdateRequest.userId(), creditCard.getUserId(), FailTestMessages.USER_ID_MISMATCH),
                () -> assertEquals(creditCardUpdateRequest.expirationDate(), creditCard.getExpirationDate(), FailTestMessages.EXPIRATION_DATE_MISMATCH),
                () -> assertEquals(creditCardUpdateRequest.cvv(), creditCard.getCvv(), FailTestMessages.CVV_MISMATCH),
                () -> assertEquals(creditCardUpdateRequest.creditLimit(), creditCard.getCreditLimit(), FailTestMessages.CREDIT_LIMIT_MISMATCH),
                () -> assertEquals(creditCardUpdateRequest.debtAmount(), creditCard.getDebtAmount(), FailTestMessages.DEBT_AMOUNT_MISMATCH),
                () -> assertEquals(creditCardUpdateRequest.bankId(), creditCard.getBankId(), FailTestMessages.BANK_ID_MISMATCH)
        );
    }

    @Test
    void should_convertCreditCardToCreditCardDTO() {
        CreditCard creditCard = ObjectFactory.getInstance().getCreditCard();
        CreditCardDTO creditCardDTO = CreditCardMapper.INSTANCE.convertToCreditCardDTO(creditCard);

        assertAll(
                () -> assertEquals(creditCardDTO.cardNumber(), creditCard.getCardNumber(), FailTestMessages.CARD_NUMBER_MISMATCH),
                () -> assertEquals(creditCardDTO.userId(), creditCard.getUserId(), FailTestMessages.USER_ID_MISMATCH),
                () -> assertEquals(creditCardDTO.expirationDate(), creditCard.getExpirationDate(), FailTestMessages.EXPIRATION_DATE_MISMATCH),
                () -> assertEquals(creditCardDTO.cvv(), creditCard.getCvv(), FailTestMessages.CVV_MISMATCH),
                () -> assertEquals(creditCardDTO.creditLimit(), creditCard.getCreditLimit(), FailTestMessages.CREDIT_LIMIT_MISMATCH),
                () -> assertEquals(creditCardDTO.debtAmount(), creditCard.getDebtAmount(), FailTestMessages.DEBT_AMOUNT_MISMATCH),
                () -> assertEquals(creditCardDTO.bankId(), creditCard.getBankId(), FailTestMessages.BANK_ID_MISMATCH)
        );
    }

    @Test
    void should_convertListOfCreditCardsToListOfCreditCardDTOs() {
        List<CreditCard> creditCardList = ObjectFactory.getInstance().getCreditCardList();
        List<CreditCardDTO> creditCardDTOList = CreditCardMapper.INSTANCE.convertCreditCardDTOs(creditCardList);

        assertEquals(creditCardList.get(0).getId(), creditCardDTOList.get(0).id());
        assertEquals(creditCardList.get(1).getId(), creditCardDTOList.get(1).id());
    }
}
