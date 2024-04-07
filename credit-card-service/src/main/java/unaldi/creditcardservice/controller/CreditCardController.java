package unaldi.creditcardservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unaldi.creditcardservice.entity.dto.CreditCardDTO;
import unaldi.creditcardservice.entity.request.CreditCardSaveRequest;
import unaldi.creditcardservice.entity.request.CreditCardUpdateRequest;
import unaldi.creditcardservice.service.abstracts.CreditCardService;
import unaldi.creditcardservice.utils.client.response.BankResponse;
import unaldi.creditcardservice.utils.client.response.UserResponse;
import unaldi.creditcardservice.utils.result.DataResult;
import unaldi.creditcardservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@RestController
@RequestMapping("api/v1/creditCards")
public class CreditCardController {
    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping
    public ResponseEntity<DataResult<CreditCardDTO>> save(@RequestBody CreditCardSaveRequest creditCardSaveRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.creditCardService.save(creditCardSaveRequest));
    }

    @PutMapping
    public ResponseEntity<DataResult<CreditCardDTO>> update(@RequestBody CreditCardUpdateRequest creditCardUpdateRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.creditCardService.update(creditCardUpdateRequest));
    }

    @DeleteMapping("/{creditCardId}")
    public ResponseEntity<Result> deleteById(@PathVariable Long creditCardId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.creditCardService.deleteById(creditCardId));
    }

    @GetMapping("/{creditCardId}")
    public ResponseEntity<DataResult<CreditCardDTO>> findById(@PathVariable Long creditCardId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.creditCardService.findById(creditCardId));
    }

    @GetMapping
    public ResponseEntity<DataResult<List<CreditCardDTO>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.creditCardService.findAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<DataResult<UserResponse>> findCreditCardUserByUserId(@PathVariable Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.creditCardService.findCreditCardUserByUserId(userId));
    }

    @GetMapping("/banks/{bankId}")
    public ResponseEntity<DataResult<BankResponse>> findCreditCardBankByUserId(@PathVariable Long bankId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.creditCardService.findCreditCardBankByUserId(bankId));
    }
}
