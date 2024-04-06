package unaldi.creditcardservice.service.abstracts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import unaldi.creditcardservice.entity.CreditCard;
import unaldi.creditcardservice.entity.dto.CreditCardDTO;
import unaldi.creditcardservice.entity.request.CreditCardSaveRequest;
import unaldi.creditcardservice.entity.request.CreditCardUpdateRequest;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditCardMapper {
    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    CreditCard convertToSaveCreditCard(CreditCardSaveRequest creditCardSaveRequest);
    CreditCard convertToUpdateCreditCard(CreditCardUpdateRequest creditCardUpdateRequest);
    CreditCardDTO convertToCreditCardDTO(CreditCard creditCard);
    List<CreditCardDTO> convertCreditCardDTOs(List<CreditCard> creditCardList);
}
