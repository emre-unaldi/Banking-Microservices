package unaldi.bankservice.service.abstracts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import unaldi.bankservice.entity.Bank;
import unaldi.bankservice.entity.dto.BankDTO;
import unaldi.bankservice.entity.request.BankSaveRequest;
import unaldi.bankservice.entity.request.BankUpdateRequest;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankMapper {
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    Bank convertToSaveBank(BankSaveRequest bankSaveRequest);
    Bank convertToUpdateBank(BankUpdateRequest bankUpdateRequest);
    BankDTO convertToBankDTO(Bank bank);
    List<BankDTO> convertBankDTOs(List<Bank> bankList);
}
