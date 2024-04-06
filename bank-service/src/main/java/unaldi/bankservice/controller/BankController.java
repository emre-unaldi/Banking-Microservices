package unaldi.bankservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unaldi.bankservice.entity.dto.BankDTO;
import unaldi.bankservice.entity.request.BankSaveRequest;
import unaldi.bankservice.entity.request.BankUpdateRequest;
import unaldi.bankservice.service.abstracts.BankService;
import unaldi.bankservice.utils.result.DataResult;
import unaldi.bankservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@RestController
@RequestMapping("api/v1/banks")
public class BankController {
    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping
    public ResponseEntity<DataResult<BankDTO>> save(@Valid @RequestBody BankSaveRequest bankSaveRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.bankService.save(bankSaveRequest));
    }

    @PutMapping
    public ResponseEntity<DataResult<BankDTO>> update(@RequestBody BankUpdateRequest bankUpdateRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.bankService.update(bankUpdateRequest));
    }

    @DeleteMapping("/{bankId}")
    public ResponseEntity<Result> delete(@PathVariable Long bankId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.bankService.deleteById(bankId));
    }

    @GetMapping("/{bankId}")
    public ResponseEntity<DataResult<BankDTO>> findById(@PathVariable Long bankId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.bankService.findById(bankId));
    }

    @GetMapping
    public ResponseEntity<DataResult<List<BankDTO>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.bankService.findAll());
    }
}

