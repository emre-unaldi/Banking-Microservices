package unaldi.bankservice.service.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unaldi.bankservice.entity.Bank;
import unaldi.bankservice.entity.dto.BankDTO;
import unaldi.bankservice.entity.request.BankSaveRequest;
import unaldi.bankservice.entity.request.BankUpdateRequest;
import unaldi.bankservice.repository.BankRepository;
import unaldi.bankservice.service.abstracts.BankService;
import unaldi.bankservice.service.abstracts.mapper.BankMapper;
import unaldi.bankservice.utils.constant.ExceptionMessages;
import unaldi.bankservice.utils.constant.Messages;
import unaldi.bankservice.utils.exception.customExceptions.BankNotFoundException;
import unaldi.bankservice.utils.result.DataResult;
import unaldi.bankservice.utils.result.Result;
import unaldi.bankservice.utils.result.SuccessDataResult;
import unaldi.bankservice.utils.result.SuccessResult;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@Service
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public DataResult<BankDTO> save(BankSaveRequest bankSaveRequest) {
        Bank bank = BankMapper.INSTANCE.convertToSaveBank(bankSaveRequest);
        this.bankRepository.save(bank);

        return new SuccessDataResult<>(
                BankMapper.INSTANCE.convertToBankDTO(bank),
                Messages.BANK_CREATED
        );
    }

    @Override
    public DataResult<BankDTO> update(BankUpdateRequest bankUpdateRequest) {
        if(!this.bankRepository.existsById(bankUpdateRequest.id()))
            throw new BankNotFoundException(ExceptionMessages.BANK_NOT_FOUND);

        Bank bank = BankMapper.INSTANCE.convertToUpdateBank(bankUpdateRequest);
        this.bankRepository.save(bank);

        return new SuccessDataResult<>(
                BankMapper.INSTANCE.convertToBankDTO(bank),
                Messages.BANK_UPDATED
        );
    }

    @Override
    public Result deleteById(Long bankId) {
        Bank bank = this.bankRepository
                .findById(bankId)
                .orElseThrow(() -> new BankNotFoundException(ExceptionMessages.BANK_NOT_FOUND));

        this.bankRepository.deleteById(bank.getId());

        return new SuccessResult(Messages.BANK_DELETED);
    }

    @Override
    public DataResult<BankDTO> findById(Long bankId) {
        BankDTO bankDTO = this.bankRepository
                .findById(bankId)
                .map(BankMapper.INSTANCE::convertToBankDTO)
                .orElseThrow(() -> new BankNotFoundException(ExceptionMessages.BANK_NOT_FOUND));

        return new SuccessDataResult<>(bankDTO, Messages.BANK_FOUND);
    }

    @Override
    public DataResult<List<BankDTO>> findAll() {
        List<Bank> bankList = this.bankRepository.findAll();

        return new SuccessDataResult<>(
                BankMapper.INSTANCE.convertBankDTOs(bankList),
                Messages.BANKS_LISTED
        );
    }
}
