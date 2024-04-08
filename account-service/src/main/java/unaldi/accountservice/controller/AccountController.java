package unaldi.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unaldi.accountservice.entity.dto.AccountDTO;
import unaldi.accountservice.entity.request.AccountSaveRequest;
import unaldi.accountservice.entity.request.AccountUpdateRequest;
import unaldi.accountservice.service.abstracts.AccountService;
import unaldi.accountservice.utils.client.dto.BankResponse;
import unaldi.accountservice.utils.client.dto.UserResponse;
import unaldi.accountservice.utils.result.DataResult;
import unaldi.accountservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<DataResult<AccountDTO>> save(@RequestBody AccountSaveRequest accountSaveRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.accountService.save(accountSaveRequest));
    }

    @PutMapping
    public ResponseEntity<DataResult<AccountDTO>> update(@RequestBody AccountUpdateRequest accountUpdateRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.accountService.update(accountUpdateRequest));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Result> deleteById(@PathVariable Long accountId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.accountService.deleteById(accountId));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<DataResult<AccountDTO>> findById(@PathVariable Long accountId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.accountService.findById(accountId));
    }

    @GetMapping
    public ResponseEntity<DataResult<List<AccountDTO>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.accountService.findAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<DataResult<UserResponse>> findAccountUserByUserId(@PathVariable Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.accountService.findAccountUserByUserId(userId));
    }

    @GetMapping("/banks/{bankId}")
    public ResponseEntity<DataResult<BankResponse>> findAccountBankByBankId(@PathVariable Long bankId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.accountService.findAccountBankByBankId(bankId));
    }
}
