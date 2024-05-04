package unaldi.creditcardservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import unaldi.creditcardservice.entity.CreditCard;
import unaldi.creditcardservice.entity.request.CreditCardSaveRequest;
import unaldi.creditcardservice.entity.request.CreditCardUpdateRequest;
import unaldi.creditcardservice.utils.client.dto.BankResponse;
import unaldi.creditcardservice.utils.client.dto.RestResponse;
import unaldi.creditcardservice.utils.client.dto.UserResponse;
import unaldi.creditcardservice.utils.client.enums.Gender;

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
    private CreditCard creditCard;
    private CreditCardUpdateRequest creditCardUpdateRequest;
    private CreditCardSaveRequest creditCardSaveRequest;
    private List<CreditCard> creditCardList;
    private UserResponse userResponse;
    private BankResponse bankResponse;

    public static synchronized ObjectFactory getInstance() {
        if (instance == null) {
            instance = new ObjectFactory();
        }

        return instance;
    }

    public CreditCard getCreditCard() {
        if (creditCard == null) {
            creditCard = new CreditCard(
                    1L,
                    "54984387545",
                    1L,
                    LocalDate.of(2001,1,1),
                    "1234",
                    3650.50,
                    1650.50,
                    1L
            );
        }

        return creditCard;
    }

    public CreditCardUpdateRequest getCreditCardUpdateRequest() {
        if (creditCardUpdateRequest == null) {
            creditCardUpdateRequest = new CreditCardUpdateRequest(
                    1L,
                    "3455367456834",
                    2L,
                    LocalDate.of(2001,1,1),
                    "5678",
                    2650.50,
                    2650.50,
                    2L
            );
        }

        return creditCardUpdateRequest;
    }

    public CreditCardSaveRequest getCreditCardSaveRequest() {
        if (creditCardSaveRequest == null) {
            creditCardSaveRequest = new CreditCardSaveRequest(
                    "54984387545",
                    1L,
                    LocalDate.of(2001,1,1),
                    "1234",
                    3650.50,
                    1650.50,
                    1L
            );
        }

        return creditCardSaveRequest;
    }

    public List<CreditCard> getCreditCardList() {
        if (creditCardList == null) {
            creditCardList = List.of(
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
            );
        }

        return creditCardList;
    }

    public UserResponse getUserResponse() {
        if (userResponse == null) {
            userResponse = new UserResponse(
                    1L,
                    "eunaldi",
                    "121221",
                    "emree.unaldi@gmail.com",
                    "Emre",
                    "Ünaldı",
                    "05078711189",
                    LocalDate.of(2001, 1, 1),
                    Gender.MALE
            );
        }

        return userResponse;
    }

    public BankResponse getBankResponse() {
        if (bankResponse == null) {
            bankResponse = new BankResponse(
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

        return bankResponse;
    }

    public <T> RestResponse<T> getRestResponse(T entityResponse) {
        return RestResponse.<T>builder()
                .data(entityResponse)
                .success(true)
                .build();
    }
}
